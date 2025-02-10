package assignment.library.domain.loan.service;

import assignment.library.domain.loan.dto.request.LoanBookRequest;
import assignment.library.domain.loan.dto.response.LoanStatusResponse;

public interface LoanService {
    void loanBook(LoanBookRequest loanBookRequest);
    LoanStatusResponse getLoanStatus(Long bookId);
    void returnBook(Long bookId);
}
