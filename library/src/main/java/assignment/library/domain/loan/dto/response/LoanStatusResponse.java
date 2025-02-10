package assignment.library.domain.loan.dto.response;

import assignment.library.domain.book.entity.BookStatus;
import assignment.library.domain.loan.entity.LoanStatus;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class LoanStatusResponse {

    private Long loanId;
    private String userName;
    private Long bookId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private String loanStatus;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String category;
    private String tag;
    private String bookStatus;

    @QueryProjection
    public LoanStatusResponse(Long loanId, String userName, Long bookId, LocalDateTime loanDate,
                              LocalDateTime dueDate, LocalDateTime returnDate, LoanStatus loanStatus,
                              String title, String author, String isbn, String publisher,
                              String publishedDate, String category, String tag, BookStatus bookStatus) {
        this.loanId = loanId;
        this.userName = userName;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus.getDescription();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.category = category;
        this.tag = tag;
        this.bookStatus = bookStatus.getDescription();
    }
}
