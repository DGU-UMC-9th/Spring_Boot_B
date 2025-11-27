package com.example.umc9th2.domain.member.exception;

import com.example.umc9th2.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}