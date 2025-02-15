package assignment.library.domain.user.dto.request;

import assignment.library.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static assignment.library.domain.book.dto.BookConstants.CHECK_BOOK_TITLE;
import static assignment.library.domain.user.dto.UserConstants.*;

@Getter
@RequiredArgsConstructor
@Schema(description = "회원 가입 요청 DTO")
public class SignUpRequest {

    @Max(value = 32, message = CHECK_USER_NAME_MAX)
    @NotBlank(message = CHECK_USER_NAME)
    @Schema(description = USER_NAME, example = EX_USER_NAME)
    private String userName;

    @Email(message = CHECK_USER_EMAIL_FORM)
    @NotBlank(message = CHECK_USER_EMAIL)
    @Schema(description = USER_EMAIL, example = EX_USER_EMAIL)
    private String userEmail;

    @Max(value = 32, message = CHECK_USER_PASSWORD_MAX)
    @NotBlank(message = CHECK_USER_PASSWORD)
    @Schema(description = USER_PASSWORD, example = EX_USER_PASSWORD)
    private String userPassword;

    public User toEntity() {
        return User.builder()
                .userEmail(userEmail)
                .userPassword(userPassword)
                .userName(userName)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
