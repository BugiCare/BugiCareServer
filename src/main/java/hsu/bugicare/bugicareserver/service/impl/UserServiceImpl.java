package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.User;
import hsu.bugicare.bugicareserver.dto.UserDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.repository.UserRepository;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto findUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .gender(user.getGender())
                .address(user.getAddress())
                .age(user.getAge())
                .phone(user.getPhone())
                .manager_id(user.getManager().getId())
                .build();
    }

    @Override
    public List<UserResponseDto> findAllUser() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(m -> {
                    try {
                        return UserResponseDto.builder().build().UsertoUserResponseDto(m);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<UserResponseDto> findPageUser(int page, int offset) {
        List<User> allUser = userRepository.findAll();
        List<User> pageUser = new ArrayList<>();
        for(int i = (page - 1) * offset; i < ((page - 1) * offset) + offset && i < allUser.size(); i++) {
            pageUser.add(allUser.get(i));
        }
        List<UserResponseDto> userResponseDtoList = pageUser.stream().map(m -> {
            try {
                return UserResponseDto.builder().build().UsertoUserResponseDto(m);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

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

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder().build().UsertoUserResponseDto(savedUser);
    }
}
