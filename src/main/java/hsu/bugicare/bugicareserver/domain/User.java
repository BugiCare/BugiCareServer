package hsu.bugicare.bugicareserver.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private String address;
    private Integer age;
    private String phone;
}
