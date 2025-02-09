package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.entity.Book;

import java.util.List;

public interface BookService {
    void registerBook(RegisterBookRequest registerBookRequest);
    List<BookInfoResponse> getBookInfo(Long bookId);
}
