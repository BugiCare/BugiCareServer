package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
