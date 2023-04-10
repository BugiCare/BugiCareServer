package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Door;
import hsu.bugicare.bugicareserver.domain.Refrigerator;
import hsu.bugicare.bugicareserver.repository.DoorRepository;
import hsu.bugicare.bugicareserver.repository.RefrigeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FurnitureGraphService {
    private final RefrigeratorRepository refriRepository;
    private final DoorRepository doorRepository;

    private int nowSecond;
    private int nowMinute;
    private int nowHour;

    private final int minusNum = -1;

    private int dateNum;

    private int n, m;

    @Autowired
    public FurnitureGraphService(RefrigeratorRepository refriRepository, DoorRepository doorRepository) {
        this.refriRepository = refriRepository;
        this.doorRepository = doorRepository;
    }

    // date 값 = day, week, month
    // furniture 값 = refrigerator, door
    // 일주일 or 한 달 동안의 문 열림 횟수
    public List<String> getCount(String date, String furniture) {

        List<Refrigerator> refrigerator = null;
        List<Door> door = null;

        // 반환할 String 배열, dayCount는 6개의 인자를, weekCount는 7개의 인자를, monthCount는 28개의 인자를 가진 String 배열이다.
        List<String> result = new ArrayList<>();
        int s = 0;

        // 현재 시, 분, 초 알아오기
        nowSecond = LocalTime.now().getSecond();
        nowMinute = LocalTime.now().getMinute();
        nowHour = LocalTime.now().getHour();

        // 주, 월, 일 구분 변수, 하루 = 6, 일주일 = 7, 한 달 = 28 개의 배열 생성
        if(date.equals("day")) {
            dateNum = 6;
            n = nowSecond / 5;
            // 하루(현재부터 6시간 전까지만)의 냉장고 문 열림 횟수
            // 초단위로 구현 예정
            // 하루 = 120초, 1시간 = 5초, 6시간 = 30초
            if(furniture.equals("refrigerator")){

                // 하루(현재부터 6시간 전까지만)의 배열이 생성
                for(int i = 0; i < dateNum; i++) {
                    m = (n*5 - i*5 < 0) ? (60 + n*5 - i*5)  : (n*5 - i*5);

                    if(i == 0){
                        refrigerator = refriRepository.findDay(m, nowSecond, 0);
                    }
                    if(n*5 - i*5 < 0) {
                        refrigerator = refriRepository.findDay(m, m + 4, -1);
                    }
                    else{
                        refrigerator = refriRepository.findDay(m, m + 4, 0);
                    }
                    result.add(String.valueOf(refrigerator.size()));
                }

                // 배열 반환
                return result;
            }
            // 하루(현재부터 6시간 전까지만)의 현관문 열림 횟수
            else if(furniture.equals("door")){
                // 하루(현재부터 6시간 전까지만)의 배열이 생성
                for(int i = 0; i < dateNum; i++) {
                    m = (n*5 - i*5 < 0) ? (60 + n*5 - i*5)  : (n*5 - i*5);

                    if(i == 0){
                        door = doorRepository.findDay(m, nowSecond, 0);
                    }
                    if(n*5 - i*5 < 0) {
                        door = doorRepository.findDay(m, m + 4, -1);
                    }
                    else{
                        door = doorRepository.findDay(m, m + 4, 0);
                    }
                    result.add(String.valueOf(door.size()));
                }
                // 배열 반환
                return result;
            }
        }
        else {
            dateNum = (date.equals("week") ? 7 : 28);

            // 일주일 or 한 달 동안의 냉장고 문 열림 횟수
            if(furniture.equals("refrigerator")){
                // 주 or 월 이냐에 따라서 배열이 생성
                for(int i = 1; i <= (dateNum * 2); i++) {
                    // NN시 1분 이상일 경우
                    if(nowMinute - i >= 0) {
                        refrigerator = refriRepository.findWeekOrMonth(nowMinute - i + 1, 0);
                    }

                    // NN시 1분 미만일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                    else {
                        // 12시일 경우 일/시 모두 변경
                        if(nowHour == 0) {
                            refrigerator = refriRepository.findWeekOrMonthAndChangeDay(60 + nowMinute - i + 1);
                        }
                        // 아닌 경우 시(Hour)만 변경
                        else {
                            refrigerator = refriRepository.findWeekOrMonth(60 + nowMinute - i + 1, minusNum);
                        }
                    }

                    s += refrigerator.size();

                    // 하루 = 2분이므로 00분 ~ 1분, 2분 ~ 3분으로 나누기 때문에
                    // 현재 NN시 3분이라면 00분 ~ 1분, 2분 ~ 3분 각을 더해서각반환,
                    // 현재 NN시 2분이라면 00분 ~ 1분, 2분 각각을 더해서 반환
                    // 즉, 현재 짝수 분이라면 현재 분의 열림 횟수만 반환
                    // 현재 홀수 분이라면 전 짝수 분까지의 열림 횟수를 더해서 반환
                    if((nowMinute - i + 1) % 2 == 0) {
                        result.add(String.valueOf(s));
                        s = 0;
                    }
                }
                // 배열 반환
                return result;
            }
            // 일주일 or 한 달 동안의 현관문 열림 횟수
            else if(furniture.equals("door")){
                // 주 or 월 이냐에 따라서 배열이 생성
                for(int i = 1; i <= dateNum; i++) {
                    // NN시 1분 이상일 경우
                    if(nowMinute - i >= 0) {
                        door = doorRepository.findWeekOrMonth(nowMinute - i + 1, 0);
                    }

                    // NN시 1분 미만일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                    else {
                        // 12시일 경우 일/시 모두 변경
                        if(nowHour == 0) {
                            door = doorRepository.findWeekOrMonthAndChangeDay(60 + nowMinute - i + 1);
                        }
                        // 아닌 경우 시(Hour)만 변경
                        else {
                            door = doorRepository.findWeekOrMonth(60 + nowMinute - i + 1, minusNum);
                        }
                    }

                    s += door.size();

                    // 하루 = 2분이므로 00분 ~ 1분, 2분 ~ 3분으로 나누기 때문에
                    // 현재 NN시 3분이라면 00분 ~ 1분, 2분 ~ 3분 각각을 더해서 반환,
                    // 현재 NN시 2분이라면 00분 ~ 1분, 2분 각각을 더해서 반환
                    // 즉, 현재 짝수 분이라면 현재 분의 열림 횟수만 반환
                    // 현재 홀수 분이라면 전 짝수 분까지의 열림 횟수를 더해서 반환
                    if((nowMinute - i + 1) % 2 == 0) {
                        result.add(String.valueOf(s));
                        s = 0;
                    }
                }
                // 배열 반환
                return result;
            }
        }
        // NULL --> day, week, month 아닐 경우
        return result;
    }

    public void saveDoor() {
        Door door = Door.builder()
                .status("openDoor")
                .build();

        doorRepository.save(door);
    }

    public void saveRefrigerator() {
        Refrigerator refrigerator = Refrigerator.builder()
                .status("openRefrigerator")
                .build();

        refriRepository.save(refrigerator);
    }
}
