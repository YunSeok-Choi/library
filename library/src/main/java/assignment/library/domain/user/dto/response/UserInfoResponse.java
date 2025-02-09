package assignment.library.domain.user.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserInfoResponse {
    private Long userId;

    private String userName;

    private String userEmail;

    private String userPassword;

    @QueryProjection
    public UserInfoResponse(Long userId, String userName, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}

