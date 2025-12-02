package com.example.umc9th2.global.apiPayload.handler;

import com.example.umc9th2.global.apiPayload.code.status.StoreErrorCode;
import com.example.umc9th2.global.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(StoreErrorCode code) {
        super(code);
    }
}
