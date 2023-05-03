package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.dto.ManagerResponseDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.service.ManagerService;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ManagerController {
    private final ManagerService managerService;
    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/allManager")
    public List<ManagerResponseDto> getAllManager() {
        return managerService.findAllManager();
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<ManagerResponseDto> getManager(@PathVariable Long id) {
        System.out.println(id);
        ManagerResponseDto managerResponseDto = managerService.findManager(id);
        return ResponseEntity.ok().body(managerResponseDto);
    }
    @GetMapping("/myUser")
    public List<UserResponseDto> getMyUser(@RequestParam Long id) {
        return managerService.findMyUser(id);
    }
}
