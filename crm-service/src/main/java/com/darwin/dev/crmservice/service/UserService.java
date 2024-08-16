package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.user.request.CreateUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetListUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetUserRequest;
import com.darwin.dev.crmservice.core.dto.user.response.CreateUserResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetListUserResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUserResponse;
import com.darwin.dev.crmservice.core.exception.InvalidUserId;
import com.darwin.dev.crmservice.core.repository.IUserRepository;
import com.darwin.dev.crmservice.core.service.IUserService;
import com.darwin.dev.distributed.crm.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public GetListUserResponse getListUserOfClient(GetListUserRequest request) {
        List<User> users = userRepository.findAll(request.clientId());
        return GetListUserResponse.from(users);
    }

    @Override
    public GetUserResponse getUser(GetUserRequest request) throws InvalidUserId {
        Optional<User> user = userRepository.findById(request.userId(), request.clientId());
        if (user.isEmpty()) {
            throw new InvalidUserId();
        }
        return GetUserResponse.from(user.get());
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        User newUser = User.builder()
                .name(request.getName())
                .clientId(request.getClientId())
                .build();
        int userId = userRepository.save(newUser);
        User insertedUser = userRepository.findById(request.getClientId(), userId).orElse(newUser);
        return CreateUserResponse.from(insertedUser);
    }
}
