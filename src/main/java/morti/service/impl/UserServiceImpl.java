package morti.service.impl;

import morti.data.Role;
import morti.dto.User;
import morti.repository.UserRepository;
import morti.service.UserRepositoryService;
import morti.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserRepositoryService userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(User user) {

        User encryptedUser;

        List<Role> roleList = new ArrayList<Role>();
        roleList.add(Role.USER);

        encryptedUser = User.builder()
                .userId(UUID.randomUUID().toString())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(roleList)
                .build();
        userRepository.createUser(encryptedUser);
        createUserRoleMapping(encryptedUser);

    }

    private void createUserRoleMapping(User user) {
        for(Role role: user.getRole()) {
            userRepository.createUserRoleMapping(user.getUserId(), role);
        }
    }

    @Override
    public void changePassword(String email, String newPassword) {
        userRepository.changePassword(email, passwordEncoder.encode(newPassword));
    }

    @Override
    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void changeRole() {

    }
}
