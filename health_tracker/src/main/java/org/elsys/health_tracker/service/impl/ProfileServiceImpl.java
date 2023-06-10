package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Gender;
import org.elsys.health_tracker.entity.Profile;
import org.elsys.health_tracker.exception.DuplicateEntityFieldException;
import org.elsys.health_tracker.mapper.DateMapper;
import org.elsys.health_tracker.repository.ProfileRepository;
import org.elsys.health_tracker.service.ProfileAuditService;
import org.elsys.health_tracker.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.elsys.health_tracker.mapper.ProfileMapper.PROFILE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileAuditService profileAuditService;

    @Override
    public List<ProfileResource> getAll() {
        return PROFILE_MAPPER.toProfileResources(profileRepository.findAll());
    }

    @Override
    public ProfileResource getById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find profile with id " + id + "."));

        return PROFILE_MAPPER.toProfileResource(profile);
    }

    @Override
    public List<Gender> getAllGenders() {
        return List.of(Gender.values());
    }

    private boolean isProfileUnchanged(ProfileResource profileResource, Profile profile) {
        return profileResource.getGender().equals(profile.getGender())
                && profileResource.getBirthday().equals(DateMapper.toString(profile.getBirthday()))
                && profileResource.getHeight().equals(profile.getHeight())
                && profileResource.getWeight().equals(profile.getWeight())
                && profileResource.getBodyFat().equals(profile.getBodyFat())
                && profileResource.getHealthBio().equals(profile.getHealthBio());
    }

    @Override
    public ProfileResource update(ProfileResource profileResource, Long id) {
        try {
            Profile profile = profileRepository.getReferenceById(id);

            if (isProfileUnchanged(profileResource, profile)) {
                throw new DuplicateEntityFieldException("Profile is unchanged.");
            }

            profile.setGender(profileResource.getGender());
            profile.setBirthday(DateMapper.toSQLDate(profileResource.getBirthday()));
            profile.setHeight(profileResource.getHeight());
            profile.setWeight(profileResource.getWeight());
            profile.setBodyFat(profileResource.getBodyFat());
            profile.setHealthBio(profileResource.getHealthBio());

            Profile savedProfile = profileRepository.save(profile);
            profileAuditService.afterUpdate(profile);

            return PROFILE_MAPPER.toProfileResource(savedProfile);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Unable to find profile with id " + id + ".");
        }
    }

    @Override
    public void delete(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            profileAuditService.afterDelete(id);
        } else {
            throw new EntityNotFoundException("Unable to find profile with id " + id + ".");
        }
    }
}
