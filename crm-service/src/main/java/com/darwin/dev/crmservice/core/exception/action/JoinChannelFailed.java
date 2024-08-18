package com.darwin.dev.crmservice.core.exception.action;

import com.darwin.dev.distributed.exception.ActionFailException;
import com.darwin.dev.distributed.util.ErrCode;
import com.darwin.dev.distributed.util.Msg;

public class JoinChannelFailed extends ActionFailException {

    @Override
    public int getErrCode() {
        return ErrCode.JOIN_CHANNEL_FAILED;
    }

    @Override
    public String getMessage() {
        return Msg.JOIN_CHANNEL_FAILED;
    }
}
