package assignment.library.domain.loan.controller;

import assignment.library.domain.loan.dto.request.LoanBookRequest;
import assignment.library.domain.loan.dto.response.LoanStatusResponse;
import assignment.library.domain.loan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
@Tag(name = "Loan", description = "대출 관련 API")
public class LoanController {
    private final LoanService loanService;

    // 대출 등록
    @PostMapping()
    @Operation(summary = "대출 등록", description = "대출 등록 API")
    public ResponseEntity<?> loanBook(@RequestBody LoanBookRequest loanBookRequest) {
        loanService.loanBook(loanBookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 대출 상태
    @GetMapping("/{bookId}")
    @Operation(summary = "대출 상태", description = "대출 상태 API")
    public ResponseEntity<?> loanStatus(@PathVariable Long bookId) {
        LoanStatusResponse loanStatus = loanService.getLoanStatus(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(loanStatus);
    }

    // 도서 반납
    @PatchMapping("/{bookId}")
    @Operation(summary = "도서 반납", description = "도서 반납 API")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId) {
        loanService.returnBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
