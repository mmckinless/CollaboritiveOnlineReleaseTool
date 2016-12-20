package morti.repository;

import morti.dto.Release;
import morti.repository.rowMapper.ReleaseRowMapper;
import morti.service.ReleaseRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.RealLiteral;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReleaseRepository implements ReleaseRepositoryService {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private ReleaseRowMapper releaseRowMapper;

    @Autowired
    public ReleaseRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.releaseRowMapper = new ReleaseRowMapper();
    }

    @Override
    public void createRelease(Release release) {
        String sql = "INSERT INTO releases (release_id, release_name, release_date) " +
                "VALUES (:release_id, :release_name, :release_date)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("release_id", release.getReleaseId())
                .addValue("release_name", release.getReleaseName())
                .addValue("release_date", release.getReleaseDate());

        jdbcTemplate.update(sql,
                mapSqlParameterSource);
    }

    @Override
    public void createReleaseProjectMapping(String releaseId, String projectId) {
        String sql = "INSERT INTO project_release_mapping (release_id, project_id)" +
                "VALUES ((SELECT id FROM releases WHERE  release_id = :release_id)," +
                "(SELECT id FROM project WHERE project_id = :project_id))";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("release_id", releaseId)
                .addValue("project_id", projectId);

        jdbcTemplate.update(sql,mapSqlParameterSource);
    }

    @Override
    public void createReleaseRequirementMapping(String releaseId, String requirementId) {
        String sql = "INSERT INTO release_requirement_mapping (release_id, requirement_id) " +
                "VALUES ((SELECT id FROM releases WHERE release_id = :release_id), " +
                "(SELECT id FROM requirements WHERE requirement_id = :requirement_id))";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("release_id", releaseId)
                .addValue("requirement_id", requirementId);

        jdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public Release getReleaseByReleaseId(String releaseId) {
        String sql =  "SELECT r.release_id, r.release_name, p.project_id, p.project_name, r.release_date " +
                "FROM releases r " +
                "JOIN project_release_mapping prm ON (r.id = prm.release_id) " +
                "JOIN project p ON (p.id = prm.project_id) " +
                "WHERE r.release_id=:release_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("release_id", releaseId);

        Release release = (Release) jdbcTemplate.queryForObject(sql,mapSqlParameterSource,releaseRowMapper);
        return release;
    }

    @Override
    public List<Release> getAllRelease() {

        String sql =  "SELECT r.release_id, r.release_name, p.project_id, p.project_name, r.release_date " +
                "FROM releases r " +
                "JOIN project_release_mapping prm ON (r.id = prm.release_id) " +
                "JOIN project p ON (p.id = prm.project_id) ";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        return jdbcTemplate.query(sql,mapSqlParameterSource,releaseRowMapper);

    }

    @Override
    public int getReleaseEffort(Release release) {
        String sql = "SELECT IFNULL(SUM(rrm.effort),0) " +
                "FROM releases r " +
                "JOIN release_requirement_mapping rrm ON (r.id = rrm.release_id) " +
                "WHERE r.release_id = :release_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("release_id", release.getReleaseId());

        return jdbcTemplate.queryForObject(sql, mapSqlParameterSource, int.class);

    }


    @Override
    public List<Release> getAllReleaseByProjectId(String projectId) {


        String sql = "SELECT r.release_id, r.release_name, p.project_id, p.project_name, r.release_date " +
                "FROM releases r " +
                "JOIN project_release_mapping prm ON (r.id = prm.release_id) " +
                "JOIN project p ON (p.id = prm.project_id) " +
                "WHERE p.project_id = :project_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("project_id", projectId);

        return jdbcTemplate.query(sql,mapSqlParameterSource,releaseRowMapper);

    }

}
