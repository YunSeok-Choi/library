package assignment.library.domain.loan.dto.request;

import assignment.library.domain.loan.entity.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanBookRequest {
    private Long userId;
    private Long bookId;
}
