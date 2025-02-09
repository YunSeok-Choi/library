package assignment.library.domain.book.service;

import assignment.library.domain.book.dto.request.RegisterBookRequest;
import assignment.library.domain.book.entity.Book;

public interface BookService {
    void registerBook(RegisterBookRequest registerBookRequest);
}
