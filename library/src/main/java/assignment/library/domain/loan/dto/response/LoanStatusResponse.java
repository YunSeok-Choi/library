package assignment.library.domain.loan.dto.response;

import assignment.library.domain.book.entity.BookStatus;
import assignment.library.domain.loan.entity.LoanStatus;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static assignment.library.domain.book.dto.BookConstants.*;
import static assignment.library.domain.book.dto.BookConstants.EX_BOOK_TITLE;
import static assignment.library.domain.loan.dto.LoanConstants.*;
import static assignment.library.domain.user.dto.UserConstants.EX_USER_NAME;
import static assignment.library.domain.user.dto.UserConstants.USER_NAME;

@Getter
@ToString
@NoArgsConstructor
@Schema(description = "대출 상태 응답 DTO")
public class LoanStatusResponse {

    @Schema(description = LOAN_ID, example = EX_LOAN_ID)
    private Long loanId;

    @Schema(description = USER_NAME, example = EX_USER_NAME)
    private String userName;

    @Schema(description = BOOK_ID, example = EX_BOOK_ID)
    private Long bookId;

    @Schema(description = LOAN_DATE, example = EX_LOAN_DATE)
    private String loanDate;

    @Schema(description = DUE_DATE, example = EX_DUE_DATE)
    private String dueDate;

    @Schema(description = RETURN_DATE, example = EX_RETURN_DATE)
    private String returnDate;

    @Schema(description = LOAN_STATUS, example = EX_LOAN_STATUS)
    private String loanStatus;

    @Schema(description = BOOK_TITLE, example = EX_BOOK_TITLE)
    private String title;

    @Schema(description = BOOK_AUTHOR, example = EX_BOOK_AUTHOR)
    private String author;

    @Schema(description = BOOK_ISBN, example = EX_BOOK_ISBN)
    private String isbn;

    @Schema(description = BOOK_PUBLISHER, example = EX_BOOK_PUBLISHER)
    private String publisher;

    @Schema(description = BOOK_PUBLISHED_DATE, example = EX_BOOK_PUBLISHED_DATE)
    private LocalDate publishedDate;

    @Schema(description = BOOK_CATEGORY, example = EX_BOOK_CATEGORY)
    private String category;

    @Schema(description = BOOK_TAG, example = EX_BOOK_TAG)
    private String tag;

    @Schema(description = BOOK_STATUS, example = EX_BOOK_STATUS)
    private String bookStatus;

    @Builder
    public LoanStatusResponse(Long loanId, String userName, Long bookId, String loanDate,
                              String dueDate, String returnDate, String loanStatus,
                              String title, String author, String isbn, String publisher,
                              LocalDate publishedDate, String category, String tag, String bookStatus) {
        this.loanId = loanId;
        this.userName = userName;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.category = category;
        this.tag = tag;
        this.bookStatus = bookStatus;
    }
}
