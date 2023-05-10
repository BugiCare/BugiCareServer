package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStatusRepository extends JpaRepository<Sleep, Long> {

    /**
     * 하루 = 2분
     * 일주일 = 14분
     * 한 달 = 56분
     */

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 같은 시, 같은 분, 4초 전 ~ 같은 초 사이의 값 가져오기
    @Query("SELECT w FROM Sleep w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND EXTRACT(HOUR FROM w.time) = EXTRACT(HOUR FROM CURRENT_TIMESTAMP)" +
            "AND EXTRACT(MINUTE FROM w.time) = EXTRACT(MINUTE FROM CURRENT_TIMESTAMP)" +
            "AND EXTRACT(SECOND FROM w.time) BETWEEN EXTRACT(SECOND FROM CURRENT_TIMESTAMP - 4) AND EXTRACT(SECOND FROM CURRENT_TIMESTAMP)")
    List<Sleep> findDay();

    // DB에 저장되어 있는 값들 중 현재 시간과 비교하여 같은 일, 설정한 시, num 분의 레코드만 가져오기
    @Query("SELECT w FROM Sleep w WHERE DATE(w.time) = CURRENT_DATE " +
            "AND EXTRACT(HOUR FROM w.time) = EXTRACT(HOUR FROM CURRENT_TIMESTAMP) + :HMinus " +
            "AND EXTRACT(MINUTE FROM w.time) = EXTRACT(MINUTE FROM CURRENT_TIMESTAMP) + :mMinus " +
            "AND EXTRACT(SECOND FROM w.time) BETWEEN EXTRACT(SECOND FROM :beforeNum) AND EXTRACT(SECOND FROM :afterNum)")
    List<Sleep> findWeekOrMonth(@Param("beforeNum") int beforeNum, @Param("afterNum") int afterNum, @Param("mMinus") int mMinus, @Param("HMinus") int HMinus);
}
