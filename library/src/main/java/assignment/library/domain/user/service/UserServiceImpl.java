package assignment.library.domain.user.service;

import assignment.library.domain.user.dto.request.SignUpRequest;
import assignment.library.domain.user.entity.User;
import assignment.library.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void signUp(SignUpRequest signUpRequest) {
        User newUser = signUpRequest.toEntity();

        userRepository.save(newUser);
    }
}
