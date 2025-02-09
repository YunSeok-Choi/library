package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.entity.Book;
import assignment.library.domain.book.repository.BookCustomRepository;
import assignment.library.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCustomRepository bookCustomRepository;

    @Override
    public void registerBook(RegisterBookRequest registerBookRequest) {
        Book book = registerBookRequest.toEntity();

        bookRepository.save(book);
    }

    @Override
    public List<BookInfoResponse> getBookInfo(Long bookId) {
        return bookCustomRepository.getBookInfo(bookId);
    }

    @Override
    public void updateBook(Long bookId, UpdateBookRequest updateBookRequest) {
        bookCustomRepository.updateBook(bookId, updateBookRequest);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookCustomRepository.deleteBook(bookId);
    }
}
