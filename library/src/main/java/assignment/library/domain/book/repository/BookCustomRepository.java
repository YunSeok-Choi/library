package assignment.library.domain.book.repository;

import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.dto.response.QBookInfoResponse;
import assignment.library.domain.book.entity.QBook;
import assignment.library.domain.user.dto.response.QUserInfoResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.library.domain.book.entity.QBook.book;
import static assignment.library.domain.user.entity.QUser.user;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class BookCustomRepository {

    private final JPAQueryFactory queryFactory;

    public List<BookInfoResponse> getBookInfo(Long bookId) {
        return queryFactory
                .select(new QBookInfoResponse(book.bookId, book.title, book.author, book.isbn,
                        book.publisher, book.publishedDate, book.category, book.tag, book.status))
                .from(book)
                .where(bookIdEq(bookId))
                .fetch();
    }

    public void updateBook(Long bookId, UpdateBookRequest updateBookRequest) {
        queryFactory
                .update(book)
                .set(book.title, updateBookRequest.getTitle())
                .set(book.author, updateBookRequest.getAuthor())
                .set(book.isbn, updateBookRequest.getIsbn())
                .set(book.publisher, updateBookRequest.getPublisher())
                .set(book.publishedDate, updateBookRequest.getPublishedDate())
                .set(book.category, updateBookRequest.getCategory())
                .set(book.tag, updateBookRequest.getTag())
                .where(bookIdEq(bookId))
                .execute();
    }

    private BooleanExpression bookIdEq(Long bookId) {
        return isEmpty(bookId) ? null : book.bookId.eq(bookId);
    }

}
