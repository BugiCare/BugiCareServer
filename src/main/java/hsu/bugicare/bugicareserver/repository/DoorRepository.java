package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoorRepository extends JpaRepository<Door, Long>{

    /**
     * 라즈베리파이 28분 동안 돌려서 Data 저장 후 그래프 일별/주별/월별 잘 나오는지 Test 필요
     * 하루 = 1분
     * 일주일 = 7분
     * 한 달 = 28분
     */

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분의 레코드들만 가져오기
    @Query("SELECT d FROM Door d WHERE DATE(d.time) = CURRENT_DATE AND HOUR(d.time) = HOUR(CURRENT_TIMESTAMP) AND MINUTE(d.time) = MINUTE(CURRENT_TIMESTAMP)")
    List<Door> findSameDay();

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 현재 - num 분 전의 레코드만 가져오기
    @Query("SELECT w FROM Door w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND EXTRACT(HOUR FROM w.time) = EXTRACT(HOUR FROM CURRENT_TIMESTAMP) " +
            "AND EXTRACT(MINUTE FROM w.time) = :num")
    List<Door> findWeekOrMonthOver(@Param("num") int num);


    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 이전 시, 현재 - num 분 전의 레코드만 가져오기
    // 정각이 지났을 경우
    @Query("SELECT w FROM Door w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND EXTRACT(HOUR FROM w.time) = EXTRACT(HOUR FROM CURRENT_TIMESTAMP) - 1 " +
            "AND EXTRACT(MINUTE FROM w.time) = :num")
    List<Door> findWeekOrMonthUnder(@Param("num") int num);


    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 이전 일, 이전 시, 현재 - num 분 전의 레코드만 가져오기
    @Query("SELECT w FROM Door w WHERE EXTRACT(DATE FROM w.time) = EXTRACT(DATE FROM CURRENT_TIMESTAMP) - 1 " +
            "AND EXTRACT(HOUR FROM w.time) = 23" +
            "AND EXTRACT(MINUTE FROM w.time) = :num")
    List<Door> findWeekOrMonthChangeDay(@Param("num") int num);
}
