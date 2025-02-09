package assignment.library.domain.user.service;

import assignment.library.domain.user.dto.request.SignUpRequest;
import assignment.library.domain.user.dto.response.UserInfoResponse;

import java.util.List;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
    List<UserInfoResponse> getUserInfo(Long userId);
}
