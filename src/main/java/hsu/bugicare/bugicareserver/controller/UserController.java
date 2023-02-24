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
    
    // 테스트용
    @GetMapping("/userInfo")
    public User userInfo() {
        return User.builder()
                .id(1L)
                .name("이름")
                .address("주소")
                .age(100)
                .phone("010-1111-1111")
                .build();
    }

    // 모든 유저의 정보 테스트용
    @GetMapping("/all")
    public List<User> allUser() {
        List<User> users = new ArrayList<>();
        User user1 = User.builder()
                .id(1L)
                .name("uesr1")
                .address("address1")
                .age(1)
                .phone("010-1111-1111")
                .build();
        User user2 = User.builder()
                .id(2L)
                .name("uesr2")
                .address("address2")
                .age(2)
                .phone("010-2222-1111")
                .build();

        users.add(user1);
        users.add(user2);

        return users;
    }
}
