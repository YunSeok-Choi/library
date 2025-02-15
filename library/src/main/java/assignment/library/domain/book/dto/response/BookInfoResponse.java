package assignment.library.domain.book.dto.response;

import assignment.library.domain.book.entity.BookStatus;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static assignment.library.domain.book.dto.BookConstants.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "도서 정보 응답 DTO")
public class BookInfoResponse {

    @Schema(description = BOOK_ID, example = EX_BOOK_ID)
    private Long bookId;

    @Schema(description = BOOK_TITLE, example = EX_BOOK_TITLE)
    private String title;

    @Schema(description = BOOK_AUTHOR, example = EX_BOOK_AUTHOR)
    private String author;

    @Schema(description = BOOK_ISBN, example = EX_BOOK_ISBN)
    private String isbn;

    @Schema(description = BOOK_PUBLISHER, example = EX_BOOK_PUBLISHER)
    private String publisher;

    @Schema(description = BOOK_PUBLISHED_DATE, example = EX_BOOK_PUBLISHED_DATE)
    private String publishedDate;

    @Schema(description = BOOK_CATEGORY, example = EX_BOOK_CATEGORY)
    private String category;

    @Schema(description = BOOK_TAG, example = EX_BOOK_TAG)
    private String tag;

    @Schema(description = BOOK_STATUS, example = EX_BOOK_STATUS)
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
        this.publishedDate = publishedDate.toString();
        this.category = category;
        this.tag = tag;
        this.status = status.getDescription();
    }
}
