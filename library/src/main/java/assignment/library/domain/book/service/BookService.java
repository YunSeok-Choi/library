package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;

import java.util.List;

public interface BookService {
    void registerBook(RegisterBookRequest registerBookRequest);
    List<BookInfoResponse> getBookInfo(Long bookId, String bookTitle, String bookAuthor);
    void updateBook(Long bookId, UpdateBookRequest updateBookRequest);
    void deleteBook(Long bookId);
}
