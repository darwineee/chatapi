package com.darwin.dev.crmservice.controller.api.v1;

import com.darwin.dev.crmservice.core.dto.user.request.CreateUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetListUserRequest;
import com.darwin.dev.crmservice.core.dto.user.request.GetUserRequest;
import com.darwin.dev.crmservice.core.dto.user.response.CreateUserResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUsersResponse;
import com.darwin.dev.crmservice.core.dto.user.response.GetUserResponse;
import com.darwin.dev.crmservice.core.exception.resource.InvalidUserId;
import com.darwin.dev.crmservice.core.service.IUserService;
import com.darwin.dev.distributed.util.RequestCst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    ResponseEntity<GetUsersResponse> getUsers(
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        GetUsersResponse rsp = userService.getListUserOfClient(new GetListUserRequest(clientId));
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/{userId}")
    ResponseEntity<GetUserResponse> getUser(
            @PathVariable int userId,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) throws InvalidUserId {
        GetUserResponse rsp = userService.getUser(new GetUserRequest(userId, clientId));
        return ResponseEntity.ok(rsp);
    }

    @PostMapping
    ResponseEntity<CreateUserResponse> createUser(
            @RequestBody CreateUserRequest request,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        request.setClientId(clientId);
        CreateUserResponse rsp = userService.createUser(request);
        return ResponseEntity.ok(rsp);
    }
}
