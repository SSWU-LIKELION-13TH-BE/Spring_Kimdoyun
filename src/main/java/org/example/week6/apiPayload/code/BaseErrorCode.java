package org.example.week6.apiPayload.code;

import org.example.week6.apiPayload.dto.ErrorReasonDTO;

public interface BaseErrorCode {
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();
}
