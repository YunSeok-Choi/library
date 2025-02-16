package assignment.library.domain.loan.controller;

import assignment.library.domain.loan.dto.request.LoanBookRequest;
import assignment.library.domain.loan.dto.response.LoanStatusResponse;
import assignment.library.domain.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static assignment.library.domain.loan.dto.LoanConstants.*;


@Slf4j
@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
@Tag(name = "Loan", description = "대출 관련 API")
public class LoanController {
    private final LoanService loanService;

    @PostMapping()
    @Operation(summary = "대출 등록", description = "대출 등록 API", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "대출 성공")
    })
    public ResponseEntity<?> loanBook(@Validated @RequestBody LoanBookRequest loanBookRequest) {
        log.info(LOG_LOAN_BOOK);
        loanService.loanBook(loanBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "대출 상태", description = "대출 상태 API", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "대출 조회 성공",
                    content = @Content(schema = @Schema(implementation = LoanStatusResponse.class))),
            @ApiResponse(
                    responseCode = "204",
                    description = "존재하지 않는 리소스 접근")
    })
    public ResponseEntity<?> loanStatus(@PathVariable Long bookId) {
        log.info(LOG_LOAN_INFO);
        LoanStatusResponse loanStatus = loanService.getLoanStatus(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(loanStatus);
    }

    @PatchMapping("/{bookId}")
    @Operation(summary = "도서 반납", description = "도서 반납 API", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "반납 성공")
    })
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        log.info(LOG_LOAN_RETURN);
        loanService.returnBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
