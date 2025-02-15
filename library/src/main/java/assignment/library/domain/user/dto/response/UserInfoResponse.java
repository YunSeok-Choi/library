package assignment.library.domain.user.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static assignment.library.domain.loan.dto.LoanConstants.EX_LOAN_ID;
import static assignment.library.domain.loan.dto.LoanConstants.LOAN_ID;
import static assignment.library.domain.user.dto.UserConstants.*;

@Getter
@Schema(description = "사용자 정보 응답 DTO")
public class UserInfoResponse {

    @Schema(description = USER_ID, example = EX_USER_ID)
    private Long userId;

    @Schema(description = USER_NAME, example = EX_USER_NAME)
    private String userName;

    @Schema(description = USER_EMAIL, example = EX_USER_EMAIL)
    private String userEmail;

    @Schema(description = USER_PASSWORD, example = EX_USER_PASSWORD)
    private String userPassword;

    @QueryProjection
    public UserInfoResponse(Long userId, String userName, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}

