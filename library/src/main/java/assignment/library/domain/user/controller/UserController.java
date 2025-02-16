package assignment.library.domain.user.controller;

import assignment.library.domain.loan.dto.response.LoanStatusResponse;
import assignment.library.domain.user.dto.request.SignUpRequest;
import assignment.library.domain.user.dto.response.UserInfoResponse;
import assignment.library.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static assignment.library.domain.book.dto.BookConstants.BOOK_ID;
import static assignment.library.domain.book.dto.BookConstants.EX_BOOK_ID;
import static assignment.library.domain.user.dto.UserConstants.*;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    @Operation(summary = "회원가입", description = "회원가입 API", responses = {
            @ApiResponse(
                    responseCode = "201",
                    description = "회원가입 성공")
    })
    public ResponseEntity<?> signUp(@Validated @RequestBody SignUpRequest signUpRequest) {
        log.info(LOG_USER_SIGNUP);
        userService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/info")
    @Operation(summary = "사용자 정보 조회", description = "사용자 정보 조회 API", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(
                    responseCode = "204",
                    description = "존재하지 않는 리소스 접근")
    })
    public ResponseEntity<?> userInfo(
            @Parameter(name = "userId", description = USER_ID) @RequestParam(required = false) Long userId) {
        log.info(LOG_USER_INFO);
        List<UserInfoResponse> userInfo = userService.getUserInfo(userId);
        if (userInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(OK).body(userInfo);
    }


}
