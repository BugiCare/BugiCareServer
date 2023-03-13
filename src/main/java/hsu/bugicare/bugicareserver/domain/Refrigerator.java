package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@Table(name = "Refrigerator")
@Entity
public class Refrigerator {

    @Id
    private Long id;

    private int count;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;
}
