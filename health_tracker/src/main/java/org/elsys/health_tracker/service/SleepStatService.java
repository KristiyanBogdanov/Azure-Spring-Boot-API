package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.controller.resources.UserSleepStatResource;
import org.elsys.health_tracker.entity.QualityStatus;

import java.util.List;

public interface SleepStatService {
    List<SleepStatResource> getAll();
    SleepStatResource getById(Long id);
    List<QualityStatus> getAllQualityStatuses();
    List<UserSleepStatResource> getAllByUserId(Long userId);
    SleepStatResource create(SleepStatResource sleepStatResource);
    SleepStatResource update(SleepStatResource sleepStatResource, Long id);
    void delete(Long id);
}
