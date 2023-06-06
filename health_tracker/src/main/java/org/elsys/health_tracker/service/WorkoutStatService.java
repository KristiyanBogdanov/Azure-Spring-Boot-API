package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.UserWorkoutStatResource;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;

import java.util.List;
import java.util.Optional;

public interface WorkoutStatService {
    List<WorkoutStatResource> getAll();
    Optional<WorkoutStatResource> getById(Long id);
    Optional<List<UserWorkoutStatResource>> getAllByUserId(Long userId);
    WorkoutStatResource create(WorkoutStatResource workoutStatResource);
    WorkoutStatResource update(WorkoutStatResource workoutStatResource, Long id);
    void delete(Long id);
}
