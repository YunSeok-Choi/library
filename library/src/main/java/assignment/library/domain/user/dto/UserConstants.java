package assignment.library.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserConstants {
    // 예제
    public static final String EX_USER_ID = "1";
    public static final String EX_USER_NAME = "홍길동";
    public static final String EX_USER_EMAIL = "goodhong@gmail.com";
    public static final String EX_USER_PASSWORD = "iloveu1512";

    // 설명
    public static final String USER_ID = "사용자 ID";
    public static final String USER_NAME = "사용자 이름";
    public static final String USER_EMAIL = "사용자 이메일";
    public static final String USER_PASSWORD = "사용자 비밀번호";

    // 검증
    public static final String CHECK_USER_ID = "사용자 ID를 입력해주세요.";
    public static final String CHECK_USER_NAME_MAX = "사용자 이름은 32자가 넘을 수 없습니다";
    public static final String CHECK_USER_NAME = "사용자 이름을 입력해주세요.";
    public static final String CHECK_USER_EMAIL = "사용자 이메일을 입력해주세요.";
    public static final String CHECK_USER_EMAIL_FORM = "xxxx@xxxx.com 형식으로 입력해주세요";
    public static final String CHECK_USER_PASSWORD = "사용자 비밀번호를 입력해주세요.";
    public static final String CHECK_USER_PASSWORD_MAX = "사용자 비밀번호는 32자가 넘을 수 없습니다";
}
