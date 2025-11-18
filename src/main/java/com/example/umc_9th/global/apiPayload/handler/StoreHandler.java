package com.example.umc_9th.global.apiPayload.handler;

import com.example.umc_9th.global.apiPayload.code.status.StoreErrorCode;
import com.example.umc_9th.global.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(StoreErrorCode code) {
        super(code);
    }
}
