package morti.repository.rowMapper;

import morti.dto.Requirement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mark on 12/12/2016.
 */
public class BasicRequirementMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Requirement(
                resultSet.getString("requirement_id"),
                resultSet.getString("requirement_name"),
                null,
//                resultSet.getString("release_id"),
                null,
//                resultSet.getString("release_name"),
                resultSet.getString("description"),
                resultSet.getInt("effort"),
                0
        );
    }
}
