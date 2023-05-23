package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.domain.TTS;
import hsu.bugicare.bugicareserver.service.impl.DataService;
import hsu.bugicare.bugicareserver.service.impl.UserStatusGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    private final DataService dataService;
    private final UserStatusGraphService userStatusGraphService;

    private String oldRefrigeratorStatus = "closeRefrigerator";
    private String oldDoorStatus = "closeDoor";
    private String fallenStatus = "false";


    @Autowired
    public DataController(DataService dataService, UserStatusGraphService userStatusGraphService) {
        this.dataService = dataService;
        this.userStatusGraphService = userStatusGraphService;
    }

    /* 가구 문 열림 횟수(하루, 일주일, 한 달 단위) */
    // date 값 = day, week, month
    // furniture 값 = refrigerator, door
    // 일주일 or 한 달 동안의 문 열림 횟수
    @GetMapping("/count/{date}/{furniture}")
    public List<String> getWeekOrMonthCount(@PathVariable String date, @PathVariable String furniture) {
        return dataService.getCount(date, furniture);
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
                dataService.saveRefrigerator();
            }
            oldRefrigeratorStatus = "openRefrigerator";
        } else if (result.contains("closeRefrigerator")) {
            oldRefrigeratorStatus = "closeRefrigerator";
        }

        // Door
        if (result.contains("openDoor")) {
            if (oldDoorStatus.equals("closeDoor")) {
                dataService.saveDoor();
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

        // sleep
        if(result.contains("sleepPerson")) {
            dataService.saveSleep();
        }
    }

    @GetMapping("/TTS")
    public ResponseEntity<TTS> getTTS(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dataService.getTTSContent(id));
    }

    @GetMapping("/allTTS")
    public ResponseEntity<List<TTS>> getAllTTS() {
        return ResponseEntity.status(HttpStatus.OK).body(dataService.getAllTTS());
    }

    @PostMapping("/tts")
    public void saveTTS(String content) {
        dataService.saveTTSContent(new TTS(content));
    }
}
