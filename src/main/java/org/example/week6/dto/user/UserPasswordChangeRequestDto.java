package org.example.week6.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordChangeRequestDto {

    @NotBlank(message = "현재 비밀번호를 입력해 주세요.")
    private String currentPassword;

    @NotBlank(message = "새 비밀번호를 입력해 주세요.")
    @Size(min = 8, message = "최소 8자 이상으로 입력해 주세요.")
    private String newPassword;

    @NotBlank(message = "새 비밀번호를 한 번 더 입력해 주세요.")
    private String confirmPassword;
}
