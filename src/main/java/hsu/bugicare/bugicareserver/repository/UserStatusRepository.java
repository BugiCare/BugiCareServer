package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<Sleep, Long> {
}
