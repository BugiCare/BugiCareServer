package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.domain.User;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public List<User> allUser() {

    }

    @GetMapping("/user/{id}")
    public User user() {

    }
}
