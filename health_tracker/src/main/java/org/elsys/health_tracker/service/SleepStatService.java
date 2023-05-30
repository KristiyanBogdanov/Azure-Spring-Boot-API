package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.SleepStatResource;

import java.util.List;
import java.util.Optional;

public interface SleepStatService {
    List<SleepStatResource> getAll();
    List<SleepStatResource> getAllByUserId(Long userId);
    Optional<SleepStatResource> getById(Long id);
    SleepStatResource create(SleepStatResource sleepStatResource);
    SleepStatResource update(SleepStatResource sleepStatResource, Long id);
    void delete(Long id);
}
