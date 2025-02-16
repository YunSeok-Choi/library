package assignment.library.domain.user.service;

import assignment.library.domain.user.dto.request.SignUpRequest;
import assignment.library.domain.user.dto.response.UserInfoResponse;
import assignment.library.domain.user.entity.User;
import assignment.library.domain.user.repository.UserCustomRepository;
import assignment.library.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        User newUser = signUpRequest.toEntity();

        userRepository.save(newUser);
    }

    @Override
    public List<UserInfoResponse> getUserInfo(Long userId) {
        return userCustomRepository.getUsersInfo(userId);
    }
}
