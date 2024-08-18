package com.darwin.dev.crmservice.core.exception.action;

import com.darwin.dev.distributed.exception.ActionFailException;
import com.darwin.dev.distributed.util.ErrCode;
import com.darwin.dev.distributed.util.Msg;

public class DeleteChannelFailed extends ActionFailException {

    @Override
    public int getErrCode() {
        return ErrCode.DELETED_CHANNEL_FAILED;
    }

    @Override
    public String getMessage() {
        return Msg.DELETE_CHANNEL_FAILED;
    }
}
