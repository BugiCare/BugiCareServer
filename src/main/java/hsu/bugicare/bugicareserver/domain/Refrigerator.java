package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name = "Refrigerator")
@Entity
public class Refrigerator {

    @Id
    private Long id;

    private int count;
    private Timestamp timestamp;
}
