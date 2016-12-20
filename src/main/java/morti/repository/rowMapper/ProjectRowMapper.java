package morti.repository.rowMapper;


import morti.dto.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Project(
                resultSet.getLong("id"),
                resultSet.getString("project_id"),
                resultSet.getString("project_name"),
                0
        );
    }
}
