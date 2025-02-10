package assignment.library.domain.loan.entity;

import assignment.library.domain.book.entity.Book;
import assignment.library.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    private LocalDateTime createdAt;

    public void returnBook() {
        this.returnDate = LocalDateTime.now();
        this.status = LoanStatus.RETURNED;
    }

    @Builder
    public Loan(Long loanId, User user, Book book, LocalDateTime loanDate, LocalDateTime dueDate,
                LocalDateTime returnDate, LoanStatus status, LocalDateTime createdAt) {
        this.loanId = loanId;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.createdAt = createdAt;
    }
}
