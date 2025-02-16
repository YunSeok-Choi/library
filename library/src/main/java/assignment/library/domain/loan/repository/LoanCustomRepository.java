package assignment.library.domain.loan.repository;

import assignment.library.domain.loan.entity.Loan;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static assignment.library.domain.loan.entity.QLoan.loan;

@Repository
@RequiredArgsConstructor
public class LoanCustomRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Loan> getLoanStatus(Long bookId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(loan)
                .where(loan.book.bookId.eq(bookId))
                .orderBy(loan.loanDate.desc())
                .fetchFirst());
    }
}
