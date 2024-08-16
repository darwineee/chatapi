package com.darwin.dev.crmservice.controller.api.v1;

import com.darwin.dev.crmservice.core.dto.user.*;
import com.darwin.dev.crmservice.core.exception.InvalidUserId;
import com.darwin.dev.crmservice.service.UserService;
import com.darwin.dev.distributed.util.RequestCst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    ResponseEntity<GetListUserResponse> getListUser(
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        GetListUserResponse rsp = userService.getListUserOfClient(new GetListUserRequest(clientId));
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<GetUserResponse> getUser(
            @PathVariable int userId
    ) throws InvalidUserId {
        GetUserResponse rsp = userService.getUser(new GetUserRequest(userId));
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/users")
    ResponseEntity<CreateUserResponse> createUser(
            @RequestBody
            @Valid
            CreateUserRequest request,
            @RequestParam(RequestCst.CLIENT_ID) int clientId
    ) {
        request.setClientId(clientId);
        CreateUserResponse rsp = userService.createUser(request);
        return ResponseEntity.ok(rsp);
    }
}
