package org.example.week6.apiPayload.code;

import org.example.week6.apiPayload.dto.ReasonDTO;

public interface BaseCode {
    ReasonDTO getReason(); // 메시지 및 코드 반환
    ReasonDTO getReasonHttpStatus(); // 메시지 + HTTP 상태 포함 반환
}