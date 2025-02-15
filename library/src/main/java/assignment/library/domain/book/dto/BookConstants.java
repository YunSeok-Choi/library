package assignment.library.domain.book.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookConstants {

    // 예시
    public static final String EX_BOOK_ID = "1";
    public static final String EX_BOOK_TITLE = "채식주의자";
    public static final String EX_BOOK_AUTHOR = "한강";
    public static final String EX_BOOK_PUBLISHER = "창비";
    public static final String EX_BOOK_CATEGORY = "소설";
    public static final String EX_BOOK_ISBN = "9788936433598";
    public static final String EX_BOOK_TAG = "한국";
    public static final String EX_BOOK_PUBLISHED_DATE = "2007-10-30";
    public static final String EX_BOOK_STATUS = "대출 가능";

    // 설명
    public static final String BOOK_ID = "도서 ID";
    public static final String BOOK_TITLE = "도서 제목";
    public static final String BOOK_AUTHOR = "도서 작가";
    public static final String BOOK_PUBLISHER = "도서 출판사";
    public static final String BOOK_CATEGORY = "도서 카테고리";
    public static final String BOOK_ISBN = "도서 국제표준도서번호";
    public static final String BOOK_TAG = "도서 태그";
    public static final String BOOK_PUBLISHED_DATE = "출판일";
    public static final String BOOK_STATUS = "대출 여부";

    // 검증
    public static final String CHECK_BOOK_TITLE = "책 제목을 입력해 주세요.";
    public static final String CHECK_BOOK_AUTHOR = "작가 이름을 입력해 주세요.";
    public static final String CHECK_BOOK_ISBN = "국제표준도서번호를 입력해 주세요.";
    public static final String CHECK_BOOK_PUBLISHER = "출판사를 입력해 주세요.";
    public static final String CHECK_BOOK_PUBLISHED_DATE = "출판일을 입력해 주세요.";
    public static final String CHECK_BOOK_PastOrPresent_DATE = "과거 혹은 오늘의 날짜로 입력해주세요.";



}