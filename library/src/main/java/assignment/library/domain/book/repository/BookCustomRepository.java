package assignment.library.domain.book.repository;

import assignment.library.domain.book.dto.request.UpdateBookRequest;
import assignment.library.domain.book.dto.response.BookInfoResponse;
import assignment.library.domain.book.entity.Book;
import assignment.library.global.util.CustomPageImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static assignment.library.domain.book.entity.QBook.book;
import static assignment.library.domain.book.entity.QBookTag.bookTag;
import static assignment.library.domain.book.entity.QTag.tag;
import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookCustomRepository {

    private final JPAQueryFactory queryFactory;

    public Page<BookInfoResponse> getBookInfo(Long bookId, String bookTitle, String tagName,
                                              String bookAuthor, String sorted, Pageable pageable) {

        List<Book> books = getBooksForCondition(bookId, bookTitle, bookAuthor, sorted, pageable);

        log.info("book info 쿼리 실행");
        List<Long> bookIds = books.stream()
                .map(Book::getBookId)
                .collect(Collectors.toList());

        Map<Long, List<String>> bookTagsMap = getBookTagsMap(tagName, bookIds);

        List<BookInfoResponse> content = books.stream()
                .map(b -> new BookInfoResponse(
                        b.getBookId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getIsbn(),
                        b.getPublisher(),
                        b.getPublishedDate(),
                        b.getCategory(),
                        bookTagsMap.getOrDefault(b.getBookId(), Collections.emptyList()), // 태그 없을 경우 빈 리스트 반환
                        b.getStatus()
                ))
                .toList();

        long total = queryFactory
                .selectFrom(book)
                .where(
                        bookIdEq(bookId),
                        bookTitleContains(bookTitle),
                        bookAuthorContains(bookAuthor)
                ).fetch().size();

        return new CustomPageImpl<>(content, pageable, total);
    }

    private Map<Long, List<String>> getBookTagsMap(String tagName, List<Long> bookIds) {
        return queryFactory
                .select(book.bookId, tag.tagName)
                .from(book)
                .leftJoin(bookTag).on(book.bookId.eq(bookTag.book.bookId))
                .leftJoin(tag).on(bookTag.tag.tagId.eq(tag.tagId))
                .where(
                        book.bookId.in(bookIds),
                        bookTagContains(tagName)
                )
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(
                        tuple -> tuple.get(book.bookId),
                        Collectors.mapping(tuple -> tuple.get(tag.tagName), Collectors.toList())
                ));
    }

    private List<Book> getBooksForCondition(Long bookId, String bookTitle, String bookAuthor, String sorted, Pageable pageable) {
        return queryFactory
                .select(book)
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
                .where(bookIdEq(bookId))
                .execute();
    }

    public void deleteBookTag(Long bookId) {
        queryFactory
                .delete(bookTag)
                .where(bookTag.book.bookId.eq(bookId))
                .execute();
    }

    public void deleteBook(Long bookId) {
        queryFactory
                .delete(book)
                .where(bookIdEq(bookId))
                .execute();
    }

    public void getBookTag(Long bookId) {
        queryFactory
                .select(tag.tagName)
                .from(tag)
                .join(bookTag).on(bookTag.book.bookId.eq(bookId));

    }

    private BooleanExpression bookIdEq(Long bookId) {
        return isEmpty(bookId) ? null : book.bookId.eq(bookId);
    }

    private BooleanExpression bookTitleContains(String bookTitle) {
        return (bookTitle == null || bookTitle.trim().isEmpty()) ? null : book.title.contains(bookTitle);
    }

    private BooleanExpression bookTagContains(String bookTag) {
        return (bookTag == null || bookTag.trim().isEmpty()) ? null : tag.tagName.contains(bookTag);
    }

    private BooleanExpression bookAuthorContains(String bookAuthor) {
        return (bookAuthor == null || bookAuthor.trim().isEmpty()) ? null : book.author.contains(bookAuthor);
    }

    private OrderSpecifier<?> getSortOrder(String sorted) {
        if (isEmpty(sorted)) {
            return book.bookId.asc();
        }

        return switch (sorted) {
            case "title" -> book.title.asc();
            case "publishedDate" -> book.publishedDate.asc();
            default -> book.bookId.asc();
        };
    }


}
