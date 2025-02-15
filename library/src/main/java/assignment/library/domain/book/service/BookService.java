package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    void registerBook(RegisterBookRequest registerBookRequest);
    Page<BookInfoResponse> getBookInfo(Long bookId, String bookTitle, String bookTag, String bookAuthor, String sorted, Pageable pageable);
    void updateBook(Long bookId, UpdateBookRequest updateBookRequest);
    void deleteBook(Long bookId);
}
