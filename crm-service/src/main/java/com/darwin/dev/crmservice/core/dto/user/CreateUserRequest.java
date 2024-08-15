package com.darwin.dev.crmservice.core.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest{
    private int clientId;
    private String name;
}