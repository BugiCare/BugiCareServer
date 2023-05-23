package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Door;
import hsu.bugicare.bugicareserver.domain.Refrigerator;
import hsu.bugicare.bugicareserver.domain.TTS;
import hsu.bugicare.bugicareserver.repository.DoorRepository;
import hsu.bugicare.bugicareserver.repository.RefrigeratorRepository;
import hsu.bugicare.bugicareserver.repository.TTSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FurnitureGraphService {
    private final RefrigeratorRepository refriRepository;
    private final DoorRepository doorRepository;
    private final TTSRepository ttsRepository;

    private int nowSecond;
    private int nowMinute;
    private int nowHour;
    private final int minusNum = -1;
    private int dateNum;
    private int monthSum;
    private int n, m, sum;

    @Autowired
    public FurnitureGraphService(RefrigeratorRepository refriRepository, DoorRepository doorRepository, TTSRepository ttsRepository) {
        this.refriRepository = refriRepository;
        this.doorRepository = doorRepository;
        this.ttsRepository = ttsRepository;
    }

    // date 값 = day, week, month
    // furniture 값 = refrigerator, door
    // 하루 or 일주일 or 한 달 동안의 문 열림 횟수
    public List<String> getCount(String date, String furniture) {

        List<Refrigerator> refrigerator;
        List<Door> door;

        // 반환할 String 배열, dayResult는 1개의 인자를, weekResult는 7개의 인자를, monthResult는 4개의 인자를 가진 String 배열이다.
        List<String> dayResult = new ArrayList<>();
        List<String> weekResult = new ArrayList<>();
        List<String> monthResult = new ArrayList<>();

        sum = 0;
        monthSum = 0;

        // 현재 시, 분, 초 알아오기
        nowSecond = LocalTime.now().getSecond();
        nowMinute = LocalTime.now().getMinute();
        nowHour = LocalTime.now().getHour();

        // 초단위로 구현 예정
        // 1시간 = 5초, 6시간 = 30초, 하루 = 120초
        // 주, 월, 일 구분 변수
        if(date.equals("day")) {
            dateNum = 6;
            n = nowSecond / 5;

            // 하루(현재부터 6시간 전까지만)의 냉장고 문 열림 횟수
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
                    dayResult.add(String.valueOf(refrigerator.size()));
                }

                // 배열 반환
                return dayResult;
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
                    dayResult.add(String.valueOf(door.size()));
                }
                // 배열 반환
                return dayResult;
            }
        }
        else {
            dateNum = (date.equals("week") ? 7 : 14);

            // 일주일 or 한 달 동안의 냉장고 문 열림 횟수
            if(furniture.equals("refrigerator")){
                // 주 or 월 이냐에 따라서 배열이 생성
                for(int i = 1; i <= (dateNum * 2); i++) {
                    // NN시 1분 이상일 경우
                    if((nowMinute - i + 1)>= 0) {
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

                    sum += refrigerator.size();

                    // 하루 = 2분이므로 00분 ~ 1분, 2분 ~ 3분으로 나누기 때문에
                    // 현재 NN시 3분이라면 00분 ~ 1분, 2분 ~ 3분 각을 더해서각반환,
                    // 현재 NN시 2분이라면 00분 ~ 1분, 2분 각각을 더해서 반환
                    // 즉, 현재 짝수 분이라면 현재 분의 열림 횟수만 반환
                    // 현재 홀수 분이라면 전 짝수 분까지의 열림 횟수를 더해서 반환
                    // 또한 마지막에 홀수분이 남을 경우를 고려하여 마지막 원소도 배열에 추가
                    // 하루 동안의 횟수를 배열에 삽입. 또한 monthSum에 각각의 요일에 속하는 값을 + 한다.
                    if(((nowMinute - i + 1) % 2) == 0 || i == ((dateNum * 2) + 1)) {
                        weekResult.add(String.valueOf(sum));
                        monthSum += sum;
                        sum = 0;
                    }
                    // 7일(일주일)이 지날 때마다 월(Month) 배열에 삽입
                    if (i != 0 && (i % 7) == 0){
                        monthResult.add(String.valueOf(monthSum));
                        monthSum = 0;
                    }
                }
                // 배열 반환
                if(date.equals("week")){
                    return weekResult;
                }
                else {
                    return monthResult;
                }
            }
            // 일주일 or 한 달 동안의 현관문 열림 횟수
            else if(furniture.equals("door")){
                // 주 or 월 이냐에 따라서 배열이 생성
                for(int i = 1; i <= (dateNum * 2); i++) {
                    // NN시 1분 이상일 경우
                    if((nowMinute - i + 1)>= 0) {
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

                    sum += door.size();

                    // 하루 = 2분이므로 00분 ~ 1분, 2분 ~ 3분으로 나누기 때문에
                    // 현재 NN시 3분이라면 00분 ~ 1분, 2분 ~ 3분 각을 더해서각반환,
                    // 현재 NN시 2분이라면 00분 ~ 1분, 2분 각각을 더해서 반환
                    // 즉, 현재 짝수 분이라면 현재 분의 열림 횟수만 반환
                    // 현재 홀수 분이라면 전 짝수 분까지의 열림 횟수를 더해서 반환
                    // 또한 마지막에 홀수분이 남을 경우를 고려하여 마지막 원소도 배열에 추가
                    // 일주일 동안의 횟수를 배열에 삽입. 또한 monthSum에 각각의 요일에 속하는 값을 + 한다.
                    if(((nowMinute - i + 1) % 2) == 0 || i == ((dateNum * 2) + 1)) {
                        weekResult.add(String.valueOf(sum));
                        monthSum += sum;
                        sum = 0;
                    }
                    // 7일(일주일)이 지날 때마다 월(Month) 배열에 삽입
                    if (i != 0 && (i % 7) == 0){
                        monthResult.add(String.valueOf(monthSum));
                        monthSum = 0;
                    }
                }
                // 배열 반환
                if(date.equals("week")){
                    return weekResult;
                }
                else {
                    return monthResult;
                }
            }
        }
        // NULL --> day, week, month 아닐 경우
        return null;
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

    public TTS getTTSContent(Long id) {
        return ttsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 내용이 없습니다. id = " + id));
    }

    public void saveTTSContent(TTS tts) {
        ttsRepository.save(tts);
    }
}
