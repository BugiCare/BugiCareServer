package hsu.bugicare.bugicareserver.controller;

import hsu.bugicare.bugicareserver.service.impl.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    // 가구 문 열림 횟수(하루, 일주일, 한 달 단위)
    // date 값 = day ,week, month
    // furniture 값 = refrigerator, door
    @GetMapping("/count/{date}/{furniture}")
    public String getCount(@PathVariable String date, @PathVariable String furniture) {
        return graphService.getCount(date, furniture);
    }

    // 어르신 상태
    // @GetMapping("/")

}
