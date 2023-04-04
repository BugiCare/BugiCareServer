package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Long> {

}
