package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Table(name = "Sleep")
@Entity
public class Sleep {

    @Id
    private Long id;

    private LocalDateTime start_time;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;
}
