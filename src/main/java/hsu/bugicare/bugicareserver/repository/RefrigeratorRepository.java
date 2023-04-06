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
     * 하루 = 2분
     * 일주일 = 14분
     * 한 달 = 56분
     */

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분의 레코드들만 가져오기
    // 초단위로 수정 예정
    @Query("SELECT d FROM Refrigerator d WHERE DATE(d.time) = CURRENT_DATE AND HOUR(d.time) = HOUR(CURRENT_TIMESTAMP) AND MINUTE(d.time) = MINUTE(CURRENT_TIMESTAMP)")
    List<Refrigerator> findDay();

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 이전 시, num 분의 레코드만 가져오기
    @Query("SELECT w FROM Refrigerator w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND EXTRACT(HOUR FROM w.time) = EXTRACT(HOUR FROM CURRENT_TIMESTAMP) + :minus " +
            "AND EXTRACT(MINUTE FROM w.time) = :num")
    List<Refrigerator> findWeekOrMonth(@Param("num") int num, @Param("minus") int minus);

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 이전 일, 이전 시, num 분의 레코드만 가져오기
    @Query("SELECT w FROM Refrigerator w WHERE EXTRACT(DATE FROM w.time) = EXTRACT(DATE FROM CURRENT_TIMESTAMP) - 1 " +
            "AND EXTRACT(HOUR FROM w.time) = 23" +
            "AND EXTRACT(MINUTE FROM w.time) = :num")
    List<Refrigerator> findWeekOrMonthAndChangeDay(@Param("num") int num);
}
