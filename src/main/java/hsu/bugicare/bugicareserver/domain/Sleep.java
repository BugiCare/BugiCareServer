package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@Table(name = "Sleep")
@Entity
public class Sleep {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME")
    private Date time;

    @Builder
    public Sleep(String status) {
        this.status = status;
        time = new Date();
    }
}
