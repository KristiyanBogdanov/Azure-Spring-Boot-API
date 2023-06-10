package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.UserResource;

import java.util.List;

public interface UserService {
    List<UserResource> getAll();
    UserResource getById(Long id);
    UserResource update(UserResource userResource, Long id);
    void delete(Long id);
}
