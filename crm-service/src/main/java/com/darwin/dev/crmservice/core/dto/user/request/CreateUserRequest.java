package com.darwin.dev.crmservice.core.dto.user.request;

import lombok.Data;

@Data
public class CreateUserRequest{
    private int clientId;
    private String name;
}