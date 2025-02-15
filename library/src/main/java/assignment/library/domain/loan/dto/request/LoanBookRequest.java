package assignment.library.domain.loan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static assignment.library.domain.book.dto.BookConstants.BOOK_ID;
import static assignment.library.domain.book.dto.BookConstants.EX_BOOK_ID;
import static assignment.library.domain.loan.dto.LoanConstants.LOAN_BOOK_ID;
import static assignment.library.domain.loan.dto.LoanConstants.LOAN_USER_ID;
import static assignment.library.domain.user.dto.UserConstants.*;
import static assignment.library.global.util.GlobalConstants.CHECK_INTEGER;

@Getter
@AllArgsConstructor
@Schema(description = "도서 대출 요청 DTO")
public class LoanBookRequest {

    @Positive(message = CHECK_INTEGER)
    @NotNull(message = LOAN_USER_ID)
    @Schema(description = USER_ID, example = EX_USER_ID)
    private Long userId;

    @Positive(message = CHECK_INTEGER)
    @NotNull(message = LOAN_BOOK_ID)
    @Schema(description = BOOK_ID, example = EX_BOOK_ID)
    private Long bookId;
}
