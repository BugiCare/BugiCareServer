package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.service.impl.FurnitureGraphService;
import hsu.bugicare.bugicareserver.service.impl.UserStatusGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GraphController {

    private final FurnitureGraphService furnitureGraphService;
    private final UserStatusGraphService userStatusGraphService;

    private String oldRefrigeratorStatus = "closeRefrigerator";
    private String oldDoorStatus = "closeDoor";
    private String fallenStatus = "false";


    @Autowired
    public GraphController(FurnitureGraphService furnitureGraphService, UserStatusGraphService userStatusGraphService) {
        this.furnitureGraphService = furnitureGraphService;
        this.userStatusGraphService = userStatusGraphService;
    }

    /* 가구 문 열림 횟수(하루, 일주일, 한 달 단위) */
    // date 값 = day, week, month
    // furniture 값 = refrigerator, door
    // 일주일 or 한 달 동안의 문 열림 횟수
    @GetMapping("/count/{date}/{furniture}")
    public List<String> getWeekOrMonthCount(@PathVariable String date, @PathVariable String furniture) {
        return furnitureGraphService.getCount(date, furniture);
    }

    /* 어르신 취침 시간 */
    // date 값 = day, week, month
    @GetMapping("/sleepTime/{date}")
    public List<String> getTime(@PathVariable String date) {
        return userStatusGraphService.getCount(date);
    }

    @GetMapping("/fallen")
    public ResponseEntity<String> getFallen() {
        return ResponseEntity.status(HttpStatus.OK).body(fallenStatus);
    }

    @PostMapping("/result")
    public void postResult(@RequestBody Map<String, Object> data) {
        String result = data.get("result").toString();
        System.out.println(result);

        // Refrigerator
        if(result.contains("openRefrigerator")) {
            if(oldRefrigeratorStatus.equals("closeRefrigerator")) {
                furnitureGraphService.saveRefrigerator();
            }
            oldRefrigeratorStatus = "openRefrigerator";
        } else if (result.contains("closeRefrigerator")) {
            oldRefrigeratorStatus = "closeRefrigerator";
        }

        // Door
        if (result.contains("openDoor")) {
            if (oldDoorStatus.equals("closeDoor")) {
                furnitureGraphService.saveDoor();
            }
            oldDoorStatus = "openDoor";
        } else if (result.contains("closeDoor")) {
            oldDoorStatus = "closeDoor";
        }

        // 넘어짐
        if (result.contains("fallenPerson")) {
            fallenStatus = "true";
        } else {
            fallenStatus = "false";
        }
    }
}
