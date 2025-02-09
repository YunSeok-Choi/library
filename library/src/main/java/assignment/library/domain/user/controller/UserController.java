package assignment.library.domain.user.controller;

import assignment.library.domain.user.dto.request.SignUpRequest;
import assignment.library.domain.user.dto.response.UserInfoResponse;
import assignment.library.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "사용자 관련 API")
public class UserController {

    private final UserService userService;

    // 사용자 등록
    @PostMapping("/signUp")
    @Operation(summary = "회원가입", description = "회원가입 API")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 사용자 조회
    @GetMapping("/info")
    @Operation(summary = "사용자 정보 조회", description = "사용자 정보 조회 API")
    public ResponseEntity<?> userInfo(@RequestParam(required = false) Long userId) {

        List<UserInfoResponse> userInfo = userService.getUserInfo(userId);
        if (userInfo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(OK).body(userInfo);
    }


}
