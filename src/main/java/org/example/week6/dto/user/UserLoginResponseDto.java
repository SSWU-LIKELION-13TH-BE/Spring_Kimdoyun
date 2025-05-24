package org.example.week6.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

//로그인 응답 DTO
@Data
@AllArgsConstructor
public class UserLoginResponseDto {
    private String userId;
    private String token;
}
