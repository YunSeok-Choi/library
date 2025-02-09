package assignment.library.domain.book.controller;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "Book", description = "도서 관련 API")
public class BookController {

    private final BookService bookService;

    // 도서 등록
    @PostMapping("/registerBook")
    @Operation(summary = "도서 등록", description = "도서 등록 API")
    public ResponseEntity<?> registerBook(@RequestBody RegisterBookRequest registerBookRequest) {
        bookService.registerBook(registerBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
