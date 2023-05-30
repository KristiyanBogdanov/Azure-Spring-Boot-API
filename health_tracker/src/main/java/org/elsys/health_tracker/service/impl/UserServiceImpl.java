package org.elsys.health_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.UserResource;
import org.elsys.health_tracker.entity.User;
import org.elsys.health_tracker.repository.UserRepository;
import org.elsys.health_tracker.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elsys.health_tracker.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final MealStatService mealStatService;
    private final SleepStatService sleepStatService;
    private final WorkoutStatService workoutStatService;

    @Override
    public List<UserResource> getAll() {
        return USER_MAPPER.toUserResources(userRepository.findAll());
    }

    @Override
    public Optional<UserResource> getById(Long id) {
        return userRepository.findById(id).map(USER_MAPPER::toUserResource);
    }

    @Override
    public UserResource create(UserResource userResource) {
        User user = userRepository.save(USER_MAPPER.fromUserResource(userResource));
        userResource.setId(user.getId());
        return userResource;
    }

    @Override
    public UserResource update(UserResource userResource, Long id) {
        User user = userRepository.getReferenceById(id);

        // TODO: I know
        user.setUsername(userResource.getUsername());
        user.setPassword(userResource.getPassword());
        profileService.update(userResource.getProfile(), user.getProfile().getId());

        return USER_MAPPER.toUserResource(userRepository.save(user));
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
