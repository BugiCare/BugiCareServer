package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.dto.ManagerResponseDto;
import hsu.bugicare.bugicareserver.dto.UserResponseDto;
import hsu.bugicare.bugicareserver.service.ManagerService;
import hsu.bugicare.bugicareserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class ManagerController {

    private final ManagerService managerService;
    private static final String IMAGES_PATH = "../../src/main/resources/images";

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

    @GetMapping(value = "/logo", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getLogo() throws IOException {
        Path imagePath = Paths.get(IMAGES_PATH, "bugicare_logo.png");
        Resource resource = new UrlResource(imagePath.toUri());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @GetMapping(value = "/manager_image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> getManagerImage() throws IOException {
        Path imagePath = Paths.get(IMAGES_PATH, "manager_khs.png");
        Resource resource = new UrlResource(imagePath.toUri());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

}
