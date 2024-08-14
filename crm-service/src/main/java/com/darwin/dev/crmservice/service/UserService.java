package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.user.*;
import com.darwin.dev.crmservice.core.exception.InvalidClientId;
import com.darwin.dev.crmservice.core.exception.InvalidUserId;
import com.darwin.dev.crmservice.core.repository.IUserRepository;
import com.darwin.dev.crmservice.core.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public GetListUserResponse getListUserOfClient(GetListUserRequest request) throws InvalidClientId {
        return null;
    }

    @Override
    public GetUserResponse getUser(GetUserRequest request) throws InvalidUserId {
        return null;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        return null;
    }
}
