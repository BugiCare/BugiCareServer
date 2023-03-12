package hsu.bugicare.bugicareserver.dto;

import lombok.Builder;

@Builder
public class UserResponseDto {
    private String name;
    private String gender;
    private String address;
    private Integer age;
    private String phone;
}
