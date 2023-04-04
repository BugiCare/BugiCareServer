package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    // DB에 저장되어 있는 값들 중 현재의 분(Minute)과 비교하여 같은 분(Minute)의 레코드들만 가져오기
    @Query("SELECT e FROM Refrigerator e WHERE MINUTE(e.time) = MINUTE(CURRENT_TIMESTAMP)")
    List<Refrigerator> findWithSameMinute();




}
