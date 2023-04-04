package hsu.bugicare.bugicareserver.service.impl;

import hsu.bugicare.bugicareserver.domain.Door;
import hsu.bugicare.bugicareserver.domain.Refrigerator;
import hsu.bugicare.bugicareserver.repository.DoorRepository;
import hsu.bugicare.bugicareserver.repository.RefrigeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class GraphService {
    private final RefrigeratorRepository refriRepository;
    private final DoorRepository doorRepository;

    private int nowMinute;
    private int nowHour;

    @Autowired
    public GraphService(RefrigeratorRepository refriRepository, DoorRepository doorRepository) {
        this.refriRepository = refriRepository;
        this.doorRepository = doorRepository;
    }

    public String getCount(String date, String furniture) {
        nowMinute = LocalTime.now().getMinute();
        nowHour = LocalTime.now().getHour();

        if(date.equals("day")){
            if(furniture.equals("refrigerator")){
                List<Refrigerator> refrigerator = refriRepository.findSameDay();
                return String.valueOf(refrigerator.size());
            }
            else if(furniture.equals("door")){
                List<Door> door = doorRepository.findSameDay();
                return String.valueOf(door.size());
            }
            else {
                return "ERROR";
            }
        }
        else if (date.equals("week") || date.equals("month")){
            // 주, 월 구분용도
            int num = (furniture.equals("week")) ? 6 : 23;

            if(furniture.equals("refrigerator")){
                List<Refrigerator> refrigerator;

                if(nowMinute > num) {
                    refrigerator = refriRepository.findWeekOrMonthOver(num);
                }

                // num 분 이하일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                else {
                    // 12시일 경우 일/시 모두 변경
                    if(nowHour == 12) {
                        refrigerator = refriRepository.findWeekOrMonthChangeDay(num);
                    }
                    else {
                        refrigerator = refriRepository.findWeekOrMonthUnder(num);
                    }
                }
                return String.valueOf(refrigerator.size());
            }
            else if(furniture.equals("door")){
                List<Door> door;
                // num 분 이상일 경우
                if(nowMinute > num) {
                    door = doorRepository.findWeekOrMonthOver(num);
                }

                // num 분 이하일 경우 -N분이 되지 않도록 처리 && 시(Hour) 변경
                 else {
                    // 12시일 경우 일/시 모두 변경
                     if(nowHour == 12) {
                        door = doorRepository.findWeekOrMonthChangeDay(num);
                     }
                    else {
                        door = doorRepository.findWeekOrMonthUnder(num);
                    }
                 }
                return String.valueOf(door.size());
            }
        }
        else {
            return "ERROR";
        }
        return "ERROR";
    }
}
