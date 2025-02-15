package assignment.library.domain.loan.dto.response;

import assignment.library.domain.book.entity.BookStatus;
import assignment.library.domain.loan.entity.LoanStatus;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
public class LoanStatusResponse {

    private Long loanId;
    private String userName;
    private Long bookId;
    private String loanDate;
    private String dueDate;
    private String returnDate;
    private String loanStatus;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private LocalDate publishedDate;
    private String category;
    private String tag;
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
