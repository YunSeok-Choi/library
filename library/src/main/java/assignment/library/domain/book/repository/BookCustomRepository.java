package assignment.library.domain.book.repository;

import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.dto.response.QBookInfoResponse;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.library.domain.book.entity.QBook.book;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class BookCustomRepository {

    private final JPAQueryFactory queryFactory;

    public Page<BookInfoResponse> getBookInfo(Long bookId, String bookTitle, String bookAuthor, String sorted, Pageable pageable) {
        List<BookInfoResponse> bookList = queryFactory
                .select(new QBookInfoResponse(book.bookId, book.title, book.author, book.isbn,
                        book.publisher, book.publishedDate, book.category, book.tag, book.status))
                .from(book)
                .where(
                        bookIdEq(bookId),
                        bookTitleContains(bookTitle),
                        bookAuthorContains(bookAuthor)
                )
                .orderBy(getSortOrder(sorted))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(book)
                .from(book)
                .where(
                        bookIdEq(bookId),
                        bookTitleContains(bookTitle),
                        bookAuthorContains(bookAuthor)
                )
                .fetch().size();

        return new PageImpl<BookInfoResponse>(bookList, pageable, total);
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

    public void deleteBook(Long bookId) {
        queryFactory
                .delete(book)
                .where(bookIdEq(bookId))
                .execute();
    }

    private BooleanExpression bookIdEq(Long bookId) {
        return isEmpty(bookId) ? null : book.bookId.eq(bookId);
    }

    private BooleanExpression bookTitleContains(String bookTitle) {
        return isEmpty(bookTitle) ? null : book.title.contains(bookTitle);
    }

    private BooleanExpression bookAuthorContains(String bookAuthor) {
        return isEmpty(bookAuthor) ? null : book.author.contains(bookAuthor);
    }

    private OrderSpecifier<?> getSortOrder(String sorted) {
        return switch (sorted) {
            case "title" -> book.title.asc();
            case "publishedDate" -> book.publishedDate.asc();
            default -> book.bookId.asc();
        };
    }


}
