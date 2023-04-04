package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.User;
import hsu.bugicare.bugicareserver.domain.UserImage;
import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.repository.UserImageRepository;
import hsu.bugicare.bugicareserver.repository.UserRepository;
import hsu.bugicare.bugicareserver.service.FileHandler;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    private final FileHandler fileHandler;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserImageRepository userImageRepository,
                           FileHandler fileHandler) {
        this.userRepository = userRepository;
        this.userImageRepository = userImageRepository;
        this.fileHandler = fileHandler;
    }

    @Override
    public UserResponseDto findUser(Long id) {
        User user = userRepository.findById(id).get();
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .gender(user.getGender())
                .address(user.getAddress())
                .age(user.getAge())
                .phone(user.getPhone())
                .build();

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> findAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = userList.stream()
                .map(m -> UserResponseDto.builder().build().UsertoUserResponseDto(m))
                .collect(Collectors.toList());

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto saveUser(UserDto userDto) throws Exception {
        User user = User.builder()
                .name(userDto.getName())
                .gender(userDto.getGender())
                .address(userDto.getAddress())
                .age(userDto.getAge())
                .phone(userDto.getPhone())
                .build();

        UserImage userImage = fileHandler.parseFileInfo(userDto.getImage());

        User savedUser = userRepository.save(user);
        userImageRepository.save(userImage);

        return UserResponseDto.builder().build().UsertoUserResponseDto(savedUser);
    }
}
