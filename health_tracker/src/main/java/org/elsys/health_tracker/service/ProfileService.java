package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Gender;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<ProfileResource> getAll();
    ProfileResource getById(Long id);
    List<Gender> getAllGenders();
//    ProfileResource findLastRevisionBeforeDate(Long profileId, String date);
    ProfileResource update(ProfileResource profileResource, Long id);
    void delete(Long id);
}
