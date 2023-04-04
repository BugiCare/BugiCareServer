package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long>{

    /**
     * 라즈베리파이 24분 동안 돌려서 Data 저장 후 그래프 일별/주별/월별 잘 나오는지 Test 필요
     * 하루 = 1분
     * 일주일 = 7분
     * 한 달 = 24분
     */

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분의 레코드들만 가져오기
    @Query("SELECT d FROM Refrigerator d WHERE DATE(d.time) = CURRENT_DATE AND HOUR(d.time) = HOUR(CURRENT_TIMESTAMP) AND MINUTE(d.time) = MINUTE(CURRENT_TIMESTAMP)")
    List<Refrigerator> findWithSameDay();


    // 0분에서 -6분 했을 때 54분되는지 확인 필요
    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분 ~ 6분 전(하루가 7분이므로)의 레코드들만 가져오기
    @Query("SELECT w FROM Refrigerator w WHERE DATE(w.time) = CURRENT_DATE AND HOUR(w.time) = HOUR(CURRENT_TIMESTAMP) AND MINUTE(w.time) BETWEEN MINUTE(CURRENT_TIMESTAMP) AND (MINUTE(CURRENT_TIMESTAMP) - 6)")
    List<Refrigerator> findWithSameWeek();

}
