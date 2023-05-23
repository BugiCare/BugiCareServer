package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
}
