package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public List<UserResponseDto> allUser() {
        return userService.findAllUser();
    }

    @GetMapping("/user/{id}")
    public UserResponseDto user(@PathVariable Long id) {
        return userService.findUser(id);
    }
}
