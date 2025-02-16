package assignment.library.domain.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@ToString
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

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookTag> bookTags = new HashSet<>();

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
    public Book(Long bookId, String title, String author, String isbn, String publisher,
                LocalDate publishedDate, String category, BookStatus status, LocalDateTime createdAt) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }
}
