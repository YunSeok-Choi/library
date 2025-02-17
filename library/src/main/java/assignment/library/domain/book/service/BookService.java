package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.entity.Book;
import assignment.library.domain.book.entity.BookTag;
import assignment.library.domain.book.repository.BookCustomRepository;
import assignment.library.domain.book.repository.BookRepository;
import assignment.library.domain.book.repository.BookTagRepository;
import assignment.library.domain.book.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assignment.library.domain.book.entity.Tag;

import java.util.Set;
import java.util.stream.Collectors;

import static assignment.library.global.util.RedisConstants.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final BookTagRepository bookTagRepository;
    private final BookCustomRepository bookCustomRepository;

    @CacheEvict(value = BOOK_INFO, key = "'allBookInfoKey'")
    public void registerBook(RegisterBookRequest registerBookRequest) {
        Book book = bookRepository.save(registerBookRequest.toEntity());
        createBookTag(registerBookRequest.getTags(), book);
    }

    @Cacheable(
            value = BOOK_INFO,
            key = "#bookId != null ? #bookId : 'allBookInfoKey'",
            condition = "#bookId != null ",
            unless = "#bookTitle == null && #bookAuthor == null && #sorted == null && #bookTag == null"
    )
    public Page<BookInfoResponse> getBookInfo(Long bookId, String bookTitle, String bookTag,
                                              String bookAuthor, String sorted, Pageable pageable) {
        return bookCustomRepository.getBookInfo(bookId, bookTitle, bookTag, bookAuthor, sorted, pageable);
    }

    @Caching(evict = {
            @CacheEvict(value = BOOK_INFO, key = "#bookId != null ? #bookId : 'allBookInfoKey'"),
            @CacheEvict(value = LOAN_STATUS, key = "#bookId")
    })
    public void updateBook(Long bookId, UpdateBookRequest updateBookRequest) {
        bookCustomRepository.updateBook(bookId, updateBookRequest);
        bookCustomRepository.deleteBookTag(bookId);
        bookRepository.findById(bookId).ifPresent(book -> {
            createBookTag(updateBookRequest.getTags(), book);
        });
    }

    @Caching(evict = {
            @CacheEvict(value = BOOK_INFO, key = "#bookId != null ? #bookId : 'allBookInfoKey'"),
            @CacheEvict(value = LOAN_STATUS, key = "#bookId")
    })
    public void deleteBook(Long bookId) {
        bookCustomRepository.deleteBook(bookId);
    }

    public void createBookTag(Set<String> tags, Book book) {
        tags.stream()
                .map(tagName -> tagRepository.findByTagName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName))))
                .map(tag -> BookTag.builder().book(book).tag(tag).build())
                .forEach(bookTagRepository::save);
    }

    public Set<String> getBookTag(Book book) {
        return book.getBookTags()
                .stream()
                .map(bookTag -> bookTag.getTag().getTagName())
                .collect(Collectors.toSet());
    }

}
