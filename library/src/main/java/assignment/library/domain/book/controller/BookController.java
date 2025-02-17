package assignment.library.domain.book.controller;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static assignment.library.domain.book.dto.BookConstants.*;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Tag(name = "Book", description = "도서 관련 API")
public class BookController {

    private final BookService bookService;

    @PostMapping("/register")
    @Operation(summary = "도서 등록", description = "도서 등록 API", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "도서 등록 성공")
    })
    public ResponseEntity<?> registerBook(@Validated @RequestBody RegisterBookRequest registerBookRequest) {
        log.info(LOG_BOOK_REGISTER);
        bookService.registerBook(registerBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/info")
    @Operation(summary = "도서 정보 조회", description = "도서 정보 조회 API", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "도서 조회 성공",
                    content = @Content(schema = @Schema(implementation = BookInfoResponse.class))),
            @ApiResponse(
                    responseCode = "204",
                    description = "존재하지 않는 리소스 접근")
    })
    public ResponseEntity<?> bookInfo(
            @Parameter(name = "bookId", description = BOOK_ID) @RequestParam(required = false) Long bookId,
            @Parameter(name = "bookTitle", description = BOOK_TITLE) @RequestParam(required = false) String bookTitle,
            @Parameter(name = "bookAuthor", description = BOOK_AUTHOR) @RequestParam(required = false) String bookAuthor,
            @Parameter(name = "bookTag", description = BOOK_TAG) @RequestParam(required = false) String bookTag,
            @Parameter(name = "sorted", description = BOOK_SORTED) @RequestParam(required = false) String sorted,
            @Parameter(name = "page, size", description = "페이지 번호, 한 페이지에 들어가는 데이터 수") Pageable pageable) {

        log.info(LOG_BOOK_INFO);
        Page<BookInfoResponse> bookInfo = bookService.getBookInfo(
                bookId, bookTitle, bookTag,
                bookAuthor, sorted, pageable);

        if (bookInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(OK).body(bookInfo);
    }

    @PutMapping("/update")
    @Operation(summary = "도서 정보 수정", description = "도서 정보 수정 API", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "도서 수정 성공")
    })
    public ResponseEntity<?> updateBook(
            @Parameter(name = "bookId", description = BOOK_ID) @RequestParam(required = false) Long bookId,
            @Validated @RequestBody UpdateBookRequest updateBookRequest) {
        log.info(LOG_BOOK_UPDATE);
        bookService.updateBook(bookId, updateBookRequest);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "도서 삭제", description = "도서 삭제 API", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "도서 삭제 성공")
    })
    public ResponseEntity<?> deleteBook(
            @Parameter(name = "bookId", description = BOOK_ID) @RequestParam(required = false) Long bookId) {
        log.info(LOG_BOOK_DELETE);
        bookService.deleteBook(bookId);
        return ResponseEntity.status(OK).build();
    }


}
