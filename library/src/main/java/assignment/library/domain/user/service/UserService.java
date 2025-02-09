package assignment.library.domain.user.service;

import assignment.library.domain.user.dto.request.SignUpRequest;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);
}
