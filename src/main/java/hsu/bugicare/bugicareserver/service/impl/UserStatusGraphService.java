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

    private int nowSecond;
    private int nowMinute;
    private int nowHour;

    private int mMinus;

    private int dateNum;

    private int n, m;

    @Autowired
    public UserStatusGraphService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    // date 값 = day, week, month
    // 하루 or 일주일 or 한 달 동안의 어르신 취침시간
    public List<String> getCount(String date) {
        List<Sleep> sleep = new ArrayList<>();

        // 반환할 String 배열, dayCount는 1개의 인자를, weekCount는 7개의 인자를, monthCount는 28개의 인자를 가진 String 배열이다.
        List<String> result = new ArrayList<>();
        int s = 0;

        // 현재 시, 분, 초 알아오기
        nowSecond = LocalTime.now().getSecond();
        nowMinute = LocalTime.now().getMinute();
        nowHour = LocalTime.now().getHour();

        // dateNum = 주, 월 구분 변수, 일주일 = 7, 한 달 = 28 개의 배열 생성
        // 1시간 = 5초, 6시간 = 30초, 하루 = 120초

        // 현재(1시간 내)의 어르신 수면 여부. 1이면 취침 0이면 활동 중
        if(date.equals("day")) {

            sleep = userStatusRepository.findDay();
            result.add(String.valueOf(sleep.size() != 0 ? 1 : 0));

            // 배열 반환 (값이 하나밖에 없지만 API 반환값 통일성을 위해)
            return result;
        }
        else {
            dateNum = (date.equals("week") ? 7 : 28);
            n = nowSecond / 5;
            mMinus = 0;

            System.out.println("nowHour : " + nowHour);
            System.out.println("nowMinute : " + nowMinute);
            System.out.println("nowSecond : " + nowSecond);
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

            // 주 or 월 이냐에 따라서 배열이 생성
            for(int i = 0; i <= dateNum * 24; i++) {
                System.out.println("배열 " + i + "번 째");
                m = (n*5 - i*5 < 0) ? (60 + n*5 - i*5)  : (n*5 - i*5);

                while(m < 0) {
                    m += 60;
                }
                if (m == 55) {
                    mMinus ++;
                }

                if(i == 0){
                    System.out.println("0 : " + nowMinute + "분 " + m + "초 부터 " + nowSecond + "초 까지");
                    sleep = userStatusRepository.findWeekOrMonth(m, nowSecond, 0, 0);
                }
                else if(n*5 - i*5 < 0 && (nowMinute - mMinus) >= 0) {
                    System.out.println("1 : " + (nowMinute - mMinus) + "분 " + m + "초 부터 " + (m + 4) + "초 까지");
                    sleep = userStatusRepository.findWeekOrMonth(m, m + 4, -mMinus, 0);
                }
                else if(nowMinute - mMinus < 0) {
                    System.out.println("2 : " + (nowHour - 1) + "시 " + (60 + nowMinute - mMinus) + "분 " + m + "초 부터 " + (m + 4) + "초 까지");
                    sleep = userStatusRepository.findWeekOrMonth(m, m + 4, -mMinus, -1);
                }
                else{
                    System.out.println("3 : " + nowMinute + "분 " + m + "초 부터 " + (m + 4) + "초 까지");
                    sleep = userStatusRepository.findWeekOrMonth(m, m + 4, 0, 0);
                }

                s += (sleep.size() != 0 ? 1 : 0);
                System.out.println("1시간 동안의 총 취침 시간 : " + (sleep.size() != 0 ? 1 : 0));

                if(i != 0 && (nowMinute - mMinus) % 2 == 0 && m == 0) {
                    System.out.println("하루 동안의 총 취침 시간 : " + s);
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
                    result.add(String.valueOf(s));
                    s = 0;
                }
            }
            // 배열 반환
            return result;
        }
    }
}