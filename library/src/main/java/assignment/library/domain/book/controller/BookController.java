package assignment.library.domain.book.controller;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.service.BookService;
import assignment.library.domain.user.dto.response.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/book")
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

    // 도서 조회
    @GetMapping("/info")
    @Operation(summary = "도서 정보 조회", description = "도서 정보 조회 API")
    public ResponseEntity<?> bookInfo(@RequestParam(required = false) Long bookId) {

        List<BookInfoResponse> bookInfo = bookService.getBookInfo(bookId);
        if (bookInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(OK).body(bookInfo);
    }

    // 도서 수정
    @PutMapping("/update")
    @Operation(summary = "도서 정보 수정", description = "도서 정보 수정 API")
    public ResponseEntity<?> updateBook(@RequestParam(required = false) Long bookId,
                                        @RequestBody UpdateBookRequest updateBookRequest) {
        bookService.updateBook(bookId, updateBookRequest);

        return ResponseEntity.status(OK).build();
    }

    // 도서 삭제
    @DeleteMapping("/delete")
    @Operation(summary = "도서 삭제", description = "도서 삭제 API")
    public ResponseEntity<?> deleteBook(@RequestParam(required = false) Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(OK).build();
    }


}
