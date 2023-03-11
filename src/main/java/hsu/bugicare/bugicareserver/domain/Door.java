package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class Door {

    @Id
    private Long id;

    private int count;
    private Timestamp timestamp;
}
