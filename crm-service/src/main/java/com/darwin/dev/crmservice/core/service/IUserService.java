package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.user.request.CreateUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetListUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetUserRequest;
import com.darwin.dev.crmservice.core.dto.user.response.CreateUserResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUsersResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUserResponse;
import com.darwin.dev.crmservice.core.exception.resource.InvalidUserId;

public interface IUserService {
    GetUsersResponse getListUserOfClient(GetListUserRequest request);
    GetUserResponse getUser(GetUserRequest request) throws InvalidUserId;
    CreateUserResponse createUser(CreateUserRequest request);
}
