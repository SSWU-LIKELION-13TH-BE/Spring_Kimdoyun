package org.example.week6.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserMeResponseDto {
    private final String userId;
    private final String name;
    private final String profileImage;
}
