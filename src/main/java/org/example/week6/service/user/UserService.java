package org.example.week6.service.user;

import org.example.week6.apiPayload.code.ErrorStatus;
import org.example.week6.apiPayload.code.SuccessStatus;
import org.example.week6.apiPayload.dto.ApiResponse;
import org.example.week6.apiPayload.exception.GeneralException;
import org.example.week6.dto.user.UserLoginRequestDto;
import org.example.week6.dto.user.UserLoginResponseDto;
import org.example.week6.dto.user.UserPasswordChangeRequestDto;
import org.example.week6.dto.user.UserSignupRequestDto;
import org.example.week6.entity.user.User;
import org.example.week6.repository.user.UserRepository;
import org.example.week6.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 🔑 회원가입 (비밀번호 암호화 후 저장)
    public void signup(UserSignupRequestDto requestDto) {
        User user = new User();
        user.setUserId(requestDto.getUserId());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setName(requestDto.getName());
        user.setProfileImage(requestDto.getProfileImage());

        userRepository.save(user);
    }

    // 🔐 로그인 (ID/PW 검증 후 JWT 발급)
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + requestDto.getUserId()));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        String token = jwtTokenProvider.createToken(user.getUserId());
        return new UserLoginResponseDto(user.getUserId(), token);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserId())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
    }


    public ApiResponse<String> changePassword(String userId, UserPasswordChangeRequestDto requestDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_MISMATCH);
        }

        if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_CONFIRM_MISMATCH);
        }

        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        userRepository.save(user);


        return ApiResponse.of(SuccessStatus._OK, "비밀번호가 변경되었습니다.");

    }
}
