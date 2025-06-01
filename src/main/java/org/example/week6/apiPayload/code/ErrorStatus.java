package org.example.week6.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.week6.apiPayload.dto.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청 입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"MEMBER4001","해당 사용자가 없습니다."),
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용 예외입니다."),
    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER4001", "이미 존재하는 아이디입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }
    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).httpStatus(httpStatus).build();
    }
}