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
@CrossOrigin
public class DataController {

    private final DataService dataService;
    private final UserStatusGraphService userStatusGraphService;

    private boolean refrigeratorOpen = false;
    private boolean doorOpen = false;
    private boolean fallen = false;


    @Autowired
    public DataController(DataService dataService, UserStatusGraphService userStatusGraphService) {
        this.dataService = dataService;
        this.userStatusGraphService = userStatusGraphService;
    }

    /* 가구 문 열림 횟수(하루, 일주일, 한 달 단위) */
    // date 값 = day, week, month
    // furniture 값 = refrigerator, door
    @GetMapping("/count/{date}/{furniture}")
    public List<String> getWeekOrMonthCount(@PathVariable String date, @PathVariable String furniture) {
        return dataService.getCount(date, furniture);
    }

    /* 어르신 현재 상태 및 취침 시간 */
    // date 값 = day, week, month
    @GetMapping("/sleepTime/{date}")
    public List<String> getTime(@PathVariable String date) {
        return userStatusGraphService.getCount(date);
    }

    @GetMapping("/fallen")
    public ResponseEntity<String> getFallen() {
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.toString(fallen));
    }

    @PostMapping("/result")
    public void postResult(@RequestBody Map<String, Object> data) {
        String result = data.get("result").toString();
        System.out.println(result);

        // Refrigerator
        if(result.contains("openRefrigerator")) { // 냉장고 문이 열려있는 경우
            if(!refrigeratorOpen) { // 전에 닫혀있던 경우에만 저장
                dataService.saveRefrigerator();
            }
            refrigeratorOpen = true; // 열려있는 상태로 업데이트
        } else if(result.contains("closeRefrigerator")){ // 냉장고 문이 닫혀있는 경우
            refrigeratorOpen = false; // 닫혀있는 상태로 업데이트
        }

        // Door
        if (result.contains("openDoor")) { // 문이 열려있는 경우
            if (!doorOpen) { // 전에 닫혀있던 경우에만 저장
                dataService.saveDoor();
            }
            doorOpen = true; // 열려있는 상태로 업데이트
        } else { // 문이 닫혀있는 경우
            doorOpen = false; // 닫혀있는 상태로 업데이트
        }

        // 넘어짐
        if (result.contains("fallenPerson")) { // 넘어진 경우
            fallen = true;
        } else {
            fallen = false;
        }

        // sleep
        if(result.contains("sleepingPerson")) {
            dataService.saveSleep();
        }
    }

    @GetMapping("/TTS/{id}")
    public ResponseEntity<TTS> getTTS(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(dataService.getTTSContent(id));
    }

    @GetMapping("/allTTS")
    public ResponseEntity<List<TTS>> getAllTTS() {
        return ResponseEntity.status(HttpStatus.OK).body(dataService.getAllTTS());
    }

    @PostMapping("/tts")
    public void saveTTS(@RequestBody String content) {
        dataService.saveTTSContent(new TTS(content));
    }

    @DeleteMapping("/TTS/{id}")
    public void deleteTTS(@PathVariable Long id) {
        dataService.deleteTTS(id);
    }
}
