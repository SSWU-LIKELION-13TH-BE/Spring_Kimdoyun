package org.example.week6.apiPayload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {

    @NotBlank(message = "username은 필수입니다.")
    @Size(min = 2, message = "username은 최소 2글자 이상이어야 합니다.")
    private String username;

    @NotBlank(message = "password는 필수입니다.")
    @Size(min = 2, message = "password는 최소 2글자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "name은 필수입니다.")
    private String name;

}

