package org.example.week6.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.week6.apiPayload.code.SuccessStatus;
import org.example.week6.apiPayload.dto.ApiResponse;
import org.example.week6.dto.user.*;
import org.example.week6.entity.user.User;
import org.example.week6.security.JwtTokenProvider;
import org.example.week6.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ✅ 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // ✅ 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    // ✅ 사용자 조회
    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(HttpServletRequest request) { // HttpServletRequest : 헤더에서 Jwt 토큰 꺼냄
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰입니다.");
        }

        String userId = jwtTokenProvider.getUserId(token);
        User user = userService.findUserByUserId(userId);

        UserMeResponseDto responseDto = new UserMeResponseDto(
                user.getUserId(),
                user.getName(),
                user.getProfileImage()
        );

        return ResponseEntity.ok(responseDto);

    }

    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UserPasswordChangeRequestDto requestDto, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token==null||!jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.onFailure("AUTH401", "유효하지 않은 토큰입니다.", null));
        }

        String userId = jwtTokenProvider.getUserId(token);
        try {
            userService.changePassword(userId, requestDto);
            return ResponseEntity.ok(ApiResponse.of(SuccessStatus._OK, "비밀번호가 변경되었습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.onFailure("USER4001",e.getMessage(), null));
        }
    }

}