package org.example.week6.apiPayload.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.week6.apiPayload.dto.ApiResponse;
import org.example.week6.apiPayload.dto.SignupRequestDTO;
import org.example.week6.apiPayload.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody @Valid SignupRequestDTO request) {
        authService.signup(request);
        return ApiResponse.onSuccess("회원가입 성공");
    }
}
