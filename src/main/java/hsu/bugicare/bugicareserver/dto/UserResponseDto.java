package hsu.bugicare.bugicareserver.dto;

import hsu.bugicare.bugicareserver.domain.Gender;
import hsu.bugicare.bugicareserver.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private String address;
    private Integer age;
    private String phone;

//    private Resource imageResource;
//
//    private static final String IMAGES_PATH = "src/main/resources/static/";

    public UserResponseDto UsertoUserResponseDto(User user) throws MalformedURLException {
        this.id = user.getId();
        this.name = user.getName();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.age = user.getAge();
        this.phone = user.getPhone();

//        String imgPath = "user" + this.id + ".png";
//        Path imagePath = Paths.get(IMAGES_PATH, imgPath);
//
//        System.out.println(imgPath);
//        this.imageResource = new UrlResource(imagePath.toUri());

        return this;
    }
}
