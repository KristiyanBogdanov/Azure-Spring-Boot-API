package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.controller.resources.UserSleepStatResource;
import org.elsys.health_tracker.entity.QualityStatus;

import java.util.List;
import java.util.Optional;

public interface SleepStatService {
    List<SleepStatResource> getAll();
    Optional<SleepStatResource> getById(Long id);
    List<QualityStatus> getAllQualityStatuses();
    Optional<List<UserSleepStatResource>> getAllByUserId(Long userId);
    SleepStatResource create(SleepStatResource sleepStatResource);
    SleepStatResource update(SleepStatResource sleepStatResource, Long id);
    void delete(Long id);
}
