package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "Sleep")
@Entity
public class Sleep {

    @Id
    private Long id;

    private LocalDateTime start_time;

    private Timestamp timestamp;
}
