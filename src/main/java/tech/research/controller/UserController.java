package tech.research.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.research.data.User;
import tech.research.repository.UserRepository;
import tech.research.services.UserService;

/**
 * Created by lbt05 on 18/03/2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId){
        return userService.findById(userId);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public User createUser(String username, String password,
                           @RequestParam(defaultValue = "12",required = false) Integer age){
        User user = User.builder().username(username).password(password).age(age).build();
        userService.save(user);
        return user;
    }
}
