package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.UserResource;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResource> getAll();
    Optional<UserResource> getById(Long id);
    UserResource create(UserResource userResource);
    UserResource update(UserResource userResource, Long id);
    void delete(Long id);
}
