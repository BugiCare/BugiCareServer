package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table(name = "Image")
@Entity
public class UserImage {

    @Id
    private Long id;

    private String mimetype;

    private String original_name;

    private String data;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    // 생성자 어떻게 만들어야 할지 정해야됨
}
