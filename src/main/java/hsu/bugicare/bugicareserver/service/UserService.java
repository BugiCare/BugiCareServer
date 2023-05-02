package hsu.bugicare.bugicareserver.service;

import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto findUser(Long id);
    List<UserResponseDto> findAllUser();
    List<UserResponseDto> findPageUser(int page, int offset);
    UserResponseDto saveUser(UserDto userDto) throws Exception;
}
