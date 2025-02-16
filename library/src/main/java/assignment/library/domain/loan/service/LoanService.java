package assignment.library.domain.loan.service;

import assignment.library.domain.book.entity.Book;
import assignment.library.domain.book.entity.BookTag;
import assignment.library.domain.book.repository.BookRepository;
import assignment.library.domain.book.service.BookService;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static assignment.library.global.util.RedisConstants.BOOK_INFO;
import static assignment.library.global.util.RedisConstants.LOAN_STATUS;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoanService {

    private final BookService bookService;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final LoanCustomRepository loanCustomRepository;

    @Caching(evict = {
            @CacheEvict(value = LOAN_STATUS, key = "#loanBookRequest.bookId"),
            @CacheEvict(value = BOOK_INFO, key = "#loanBookRequest.bookId"),
            @CacheEvict(value = BOOK_INFO, key = "'allBookInfoKey'")
    })
    public void loanBook(LoanBookRequest loanBookRequest) {
        Long userId = loanBookRequest.getUserId();
        Long bookId = loanBookRequest.getBookId();

        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

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

    @Cacheable(value = LOAN_STATUS, key = "#bookId", unless = "#result == null")
    public LoanStatusResponse getLoanStatus(Long bookId) {

        LoanStatusResponse.LoanStatusResponseBuilder responseBuilder = LoanStatusResponse.builder();

        bookRepository.findById(bookId).ifPresent(book -> {
            responseBuilder
                    .bookId(book.getBookId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .publisher(book.getPublisher())
                    .publishedDate(book.getPublishedDate())
                    .category(book.getCategory())
                    .tags(bookService.getBookTag(book))
                    .bookStatus(book.getStatus().getDescription());
        });

        loanCustomRepository.getLoanStatus(bookId).ifPresent(loanStatus -> {
            responseBuilder
                    .loanId(loanStatus.getLoanId())
                    .userName(loanStatus.getUser().getUserName())
                    .loanDate(loanStatus.getLoanDate().toString())
                    .dueDate(loanStatus.getDueDate().toString())
                    .returnDate(loanStatus.getReturnDate() == null ? null : loanStatus.getReturnDate().toString())
                    .loanStatus(loanStatus.getStatus().getDescription());
        });

        return responseBuilder.build();

    }

    @Caching(evict = {
            @CacheEvict(value = LOAN_STATUS, key = "#bookId"),
            @CacheEvict(value = BOOK_INFO, key = "#bookId"),
            @CacheEvict(value = BOOK_INFO, key = "'allBookInfoKey'")
    })
    public void returnBook(Long bookId) {
        Loan loanStatus = loanCustomRepository.getLoanStatus(bookId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        loanStatus.returnBook();
        book.availableBook();

        loanRepository.save(loanStatus);
        bookRepository.save(book);
    }

}
