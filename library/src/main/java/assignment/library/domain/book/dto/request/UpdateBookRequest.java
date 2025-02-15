package assignment.library.domain.book.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateBookRequest {
    @NotNull(message = "책 제목을 입력해 주세요.")
    private String title;

    @NotNull(message = "작가 이름을 입력해 주세요.")
    private String author;

    @NotBlank(message = "국제표준도서번호를 입력해 주세요.")
    private String isbn;

    @NotNull(message = "출판사를 입력해 주세요.")
    private String publisher;

    @NotNull(message = "출판일을 입력해 주세요.")
    @PastOrPresent(message = "과거 혹은 오늘의 날짜로 입력해주세요.")
    private LocalDate publishedDate;

    private String category;

    private String tag;
}
