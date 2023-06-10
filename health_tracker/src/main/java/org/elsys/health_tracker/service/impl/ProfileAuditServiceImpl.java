package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Profile;
import org.elsys.health_tracker.entity.ProfileAudit;
import org.elsys.health_tracker.repository.ProfileAuditRepository;
import org.elsys.health_tracker.service.ProfileAuditService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.elsys.health_tracker.mapper.ProfileAuditMapper.PROFILE_AUDIT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProfileAuditServiceImpl implements ProfileAuditService {
    private final ProfileAuditRepository profileAuditRepository;

    @Override
    public void afterInsert(Profile profile) {
        ProfileAudit profileAudit = new ProfileAudit(profile);
        profileAudit.setBeginDate(LocalDateTime.now());
        profileAuditRepository.save(profileAudit);
    }

    @Override
    public void afterUpdate(Profile profile) {
        profileAuditRepository.updateEndDate(profile.getId(), LocalDateTime.now());
        ProfileAudit profileAudit = new ProfileAudit(profile);
        profileAudit.setBeginDate(LocalDateTime.now());
        profileAuditRepository.save(profileAudit);
    }

    @Override
    public void afterDelete(Long profileId) {
        profileAuditRepository.updateEndDate(profileId, LocalDateTime.now());
    }

    @Override
    public ProfileResource getLastVerBeforeDate(Long profileId, LocalDateTime date) {
        ProfileAudit profileAudit = profileAuditRepository.findLastVerBeforeDate(profileId, date)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find profile audit with profile id " + profileId + " and date " + date + "."));

        return PROFILE_AUDIT_MAPPER.toProfileResource(profileAudit);
    }
}
