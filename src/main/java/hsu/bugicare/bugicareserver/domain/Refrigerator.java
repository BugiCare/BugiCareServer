package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@NoArgsConstructor
@Table(name = "Refrigerator")
@Entity
public class Refrigerator {

    @Id@ GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date time;

    @Builder
    public Refrigerator(String status) {
        this.status = status;
        time = new Date();
    }
}
