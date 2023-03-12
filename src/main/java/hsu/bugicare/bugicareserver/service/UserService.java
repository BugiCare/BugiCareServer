package hsu.bugicare.bugicareserver.service;

import hsu.bugicare.bugicareserver.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto getUser(Long id);
    List<UserResponseDto> getAllUser();
}
