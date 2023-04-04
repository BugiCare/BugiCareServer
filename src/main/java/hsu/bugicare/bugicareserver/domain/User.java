package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "User")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private Manager manager;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, length = 50000)
    private String address;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    @OneToOne
    @JoinColumn(name = "door_id")
    private Door door;

    @OneToOne
    @JoinColumn(name = "image_id")
    private UserImage image;

    @OneToOne
    @JoinColumn(name = "sleep_id")
    private Sleep sleep;

    // id를 직접 만들거면 인자에 id도 추가해야함
    @Builder
    public User(String name, Gender gender, String address, int age, String phone) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.phone = phone;
    }
}
