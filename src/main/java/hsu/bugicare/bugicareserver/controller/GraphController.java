package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.service.impl.FurnitureGraphService;
import hsu.bugicare.bugicareserver.service.impl.UserStatusGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    // furniture 값 = refrigerator, door
    // 하루동안의 문 열림 횟수, 60초를 시간대별로 나누기 애매해서 하루에 몇 번 열었는지 String 값으로만 반환. 배열 X
    @GetMapping("/countDay/{furniture}")
    public String getDayCount( @PathVariable String furniture) {
        return furnitureGraphService.getDayCount(furniture);
    }

    // date 값 = week, month
    // furniture 값 = refrigerator, door
    // 일주일 or 한 달 동안의 문 열림 횟수
    @GetMapping("/countWeekOrMonth/{date}/{furniture}")
    public List<String> getWeekOrMonthCount(@PathVariable String date, @PathVariable String furniture) {
        return furnitureGraphService.getWeekOrMonthCount(date, furniture);
    }


    /* 어르신 상태 */

    // status 값 = sleep, active
    @GetMapping("/timeDay/{status}")
    public String getTime( @PathVariable String status) {
        return userStatusGraphService.getDayTime(status);
    }

    // date 값 = week, month
    // status 값 = sleep, active
    @GetMapping("/timeWeekOrMonth/{date}/{status}")
    public List<String> getTime(@PathVariable String date, @PathVariable String status) {
        return userStatusGraphService.getWeekOrMonthTime(date, status);
    }

}
