package com.darwin.dev.crmservice.core.exception.resource;

import com.darwin.dev.distributed.exception.ResourceNotFoundException;
import com.darwin.dev.distributed.util.ErrCode;
import com.darwin.dev.distributed.util.Msg;

public class InvalidUserId extends ResourceNotFoundException {
    @Override
    public String getMessage() {
        return Msg.INVALID_USER_ID;
    }

    @Override
    public int getErrCode() {
        return ErrCode.INVALID_USER_ID;
    }
}
