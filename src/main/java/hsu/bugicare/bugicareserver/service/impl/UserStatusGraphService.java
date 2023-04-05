package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusGraphService {
    private final UserStatusRepository userStatusRepository;

    private int nowMinute;
    private int nowHour;

    @Autowired
    public UserStatusGraphService(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    // 구현 예정
    public String getDayTime(String status) {
        return null;
    }

    // 구현 예정
    public List<String> getWeekOrMonthTime(String date, String status) {
        return null;
    }
}
