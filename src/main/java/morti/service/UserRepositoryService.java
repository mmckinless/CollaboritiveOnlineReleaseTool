package morti.service;


import morti.data.Role;
import morti.dto.User;

import java.util.List;

public interface UserRepositoryService {

    void createUser(User user);

    void createUserRoleMapping(String userId, Role role);

    void changePassword(String email, String newPassword);

    User getUser(String userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

}
