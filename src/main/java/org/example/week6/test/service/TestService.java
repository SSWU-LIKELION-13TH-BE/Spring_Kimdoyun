package org.example.week6.test.service;

import org.example.week6.apiPayload.code.ErrorStatus;
import org.example.week6.apiPayload.exception.GeneralException;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void checkFlag(Integer flag) {
        if (flag != null && flag == 1) {
            throw new GeneralException(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
