package morti.service;

import morti.dto.User;

import java.util.List;


public interface UserService {

    void createUser(User user);

    void changePassword(String email, String newPassword);

    User getUser(String userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void changeRole();

}
