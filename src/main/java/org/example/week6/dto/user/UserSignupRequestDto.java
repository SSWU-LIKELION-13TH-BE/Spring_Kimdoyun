package org.example.week6.dto.user;


import lombok.Data;

//회원가입 요청 DTO
@Data
public class UserSignupRequestDto {
    private String userId;
    private String password;
    private String name;
    private String profileImage;
}
