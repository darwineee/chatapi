package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.user.*;
import com.darwin.dev.crmservice.core.exception.InvalidClientId;
import com.darwin.dev.crmservice.core.exception.InvalidUserId;

public interface IUserService {
    GetListUserResponse getListUserOfClient(GetListUserRequest request) throws InvalidClientId;
    GetUserResponse getUser(GetUserRequest request) throws InvalidUserId;
    CreateUserResponse createUser(CreateUserRequest request);
}
