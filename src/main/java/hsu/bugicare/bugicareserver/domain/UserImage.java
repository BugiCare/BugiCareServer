package hsu.bugicare.bugicareserver.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "Image")
@Entity
public class UserImage {

    @Id
    @GeneratedValue
    private Long id;

    private String mimetype;

    @Column(nullable = false)
    private String original_name;

    @Column(nullable = false)
    private String stored_path;

    private long size;

    private String data;

    // 생성자 어떻게 만들어야 할지 정해야됨
    @Builder
    public UserImage(String original_name, String stored_path, long size) {
        this.original_name = original_name;
        this.stored_path = stored_path;
        this.size = size;
    }
}
