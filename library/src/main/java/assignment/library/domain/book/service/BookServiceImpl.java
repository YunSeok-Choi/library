package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.entity.Book;
import assignment.library.domain.book.repository.BookCustomRepository;
import assignment.library.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static assignment.library.global.util.RedisConstants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCustomRepository bookCustomRepository;

    @Override
    @CacheEvict(value = BOOK_INFO, key = ALL_BOOK_INFO_KEY)
    public void registerBook(RegisterBookRequest registerBookRequest) {
        Book book = registerBookRequest.toEntity();

        bookRepository.save(book);
    }

    @Override
    @Cacheable(value = BOOK_INFO,
            key = "#bookId != null ? #bookId : T(assignment.library.global.util.RedisConstants).ALL_BOOK_INFO_KEY",
            unless = "#result == null or #result.isEmpty()")
    public List<BookInfoResponse> getBookInfo(Long bookId) {
        return bookCustomRepository.getBookInfo(bookId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(
                    value = BOOK_INFO,
                    key = "#bookId != null ?" +
                            " #bookId : T(assignment.library.global.util.RedisConstants).ALL_BOOK_INFO_KEY"),
            @CacheEvict(value = LOAN_STATUS, key = "#bookId")
    })
    public void updateBook(Long bookId, UpdateBookRequest updateBookRequest) {
        bookCustomRepository.updateBook(bookId, updateBookRequest);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = BOOK_INFO,
                    key = "#bookId != null ?" +
                            " #bookId : T(assignment.library.global.util.RedisConstants).ALL_BOOK_INFO_KEY"),
            @CacheEvict(value = LOAN_STATUS, key = "#bookId")
    })
    public void deleteBook(Long bookId) {
        bookCustomRepository.deleteBook(bookId);
    }
}
