package assignment.library.domain.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private LocalDate publishedDate;
    private String category;
    private String tag;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void loanedBook() {
        this.status = BookStatus.LOANED;
    }

    public void availableBook() {
        this.status = BookStatus.AVAILABLE;
    }

    @Builder
    public Book(Long bookId, String title, String author, String isbn, String publisher, LocalDate publishedDate,
                String category, String tag, BookStatus status, LocalDateTime createdAt) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.category = category;
        this.tag = tag;
        this.status = status;
        this.createdAt = createdAt;
    }
}
