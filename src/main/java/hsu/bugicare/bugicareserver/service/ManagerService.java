package hsu.bugicare.bugicareserver.service;

import hsu.bugicare.bugicareserver.dto.ManagerResponseDto;

import java.util.List;

public interface ManagerService {
    ManagerResponseDto findManager(Long id);
    List<ManagerResponseDto> findAllManager();
}
