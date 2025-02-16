package assignment.library.domain.book.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

import static assignment.library.domain.book.dto.BookConstants.*;

@Getter
@Schema(description = "도서 수정 요청 DTO")
public class UpdateBookRequest {

    @Schema(description = BOOK_TITLE, example = EX_BOOK_TITLE)
    @NotNull(message = CHECK_BOOK_TITLE)
    private String title;

    @Schema(description = BOOK_AUTHOR, example = EX_BOOK_AUTHOR)
    @NotNull(message = CHECK_BOOK_AUTHOR)
    private String author;

    @Schema(description = BOOK_ISBN, example = EX_BOOK_ISBN)
    @NotBlank(message = CHECK_BOOK_ISBN)
    private String isbn;

    @Schema(description = BOOK_PUBLISHER, example = EX_BOOK_PUBLISHER)
    @NotNull(message = CHECK_BOOK_PUBLISHER)
    private String publisher;

    @Schema(description = BOOK_PUBLISHED_DATE, example = EX_BOOK_PUBLISHED_DATE)
    @NotNull(message = CHECK_BOOK_PUBLISHED_DATE)
    @PastOrPresent(message = CHECK_BOOK_PastOrPresent_DATE)
    private LocalDate publishedDate;

    @Schema(description = BOOK_CATEGORY, example = EX_BOOK_CATEGORY)
    private String category;

    @Schema(description = BOOK_TAG)
    private Set<String> tags;
}
