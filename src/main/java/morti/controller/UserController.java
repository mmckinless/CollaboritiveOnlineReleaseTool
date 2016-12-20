package morti.controller;

import morti.dto.User;
import morti.service.UserService;
import morti.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private Logger LOGGER = Logger.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<String>("User created", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Controller Exception: " + e.toString());
            return new ResponseEntity<String>("User could not be created. Exception: " + e.toString(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestParam("userId") String userId) {
        try {
            return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public ResponseEntity<String> changePassword(@RequestBody User user) {
        try {
            userService.changePassword(user.getEmail(), user.getPassword());
            return new ResponseEntity<String>("Password Reset", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/changeRole", method = RequestMethod.PUT)
    public void changeRole(@RequestParam("userId") String userId) {

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("userId") String userId) {

    }

}
