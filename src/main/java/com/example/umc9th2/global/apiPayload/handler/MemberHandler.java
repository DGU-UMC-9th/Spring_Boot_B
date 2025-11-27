package com.example.umc9th2.global.apiPayload.handler;

import com.example.umc9th2.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(MemberErrorCode code) {
        super(code);
    }
}
