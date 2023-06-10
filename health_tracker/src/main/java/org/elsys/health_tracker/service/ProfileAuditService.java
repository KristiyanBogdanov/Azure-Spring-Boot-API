package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Profile;

import java.time.LocalDateTime;

public interface ProfileAuditService {
    void afterInsert(Profile profile);
    void afterUpdate(Profile profile);
    void afterDelete(Long profileId);
    ProfileResource getLastVerBeforeDate(Long profileId, LocalDateTime date);
}
