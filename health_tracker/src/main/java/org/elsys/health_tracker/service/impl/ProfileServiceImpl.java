package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Gender;
import org.elsys.health_tracker.entity.Profile;
import org.elsys.health_tracker.mapper.DateMapper;
import org.elsys.health_tracker.repository.ProfileRepository;
import org.elsys.health_tracker.service.ProfileService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elsys.health_tracker.mapper.ProfileMapper.PROFILE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public List<ProfileResource> getAll() {
        return PROFILE_MAPPER.toProfileResources(profileRepository.findAll());
    }

    @Override
    public Optional<ProfileResource> getById(Long id) {
        return profileRepository.findById(id).map(PROFILE_MAPPER::toProfileResource);
    }

    @Override
    public List<Gender> getAllGenders() {
        return List.of(Gender.values());
    }

    @Override
    public ProfileResource create(ProfileResource profileResource) {
        try {
            Profile profile = profileRepository.save(PROFILE_MAPPER.fromProfileResource(profileResource));
            profileResource.setId(profile.getId());
            return profileResource;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Profile with id " + profileResource.getId() + " already exists.");
        }
    }

    @Override
    public ProfileResource update(ProfileResource profileResource, Long id) {
        try {
            Profile profile = profileRepository.getReferenceById(id);

            profile.setGender(profileResource.getGender());
            profile.setBirthday(DateMapper.toSQLDate(profileResource.getBirthday()));
            profile.setHeight(profileResource.getHeight());
            profile.setWeight(profileResource.getWeight());
            profile.setBodyFat(profileResource.getBodyFat());
            profile.setHealthBio(profileResource.getHealthBio());

            return PROFILE_MAPPER.toProfileResource(profileRepository.save(profile));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Unable to find profile with id " + id + ".");
        }
    }

    @Override
    public void delete(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find profile with id " + id + ".");
        }
    }
}
