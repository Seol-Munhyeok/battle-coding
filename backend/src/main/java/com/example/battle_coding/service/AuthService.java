package com.example.battle_coding.service;

import com.example.battle_coding.dto.LoginRequestDto;
import com.example.battle_coding.dto.SignupRequestDto;
import com.example.battle_coding.entity.User;
import com.example.battle_coding.repository.UserRepository;
import com.example.battle_coding.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String signup(SignupRequestDto request) {

        validateEmailDuplicate(request.email());

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = User.builder()
                .email(request.email())
                .password(encodedPassword)
                .provider(request.provider())
                .providerId(request.providerId())
                .build();

        userRepository.save(user);
        return "회원가입 성공!!";
    }

    public String login(LoginRequestDto request) {
        User user = validateUserCredentials(request.email(), request.password());
        return jwtTokenProvider.createAccessToken(user.getEmail());
    }

    private User validateUserCredentials(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    private void validateEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }


}
public void logout(String token) {
    jwtTokenProvider.invalidateToken(token);
}
