package org.example.week6.apiPayload.service;

import lombok.RequiredArgsConstructor;
import org.example.week6.apiPayload.Repository.AuthRepository;
import org.example.week6.apiPayload.code.ErrorStatus;
import org.example.week6.apiPayload.dto.SignupRequestDTO;
import org.example.week6.apiPayload.exception.GeneralException;
import org.example.week6.apiPayload.entity.Auth;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void signup(SignupRequestDTO request) {
        if (authRepository.existsByUsername(request.getUsername())) {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        Auth auth = new Auth(
                request.getUsername(),
                request.getPassword(),
                request.getName()
        );

        authRepository.save(auth);
    }
}
