package org.example.week6.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordChangeRequestDto {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
