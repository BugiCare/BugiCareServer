package hsu.bugicare.bugicareserver.repository;

import hsu.bugicare.bugicareserver.domain.TTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTSRepository extends JpaRepository<TTS, Long> {
}
