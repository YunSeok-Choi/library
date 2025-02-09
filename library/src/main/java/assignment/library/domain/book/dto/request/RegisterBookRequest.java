package assignment.library.domain.book.dto.request;

import assignment.library.domain.book.entity.Book;
import assignment.library.domain.book.entity.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RegisterBookRequest {

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publishedDate;
    private String category;
    private String tag;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .isbn(isbn)
                .publisher(publisher)
                .publishedDate(publishedDate)
                .category(category)
                .tag(tag)
                .status(BookStatus.AVAILABLE)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
