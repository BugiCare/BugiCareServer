package hsu.bugicare.bugicareserver.service;

import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto findUser(Long id);
    List<UserResponseDto> findAllUser();
    UserResponseDto saveUser(UserDto userDto);
}
