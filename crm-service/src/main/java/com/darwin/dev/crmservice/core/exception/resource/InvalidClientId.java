package com.darwin.dev.crmservice.core.exception.resource;

import com.darwin.dev.distributed.exception.ResourceNotFoundException;
import com.darwin.dev.distributed.util.ErrCode;
import com.darwin.dev.distributed.util.Msg;

public class InvalidClientId extends ResourceNotFoundException {
    @Override
    public String getMessage() {
        return Msg.INVALID_CLIENT_ID;
    }

    @Override
    public int getErrCode() {
        return ErrCode.INVALID_CLIENT_ID;
    }
}
