package assignment.library.domain.loan.service;

import assignment.library.domain.book.entity.Book;
import assignment.library.domain.book.repository.BookRepository;
import assignment.library.domain.loan.dto.request.LoanBookRequest;
import assignment.library.domain.loan.dto.response.LoanStatusResponse;
import assignment.library.domain.loan.entity.Loan;
import assignment.library.domain.loan.entity.LoanStatus;
import assignment.library.domain.loan.repository.LoanCustomRepository;
import assignment.library.domain.loan.repository.LoanRepository;
import assignment.library.domain.user.entity.User;
import assignment.library.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final LoanCustomRepository loanCustomRepository;

    @Override
    public void loanBook(LoanBookRequest loanBookRequest) {
        Long userId = loanBookRequest.getUserId();
        Long bookId = loanBookRequest.getBookId();

        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null || book == null) {
            log.info("User or Book not found");
            return;
        }

        Loan loan = Loan.builder()
                .user(user)
                .book(book)
                .loanDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(7))
                .status(LoanStatus.LOANED)
                .createdAt(LocalDateTime.now())
                .build();

        loanRepository.save(loan);
        book.loanedBook();
        bookRepository.save(book);

    }

    @Override
    public LoanStatusResponse getLoanStatus(Long bookId) {

        Loan loanStatus = loanCustomRepository.getLoanStatus(bookId);
        Book book = bookRepository.findById(bookId).orElse(null);

        LoanStatusResponse.LoanStatusResponseBuilder responseBuilder;

        responseBuilder = LoanStatusResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .publishedDate(book.getPublishedDate())
                .category(book.getCategory())
                .tag(book.getTag())
                .bookStatus(book.getStatus().getDescription());

        if (loanStatus != null) {
            responseBuilder
                    .loanId(loanStatus.getLoanId())
                    .userName(loanStatus.getUser().getUserName())
                    .loanDate(loanStatus.getLoanDate())
                    .dueDate(loanStatus.getDueDate())
                    .returnDate(loanStatus.getReturnDate())
                    .loanStatus(loanStatus.getStatus().getDescription());
        }

        return responseBuilder.build();

    }

    @Override
    public void returnBook(Long bookId) {
        Loan loanStatus = loanCustomRepository.getLoanStatus(bookId);
        Book book = bookRepository.findById(bookId).orElse(null);

        loanStatus.returnBook();
        book.availableBook();

        loanRepository.save(loanStatus);
        bookRepository.save(book);

    }
}
