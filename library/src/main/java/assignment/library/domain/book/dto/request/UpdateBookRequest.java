package assignment.library.domain.book.dto.request;

import assignment.library.domain.book.entity.BookStatus;
import lombok.Getter;

@Getter
public class UpdateBookRequest {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String category;
    private String tag;
}
