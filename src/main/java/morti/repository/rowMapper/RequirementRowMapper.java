package morti.repository.rowMapper;


import morti.dto.Requirement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequirementRowMapper implements RowMapper{

    @Override
    public Requirement mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Requirement(
                resultSet.getString("requirement_id"),
                resultSet.getString("requirement_name"),
                resultSet.getString("release_id"),
                resultSet.getString("release_name"),
                resultSet.getString("description"),
                resultSet.getInt("effort"),
                0
        );
    }

}
