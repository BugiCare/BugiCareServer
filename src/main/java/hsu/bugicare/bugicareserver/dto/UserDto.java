package hsu.bugicare.bugicareserver.dto;

import hsu.bugicare.bugicareserver.domain.Gender;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class UserDto {
    private String name;
    private Gender gender;
    private String address;
    private Integer age;
    private String phone;
    private MultipartFile image;
}
