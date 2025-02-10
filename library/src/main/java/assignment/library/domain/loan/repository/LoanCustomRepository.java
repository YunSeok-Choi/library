package assignment.library.domain.loan.repository;

import assignment.library.domain.loan.dto.response.LoanStatusResponse;
import assignment.library.domain.loan.dto.response.QLoanStatusResponse;
import assignment.library.domain.loan.entity.Loan;
import assignment.library.domain.loan.entity.LoanStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static assignment.library.domain.book.entity.QBook.book;
import static assignment.library.domain.loan.entity.QLoan.loan;
import static assignment.library.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class LoanCustomRepository {

    private final JPAQueryFactory queryFactory;

    public LoanStatusResponse getLoanStatus(Long bookId) {

        return queryFactory
                .select(new QLoanStatusResponse(loan.loanId, loan.user.userName, book.bookId,
                        loan.loanDate, loan.dueDate, loan.returnDate, loan.status.coalesce(LoanStatus.NON_STATUS),
                        book.title, book.author, book.isbn, book.publisher, book.publishedDate,
                        book.category, book.tag, book.status))
                .from(book)
                .leftJoin(loan).on(book.bookId.eq(loan.book.bookId))
                .leftJoin(user).on(loan.user.userId.eq(user.userId))
                .where(book.bookId.eq(bookId))
                .orderBy(loan.loanDate.desc())
                .fetchFirst();

    }
}
