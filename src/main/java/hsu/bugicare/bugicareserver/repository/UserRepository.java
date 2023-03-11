package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
