package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.service.impl.FurnitureGraphService;
import hsu.bugicare.bugicareserver.service.impl.UserStatusGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GraphController {

    private final FurnitureGraphService furnitureGraphService;
    private final UserStatusGraphService userStatusGraphService;

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

    /* 어르신 상태 */
    // date 값 = day, week, month
    @GetMapping("/sleepTime/{date}")
    public List<String> getTime(@PathVariable String date, @PathVariable String status) {
        return userStatusGraphService.getWeekOrMonthTime(date, status);
//        return userStatusGraphService.getCount(date, furniture);
    }

    @PostMapping("/result")
    public void postResult(@RequestBody String result) {
        System.out.println(result);
    }
}
