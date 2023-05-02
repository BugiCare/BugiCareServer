package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.domain.Gender;
import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private static final String IMAGES_PATH = "src/main/resources/images";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public List<UserResponseDto> getAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping(value = "/userImage/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable Long id) throws IOException {
        Path imagePath = Paths.get(IMAGES_PATH, "user" + id + ".png");
        Resource imageResource = new UrlResource(imagePath.toUri());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageResource);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> postUser(
            @RequestParam("name") String name,
            @RequestParam("gender") Gender gender,
            @RequestParam("address") String address,
            @RequestParam("age") Integer age,
            @RequestParam("phone") String phone,
            @RequestParam("image") MultipartFile image
            ) throws Exception {
            UserDto userDto = UserDto.builder()
                    .name(name)
                    .gender(gender)
                    .address(address)
                    .age(age)
                    .phone(phone)
                    .image(image)
                    .build();
            UserResponseDto userResponseDto = userService.saveUser(userDto);
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}
