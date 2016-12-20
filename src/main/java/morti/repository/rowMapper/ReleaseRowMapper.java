package morti.repository.rowMapper;

import morti.dto.Release;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ReleaseRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Release (
                resultSet.getString("release_id"),
                resultSet.getString("release_name"),
                resultSet.getString("project_id"),
                resultSet.getString("project_name"),
                null,
                0,
                resultSet.getString("release_date")
        );
    }
}
