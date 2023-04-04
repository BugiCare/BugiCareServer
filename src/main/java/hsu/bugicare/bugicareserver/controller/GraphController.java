package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.service.UserService;
import hsu.bugicare.bugicareserver.service.impl.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    // 냉장고 문 열림 횟수(하루 단위)
    @GetMapping("/refrigeratorDayCount")
    public String getRefrigeratorDayCount() {
        return graphService.refrigeratorDayCount();
    }

//    // 냉장고 문 열림 횟수(일주일 단위)
//    @GetMapping("/refriWeekCount")
//    public String getRefriWeekCount() {
//        return graphService
//    }
//
//    // 냉장고 문 열림 횟수(한 달 단위)
//    @GetMapping("/refriMonthCount")
//    public String getRefriMonthCount() {
//        return graphService
//    }
//
//    // 현관문 열림 횟수(하루 단위)
//    @GetMapping("/doorDayCount")
//    public String getDoorDayCount() {
//        return graphService
//    }
//
//    // 현관문 열림 횟수(일주일 단위)
//    @GetMapping("/doorWeekCount")
//    public String getDoorWeekCount() {
//        return graphService
//    }
//
//    // 현관문 열림 횟수(한 달 단위)
//    @GetMapping("/doorMonthCount")
//    public String getDoorMonthCount() {
//        return graphService
//    }

    // 어르신 상태
    // @GetMapping("/")

}
