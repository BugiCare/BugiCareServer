package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "Sleep")
@Entity
public class Sleep {

    @Id
    private Long id;

    private LocalDateTime start_time;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Data timestamp;
}
