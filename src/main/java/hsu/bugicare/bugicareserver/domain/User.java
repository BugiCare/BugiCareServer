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
    private String gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "REFRIGERATOR_ID")
    private Refrigerator refrigerator;

    @OneToOne
    @JoinColumn(name = "DOOR_ID")
    private Door door;

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private UserImage image;

    @OneToOne
    @JoinColumn(name = "SLEEP_ID")
    private Sleep sleep;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    // id를 직접 만들거면 인자에 id도 추가해야함
    @Builder
    public User(String name, String gender, String address, int age, String phone) {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
