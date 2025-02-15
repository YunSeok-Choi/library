package assignment.library.domain.loan.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanBookRequest {

    @Positive(message = "정수로 입력해주세요.")
    @NotNull(message = "대출하려는 사용자의 ID를 입력해주세요.")
    private Long userId;

    @Positive(message = "정수로 입력해주세요.")
    @NotNull(message = "대출하려는 도서의 ID를 입력해주세요.")
    private Long bookId;
}
