package com.darwin.dev.crmservice.core.repository;

import com.darwin.dev.distributed.crm.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> findAll(int clientId);
    Optional<User> findById(int userId);
    int save(User user);
}
