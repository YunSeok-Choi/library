package assignment.library.domain.book.dto.response;

import assignment.library.domain.book.entity.BookStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class BookInfoResponse {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String category;
    private String tag;
    private String status;

    @QueryProjection
    public BookInfoResponse(Long bookId, String title, String author, String isbn, String publisher,
                            String publishedDate, String category, String tag, BookStatus status) {
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
