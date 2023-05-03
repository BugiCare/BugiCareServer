package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "Manager")
@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "manager")
    private List<User> users = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String center_name;

    @Column(nullable = false)
    private String phone;

    @Builder
    public Manager(String name, String center_name, String phone) {
        this.name = name;
        this.center_name = center_name;
        this.phone = phone;
    }
}
