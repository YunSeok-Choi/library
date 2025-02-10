package assignment.library.domain.loan.service;

import assignment.library.domain.loan.dto.request.LoanBookRequest;

public interface LoanService {
    void loanBook(LoanBookRequest loanBookRequest);
}
