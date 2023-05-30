package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.WorkoutStatResource;

import java.util.List;
import java.util.Optional;

public interface WorkoutStatService {
    List<WorkoutStatResource> getAll();
    List<WorkoutStatResource> getAllByUserId(Long userId);
    Optional<WorkoutStatResource> getById(Long id);
    WorkoutStatResource create(WorkoutStatResource workoutStatResource);
    WorkoutStatResource update(WorkoutStatResource workoutStatResource, Long id);
    void delete(Long id);
}
