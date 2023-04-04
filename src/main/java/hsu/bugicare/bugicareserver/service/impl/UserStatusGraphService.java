package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String getTime(String date, String status) {
        return null;
    }
}
