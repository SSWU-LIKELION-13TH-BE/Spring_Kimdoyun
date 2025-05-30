package org.example.week6.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.week6.apiPayload.code.BaseErrorCode;
import org.example.week6.apiPayload.dto.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
