package assignment.library.domain.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanConstants {

    // 예제
    public static final String EX_LOAN_ID = "1";
    public static final String EX_LOAN_DATE = "2025-02-13";
    public static final String EX_DUE_DATE = "2025-02-20";
    public static final String EX_RETURN_DATE = "2025-02-15";
    public static final String EX_LOAN_STATUS = "반납 완료";

    // 설명
    public static final String LOAN_ID = "대출 ID";
    public static final String LOAN_DATE = "대출 날짜";
    public static final String DUE_DATE = "반납 예정 날짜";
    public static final String RETURN_DATE = "반납일";
    public static final String LOAN_STATUS = "대출 상태";

    // 검증
    public static final String LOAN_USER_ID = "대출하려는 사용자의 ID를 입력해주세요.";
    public static final String LOAN_BOOK_ID = "대출하려는 도서의 ID를 입력해주세요.";


}
