package com.darwin.dev.crmservice.core.exception;

import com.darwin.dev.distributed.exception.ApiException;
import com.darwin.dev.distributed.util.ErrCode;
import com.darwin.dev.distributed.util.Msg;

public class InvalidChannelId extends ApiException {

    @Override
    public String getMessage() {
        return Msg.INVALID_CHANNEL_ID;
    }

    @Override
    public int getErrCode() {
        return ErrCode.INVALID_CHANNEL_ID;
    }
}
