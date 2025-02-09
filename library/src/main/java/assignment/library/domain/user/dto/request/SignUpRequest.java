package assignment.library.domain.user.dto.request;

import assignment.library.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {
    private String userName;

    private String userEmail;

    private String userPassword;

    public User toEntity(){
        return User.builder()
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userName(userName)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
