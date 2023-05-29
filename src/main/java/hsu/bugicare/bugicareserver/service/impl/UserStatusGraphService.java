package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Sleep;
import hsu.bugicare.bugicareserver.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusGraphService {
    private final UserStatusRepository userStatusRepository;

//    private int nowSecond;
//    private int nowMinute;
//    private int nowHour;
//    private int mMinus;
//    private int dateNum;
//    private int monthSum;
//    private int n, m, sum, flag;

    private int nowSecond;
    private int nowMinute;
    private int nowHour;
    private final int minusNum = -1;
    private int dateNum;
    private int monthSum;
    private int n, m, sum;

    @Autowired
    public UserStatusGraphService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    // date 값 = day, week, month
    // 하루 or 일주일 or 한 달 동안의 어르신 취침시간
    public List<String> getCount(String date) {
        List<Sleep> sleep = new ArrayList<>();

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

        // dateNum = 주, 월 구분 변수, 일주일 = 7, 한 달 = 4 개의 배열 생성
        // 1시간 = 5초, 6시간 = 30초, 하루 = 120초

        // 현재(1시간 내)의 어르신 수면 여부. 1이면 취침 0이면 활동 중
        if(date.equals("day")) {

            sleep = userStatusRepository.findDay();
            dayResult.add(String.valueOf(sleep.size() != 0 ? 1 : 0));

            // 배열 반환 (값이 하나밖에 없지만 API 반환값 통일성을 위해)
            return dayResult;
        }
        else {
//            dateNum = (date.equals("week") ? 7 : 28);
//            n = nowSecond / 5;
//            mMinus = 0;
//
//            System.out.println("nowHour : " + nowHour);
//            System.out.println("nowMinute : " + nowMinute);
//            System.out.println("nowSecond : " + nowSecond);
//
//            // 주 or 월 이냐에 따라서 배열이 생성
//            for(int i = 0; i <= dateNum * 24; i++) {
//                System.out.println(i + "번 째");
//                m = (n*5 - i*5 < 0) ? (60 + n*5 - i*5)  : (n*5 - i*5);
//
//                while(m < 0) {
//                    m += 60;
//                }
//                if (m == 55) {
//                    mMinus ++;
//                }
//
//                if(i == 0){
//                    System.out.println("0 : " + nowMinute + "분 " + m + "초 부터 " + nowSecond + "초 까지");
//                    sleep = userStatusRepository.findWeekOrMonth(m, nowSecond, 0, 0);
//                }
//                else if(n*5 - i*5 < 0 && (nowMinute - mMinus) >= 0) {
//                    System.out.println("1 : " + (nowMinute - mMinus) + "분 " + m + "초 부터 " + (m + 4) + "초 까지");
//                    sleep = userStatusRepository.findWeekOrMonth(m, m + 4, -mMinus, 0);
//                }
//                // 구간이 이전 시(Hour)로 넘어갈 경우
//                else if(nowMinute - mMinus < 0) {
//                    System.out.println("2 : " + (nowHour - 1) + "시 " + (60 + nowMinute - mMinus) + "분 " + m + "초 부터 " + (m + 4) + "초 까지");
//                    sleep = userStatusRepository.findWeekOrMonth(m, m + 4, 60 - mMinus, -1);
//                }
//                else{
//                    System.out.println("3 : " + nowMinute + "분 " + m + "초 부터 " + (m + 4) + "초 까지");
//                    sleep = userStatusRepository.findWeekOrMonth(m, m + 4, 0, 0);
//                }
//
//                // 5초마다(1시간)의 구간에서 데이터가 존재하면 1 아니면 0
//                sum += (sleep.size() != 0 ? 1 : 0);
//                System.out.println("1시간 동안의 총 취침 시간 : " + (sleep.size() != 0 ? 1 : 0));
//
//                // 하루 = 2분이므로 00분 ~ 1분, 2분 ~ 3분으로 나누기 때문에
//                // 현재 NN시 3분이라면 00분 ~ 1분, 2분 ~ 3분 각을 더해서각반환,
//                // 현재 NN시 2분이라면 00분 ~ 1분, 2분 각각을 더해서 반환
//                // 즉, 현재 짝수 분이라면 현재 분의 열림 횟수만 반환
//                // 현재 홀수 분이라면 전 짝수 분까지의 열림 횟수를 더해서 반환
//                // 하루 동안의 횟수를 배열에 삽입. 또한 monthSum에 각각의 요일에 속하는 값을 + 한다.
//                if(i != 0 && (nowMinute - mMinus) % 2 == 0 && m == 0) {
//                    System.out.println("++++++++++++하루 동안의 총 취침 시간 : " + sum + "++++++++++++");
//                    weekResult.add(String.valueOf(sum));
//                    monthSum += sum;
//                    sum = 0;
//                    flag ++;
//                }
//                // 7일(일주일)이 지날 때마다 월(Month) 배열에 삽입
//                if(i != 0 && flag == 7 && date.equals("month")){
//                    System.out.println("++++++++++++일주일 동안의 총 취침 시간 : " + monthSum + "++++++++++++");
//                    monthResult.add(String.valueOf(monthSum));
//                    sum = 0;
//                    flag = 0;
//                    monthSum = 0;
//                }
//            }
//            // 배열 반환
//            if(date.equals("week")){
//                return weekResult;
//            }
//            else{
//                return monthResult;
//            }
            dateNum = (date.equals("week") ? 7 : 14);

            // 주 or 월 이냐에 따라서 배열이 생성
            for(int i = 1; i <= (dateNum * 2); i++) {
                // NN시 1분 이상일 경우
                if((nowMinute - i + 1)>= 0) {
                    sleep = userStatusRepository.findWeekOrMonth(nowMinute - i + 1, 0);
                }

                // NN시 1분 미만일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                else {
                    // 12시일 경우 일/시 모두 변경
                    if(nowHour == 0) {
                        sleep = userStatusRepository.findWeekOrMonthAndChangeDay(60 + nowMinute - i + 1);
                    }
                    // 아닌 경우 시(Hour)만 변경
                    else {
                        sleep = userStatusRepository.findWeekOrMonth(60 + nowMinute - i + 1, minusNum);
                    }
                }

                sum += sleep.size();

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
    }
}