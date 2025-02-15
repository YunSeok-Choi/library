package assignment.library.domain.book.dto.response;

import assignment.library.domain.book.entity.BookStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoResponse {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private LocalDate publishedDate;
    private String category;
    private String tag;
    private String status;

    @Builder
    @QueryProjection
    public BookInfoResponse(Long bookId, String title, String author, String isbn, String publisher,
                            LocalDate publishedDate, String category, String tag, BookStatus status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.category = category;
        this.tag = tag;
        this.status = status.getDescription();
    }
}
