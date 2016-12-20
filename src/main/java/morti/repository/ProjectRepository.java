package morti.repository;

import morti.dto.Project;
import morti.repository.rowMapper.ProjectRowMapper;
import morti.service.ProjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository implements ProjectRepositoryService{

    private NamedParameterJdbcTemplate jdbcTemplate;

    private ProjectRowMapper projectRowMapper;

    @Autowired
    public ProjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        projectRowMapper = new ProjectRowMapper();
    }

    @Override
    public void createProject(Project project) {
        String sql = "INSERT INTO project (project_id, project_name)" +
                "VALUES (:project_id, :project_name)";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("project_id", project.getProjectId())
                .addValue("project_name", project.getProjectName());

        jdbcTemplate.update(sql,mapSqlParameterSource);

    }

    @Override
    public Project getProject(String projectId) {

        String sql = "SELECT * " +
                "FROM project " +
                "WHERE project_id = :project_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("project_id", projectId);

        Project project = (Project) jdbcTemplate.queryForObject(sql,mapSqlParameterSource,projectRowMapper);

        return project;
    }

    @Override
    public List<Project> getAllProjects() {
        String sql = "SELECT *" +
                "FROM project ";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        return jdbcTemplate.query(sql,mapSqlParameterSource,projectRowMapper);
    }

    @Override
    public int getNumberOfReleasesPerProject(String projectId) {
        String sql = "SELECT COUNT(r.id) " +
                "FROM project p " +
                "JOIN project_release_mapping prm ON (p.id = prm.project_id) " +
                "JOIN releases r ON (r.id = prm.release_id) " +
                "WHERE p.project_id = :project_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("project_id", projectId);

        return jdbcTemplate.queryForObject(sql, mapSqlParameterSource, int.class);

    }

}
