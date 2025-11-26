package com.example.umc_9th.global.apiPayload.handler;


import com.example.umc_9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_9th.global.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}