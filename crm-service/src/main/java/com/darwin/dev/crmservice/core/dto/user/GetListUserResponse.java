package com.darwin.dev.crmservice.core.dto.user;

import com.darwin.dev.distributed.crm.User;
import lombok.Builder;

import java.util.List;

@Builder
public record GetListUserResponse(
        List<User> users
) {
}
