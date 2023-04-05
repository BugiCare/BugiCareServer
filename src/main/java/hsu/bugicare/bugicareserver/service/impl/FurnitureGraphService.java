package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Door;
import hsu.bugicare.bugicareserver.domain.Refrigerator;
import hsu.bugicare.bugicareserver.repository.DoorRepository;
import hsu.bugicare.bugicareserver.repository.RefrigeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FurnitureGraphService {
    private final RefrigeratorRepository refriRepository;
    private final DoorRepository doorRepository;

    private int nowMinute;
    private int nowHour;

    @Autowired
    public FurnitureGraphService(RefrigeratorRepository refriRepository, DoorRepository doorRepository) {
        this.refriRepository = refriRepository;
        this.doorRepository = doorRepository;
    }

    public String getDayCount(String furniture) {

        // 현재 시,분 알아오기
        nowMinute = LocalTime.now().getMinute();
        nowHour = LocalTime.now().getHour();

        // 하루동안의 냉장고 문 열림 횟수
        if (furniture.equals("refrigerator")) {
            List<Refrigerator> refrigerator = refriRepository.findSameDay();
            return String.valueOf(refrigerator.size());
        }
        // 하루동안의 현관문 열림 횟수
        else if (furniture.equals("door")) {
            List<Door> door = doorRepository.findSameDay();
            return String.valueOf(door.size());
        }
        // 오류 처리
        return "ERROR";
    }

    public List<String> getWeekOrMonthCount(String date, String furniture) {

        // 현재 시,분 알아오기
        nowMinute = LocalTime.now().getMinute();
        nowHour = LocalTime.now().getHour();

        // 주, 월 구분 변수, 일주일 = 7분, 한 달 = 28분 이므로
        int num = (date.equals("week")) ? 6 : 27;

        // 반환할 String 배열, weekCount는 7개의 인자를, monthCount는 28개의 인자를 가진 String 배열이다.
        List<String> result = new ArrayList<>();

        // 일주일 or 한 달 동안의 냉장고 문 열림 횟수
        if(furniture.equals("refrigerator")){
            List<Refrigerator> refrigerator;

            // 주 or 월 이냐에 따라서 배열이 생성
            for(int i = 0; i <= num; i++) {
                if(nowMinute - i >= 0) {
                    refrigerator = refriRepository.findWeekOrMonthOver(nowMinute - i);
                }

                // num 분 미만일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                else {
                    // 12시일 경우 일/시 모두 변경
                    if(nowHour == 0) {
                        refrigerator = refriRepository.findWeekOrMonthChangeDay(60 + nowMinute - i);
                    }
                    // 아닌 경우 시(Hour)만 변경
                    else {
                        refrigerator = refriRepository.findWeekOrMonthUnder(60 + nowMinute - i);
                    }
                }
                // 각 분마다의 OPEN 값의 개수를 세서 result 배열에 추가. 없으면 0 추가
                result.add(String.valueOf(refrigerator.size()));
            }
            // 배열 반환
            return result;
        }
        // 일주일 or 한 달 동안의 현관문 열림 횟수
        else if(furniture.equals("door")){
            List<Door> door;

            // 주 or 월 이냐에 따라서 배열이 생성
            for(int i = 0; i <= num; i++) {
                if(nowMinute - i >= 0) {
                    door = doorRepository.findWeekOrMonthOver(nowMinute - i);
                }

                // num 분 미만일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                else {
                    // 12시일 경우 일/시 모두 변경
                    if(nowHour == 12) {
                        door = doorRepository.findWeekOrMonthChangeDay(60 + nowMinute - i);
                    }
                    // 아닌 경우 시(Hour)만 변경
                    else {
                        door = doorRepository.findWeekOrMonthUnder(60 + nowMinute - i);
                    }
                }
                // 각 분마다의 OPEN 값의 개수를 세서 result 배열에 추가. 없으면 0 추가
                result.add(String.valueOf(door.size()));
            }
            // 배열 반환
            return result;
        }
        // NULL
        return result;
    }
}
