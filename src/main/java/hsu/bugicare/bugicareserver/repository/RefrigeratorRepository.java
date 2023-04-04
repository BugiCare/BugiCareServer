package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long>{

    /**
     * 라즈베리파이 28분 동안 돌려서 Data 저장 후 그래프 일별/주별/월별 잘 나오는지 Test 필요
     * 하루 = 1분
     * 일주일 = 7분
     * 한 달 = 28분
     */

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분의 레코드들만 가져오기
    @Query("SELECT d FROM Refrigerator d WHERE DATE(d.time) = CURRENT_DATE AND HOUR(d.time) = HOUR(CURRENT_TIMESTAMP) AND MINUTE(d.time) = MINUTE(CURRENT_TIMESTAMP)")
    List<Refrigerator> findSameDay();

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분 ~ num 분 전의 레코드들만 가져오기
    @Query("SELECT w FROM Refrigerator w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND HOUR(w.time) = HOUR(CURRENT_TIMESTAMP) " +
            "AND MINUTE(w.time) BETWEEN (MINUTE(CURRENT_TIMESTAMP) - :num) AND MINUTE(CURRENT_TIMESTAMP)")
    List<Refrigerator> findWeekOrMonthOver(@Param("num") int num);

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 이전 시 ~ 같은 시, 같은 분 ~ num 분 전의 레코드들만 가져오기
    @Query("SELECT w FROM Refrigerator w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND (HOUR(w.time)*60 + MINUTE(w.time)) BETWEEN ((HOUR(CURRENT_TIMESTAMP)*60 + MINUTE(CURRENT_TIMESTAMP)) - :num) " +
            "AND (HOUR(CURRENT_TIMESTAMP)*60 + MINUTE(CURRENT_TIMESTAMP))")
    List<Refrigerator> findWeekOrMonthUnder(@Param("num") int num);


    // 아직 테스트는 못해봄
    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 이전 일 ~ 같은 일, 이전 시 ~ 같은 시, 같은 분 ~ num 분 전의 레코드들만 가져오기
    @Query("SELECT w FROM Refrigerator w WHERE DATE(w.time) BETWEEN DATE(CURRENT_DATE - 1) AND DATE(CURRENT_DATE) " +
            "AND HOUR(w.time) BETWEEN CASE WHEN HOUR(CURRENT_TIMESTAMP) = 0 THEN 23 ELSE HOUR(CURRENT_TIMESTAMP) - 1 END AND 23 " +
            "AND MINUTE(w.time) BETWEEN (MINUTE(CURRENT_TIMESTAMP) - :num) AND 59 " +
            "OR (HOUR(w.time) = 0 AND MINUTE(w.time) <= (MINUTE(CURRENT_TIMESTAMP) - :num))")
    List<Refrigerator> findWeekOrMonthChangeDay(@Param("num") int num);
}
