package assignment.library.domain.loan.controller;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.loan.dto.request.LoanBookRequest;
import assignment.library.domain.loan.service.LoanService;
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
}
