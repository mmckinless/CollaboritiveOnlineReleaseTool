package morti.repository;

import morti.data.Role;
import morti.dto.User;
import morti.repository.rowMapper.UserRowMapper;
import morti.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepository implements UserRepositoryService {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private UserRowMapper userRowMapper;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        userRowMapper = new UserRowMapper();
    }

    @Override
    public void createUser(User user) {

      String sql = "INSERT INTO user (user_id, email, first_name, last_name, password) " +
              "VALUES (:user_id, :email, :first_name, :last_name, :password)";

      MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
              .addValue("user_id", user.getUserId())
              .addValue("email", user.getEmail())
              .addValue("first_name", user.getFirstName())
              .addValue("last_name", user.getLastName())
              .addValue("password", user.getPassword());

      jdbcTemplate.update(sql,
              mapSqlParameterSource);

    }

    @Override
    public void createUserRoleMapping(String userId, Role role) {
        String sql = "INSERT INTO user_role_mapping (user_id, user_role_id) " +
                "VALUES ((SELECT id FROM user WHERE user_id = :user_id), " +
                "(SELECT id FROM user_role WHERE role = :role))";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("role", role.toString())
                .addValue("user_id", userId);

        jdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public void changePassword(String email, String newPassword) {

        String sql = "UPDATE user SET password=:password WHERE email=:email";

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("email", email)
                .addValue("password", newPassword);

        jdbcTemplate.update(sql, sqlParameterSource);


    }

    @Override
    public User getUser(String userId) {

        String sql = "SELECT * FROM user WHERE user_id = :user_id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId);

        User user = (User) jdbcTemplate.queryForObject(sql, mapSqlParameterSource, userRowMapper);
        List<Role> role = getRoleByEmail(user.getEmail());
        user.setRole(role);

        return user;

    }

    @Override
    public User getUserByEmail(String email) {

        String sql = "SELECT * FROM user WHERE email = :email";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("email", email);

        User user = (User) jdbcTemplate.queryForObject(sql, mapSqlParameterSource, userRowMapper);
        List<Role> role = getRoleByEmail(user.getEmail());
        user.setRole(role);

        return user;

    }

    //TODO: not efficient query. Look into using joins

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM user";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

        List<User> userList = jdbcTemplate.query(sql,mapSqlParameterSource,userRowMapper);

        for (User user: userList) {
            List<Role> role = getRoleByEmail(user.getEmail());
            user.setRole(role);
            user.setPassword("");
        }
        return userList;

    }

    private List<Role> getRoleByEmail(String email) {

        String sql = "SELECT ur.role " +
                "FROM user_role ur " +
                "JOIN user_role_mapping urm ON (ur.id = urm.user_role_id)" +
                "JOIN user u ON (u.id = urm.user_id)" +
                "WHERE u.email = :email";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("email", email);

        List<String> roleString = jdbcTemplate.queryForList(sql, mapSqlParameterSource, String.class);

        List<Role> roleList = new ArrayList<Role>();
        for (String role: roleString) {
            roleList.add(Role.valueOf(role));
        }

        return roleList;

    }

}
