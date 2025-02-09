package assignment.library.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String userEmail;

    private String userPassword;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public User(String userEmail, String userPassword, String userName, String userNickname, LocalDateTime createdAt) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.createdAt = createdAt;
    }

}
