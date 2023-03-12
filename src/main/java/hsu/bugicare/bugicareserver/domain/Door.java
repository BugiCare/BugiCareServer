package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
@Table(name = "Door")
@Entity
public class Door {

    @Id
    private Long id;

    private int count;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;
}
