package assignment.library.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status; // HTTP 상태 코드
    private String error; // 오류 유형
    private String message; // 상세 오류 메시지
}
