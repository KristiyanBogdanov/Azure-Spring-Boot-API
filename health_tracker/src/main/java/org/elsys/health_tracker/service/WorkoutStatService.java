package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.UserWorkoutStatResource;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;

import java.util.List;

public interface WorkoutStatService {
    List<WorkoutStatResource> getAll();
    WorkoutStatResource getById(Long id);
    List<UserWorkoutStatResource> getAllByUserId(Long userId);
    WorkoutStatResource create(WorkoutStatResource workoutStatResource);
    WorkoutStatResource update(WorkoutStatResource workoutStatResource, Long id);
    void delete(Long id);
}
