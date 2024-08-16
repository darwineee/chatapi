package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.user.request.CreateUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetListUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetUserRequest;
import com.darwin.dev.crmservice.core.dto.user.response.CreateUserResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetListUserResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUserResponse;
import com.darwin.dev.crmservice.core.exception.InvalidUserId;

public interface IUserService {
    GetListUserResponse getListUserOfClient(GetListUserRequest request);
    GetUserResponse getUser(GetUserRequest request) throws InvalidUserId;
    CreateUserResponse createUser(CreateUserRequest request);
}
