package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Manager;
import hsu.bugicare.bugicareserver.dto.ManagerResponseDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.repository.ManagerRepository;
import hsu.bugicare.bugicareserver.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public ManagerResponseDto findManager(Long id) {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 매니저가 없습니다. id = " + id));
        System.out.println("*************************");
        System.out.println(manager);
        System.out.println("*************************");

        return ManagerResponseDto.builder()
                .id(manager.getId())
                .name(manager.getName())
                .center_name(manager.getCenter_name())
                .phone(manager.getPhone())
                .build();
    }

    @Override
    public List<ManagerResponseDto> findAllManager() {
        List<Manager> managerList = managerRepository.findAll();

        return managerList.stream()
                .map(m -> {
                    try {
                        return ManagerResponseDto.builder().build().ManagertoManagerResponseDto(m);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }



}
