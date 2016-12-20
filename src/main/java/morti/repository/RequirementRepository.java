package morti.repository;

import morti.dto.Requirement;
import morti.dto.RequirementConflict;
import morti.dto.RequirementValue;
import morti.repository.rowMapper.BasicRequirementMapper;
import morti.repository.rowMapper.RequirementRowMapper;
import morti.service.RequirementRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequirementRepository implements RequirementRepositoryService {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private RequirementRowMapper requirementRowMapper;
    private BasicRequirementMapper basicRequirementMapper;


    @Autowired
    public RequirementRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        requirementRowMapper = new RequirementRowMapper();
        basicRequirementMapper = new BasicRequirementMapper();
    }

    @Override
    public void createRequirement(Requirement requirement) {

        String sql = "INSERT INTO requirements (requirement_id, requirement_name, description, effort) " +
                "VALUES (:requirement_id, :requirement_name, :description, :effort)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirement.getRequirementId())
                .addValue("requirement_name", requirement.getRequirementName())
                .addValue("description", requirement.getDescription())
                .addValue("effort", requirement.getEffort());

        jdbcTemplate.update(sql,
                mapSqlParameterSource);

    }

    @Override
    public void createRequirementUserMapping(String requirementId, String userId, Long rank) {

        String sql = "INSERT INTO requirement_user_mapping (requirement_id, user_id, rank) " +
                "VALUES ((SELECT id FROM requirements WHERE requirement_id = :requirement_id), " +
                "(SELECT id FROM user WHERE user_id = :user_id), :rank)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirementId)
                .addValue("user_id", userId)
                .addValue("rank", rank);

        jdbcTemplate.update(sql,
                mapSqlParameterSource);

    }

    @Override
    public void createRequirementReleaseMapping(Requirement requirement) {

        String sql = "INSERT INTO release_requirement_mapping (release_id, requirement_id, effort) " +
                "VALUES ((SELECT id FROM releases WHERE release_id = :release_id), " +
                "(SELECT id FROM requirements WHERE requirement_id = :requirement_id), " +
                ":effort)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("release_id", requirement.getReleaseId())
                .addValue("requirement_id", requirement.getRequirementId())
                .addValue("effort", requirement.getEffort());

        jdbcTemplate.update(sql, mapSqlParameterSource);

    }

    @Override
    public Requirement getRequirement(String requirementId) {

        String sql = "SELECT * " +
                "FROM requirements " +
                "WHERE requirement_id = :requirement_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirementId);

        Requirement requirement = (Requirement) jdbcTemplate.queryForObject(sql, mapSqlParameterSource, basicRequirementMapper);
        return requirement;

    }

    @Override
    public Requirement getReleaseRequirement(String requirementId) {

        String sql = "SELECT i.requirement_id, i.requirement_name, r.release_id, r.release_name, i.description, i.effort " +
                "FROM requirements i " +
                "JOIN release_requirement_mapping rim ON (i.id = rim.requirement_id) " +
                "JOIN releases r ON (r.id = rim.release_id) " +
                "WHERE i.requirement_id = :requirement_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirementId);

        Requirement requirement = (Requirement) jdbcTemplate.queryForObject(sql, mapSqlParameterSource, requirementRowMapper);
        return requirement;
    }

    @Override
    public List<Requirement> getAllRequirements() {

        String sql = "SELECT * " +
                "FROM requirements";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, mapSqlParameterSource, basicRequirementMapper);

    }

    @Override
    public List<Requirement> getAllReleaseRequirements() {

        String sql = "SELECT i.requirement_id, i.requirement_name, r.release_id, r.release_name, i.description, i.effort " +
                "FROM requirements i " +
                "JOIN release_requirement_mapping rim ON (i.id = rim.requirement_id) " +
                "JOIN releases r ON (r.id = rim.release_id) ";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<Requirement> requirementList = jdbcTemplate.query(sql,mapSqlParameterSource, requirementRowMapper);
        return requirementList;

    }

    @Override
    public void insertUserValue(RequirementValue requirementValue) {
        String sql = "INSERT INTO requirement_user_mapping (requirement_id, user_id, value) " +
                "VALUES ((SELECT id FROM requirements WHERE requirement_id = :requirement_id), " +
                "(SELECT id FROM user WHERE user_id = :user_id), :value)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirementValue.getRequirementId())
                .addValue("user_id", requirementValue.getUserId())
                .addValue("value", requirementValue.getValue());

        jdbcTemplate.update(sql, mapSqlParameterSource);

    }

    @Override
    public void insertConflict(RequirementConflict requirementConflict) {

        String sql = "UPDATE requirement_user_mapping " +
                "SET conflict = :conflict " +
                "WHERE requirement_id = (SELECT id FROM requirements WHERE requirement_id = :requirement_id)";

        MapSqlParameterSource mapSqlParameterSource1 = new MapSqlParameterSource()
                .addValue("requirement_id", requirementConflict.getRequirementId1())
                .addValue("conflict", requirementConflict.getConflictId());

        jdbcTemplate.update(sql, mapSqlParameterSource1);

        MapSqlParameterSource mapSqlParameterSource2 = new MapSqlParameterSource()
                .addValue("requirement_id", requirementConflict.getRequirementId2())
                .addValue("conflict", requirementConflict.getConflictId());

        jdbcTemplate.update(sql, mapSqlParameterSource2);
    }

    @Override
    public void insertDependency(RequirementConflict requirementConflict) {
        String sql = "UPDATE requirement_user_mapping " +
                "SET dependency = :dependency " +
                "WHERE requirement_id = (SELECT id FROM requirements WHERE requirement_id = :requirement_id)";

        MapSqlParameterSource mapSqlParameterSource1 = new MapSqlParameterSource()
                .addValue("requirement_id", requirementConflict.getRequirementId1())
                .addValue("dependency", requirementConflict.getConflictId());

        jdbcTemplate.update(sql, mapSqlParameterSource1);

        MapSqlParameterSource mapSqlParameterSource2 = new MapSqlParameterSource()
                .addValue("requirement_id", requirementConflict.getRequirementId2())
                .addValue("dependency", requirementConflict.getConflictId());

        jdbcTemplate.update(sql, mapSqlParameterSource2);

    }

    @Override
    public int getUserValue(String userId, String requirementId) {

        String sql = "SELECT IFNULL(SUM(value),0) " +
                "FROM requirement_user_mapping " +
                "WHERE requirement_id = (SELECT id FROM requirements WHERE requirement_id = :requirement_id) " +
                "AND user_id = (SELECT id FROM user WHERE user_id = :user_id)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirementId)
                .addValue("user_id", userId);

        return jdbcTemplate.queryForObject(sql, mapSqlParameterSource, int.class);

    }

    @Override
    public void deleteUserValue(String userId, String requirementId) {

        String sql = "DELETE " +
                "FROM requirement_user_mapping " +
                "WHERE requirement_id = (SELECT id FROM requirements WHERE requirement_id = :requirement_id) " +
                "AND user_id = (SELECT id FROM user WHERE user_id = :user_id)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("requirement_id", requirementId)
                .addValue("user_id", userId);

        jdbcTemplate.update(sql, mapSqlParameterSource);

    }

}
