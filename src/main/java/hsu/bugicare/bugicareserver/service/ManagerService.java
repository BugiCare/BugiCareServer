package hsu.bugicare.bugicareserver.service;

import hsu.bugicare.bugicareserver.dto.ManagerResponseDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;

import java.util.List;

public interface ManagerService {
    ManagerResponseDto findManager(Long id);
    List<ManagerResponseDto> findAllManager();
    List<UserResponseDto> findMyUser(Long id);

}
