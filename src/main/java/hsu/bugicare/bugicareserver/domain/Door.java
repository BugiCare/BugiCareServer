package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "Door")
@Entity
public class Door {

    @Id
    private Long id;

    private int count;
    private Timestamp timestamp;
}
