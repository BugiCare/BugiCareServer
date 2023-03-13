package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public List<UserResponseDto> getAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/user/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PostMapping("/user")
    public UserResponseDto postUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }
}
