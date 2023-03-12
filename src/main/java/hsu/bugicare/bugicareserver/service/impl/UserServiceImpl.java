package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.User;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.repository.UserRepository;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).get();
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .name(user.getName())
                .gender(user.getGender())
                .address(user.getAddress())
                .age(user.getAge())
                .phone(user.getPhone())
                .build();

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        return null;
    }
}
