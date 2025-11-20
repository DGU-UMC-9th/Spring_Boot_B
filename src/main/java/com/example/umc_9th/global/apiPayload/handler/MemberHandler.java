package com.example.umc_9th.global.apiPayload.handler;

import com.example.umc_9th.global.apiPayload.code.status.MemberErrorCode;
import com.example.umc_9th.global.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(MemberErrorCode code) {
        super(code);
    }
}
