package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

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
}
