package hsu.bugicare.bugicareserver.dto;

import hsu.bugicare.bugicareserver.domain.Gender;
import hsu.bugicare.bugicareserver.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private String address;
    private Integer age;
    private String phone;

    public UserResponseDto UsertoUserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.age = user.getAge();
        this.phone = user.getPhone();

        return this;
    }
}
