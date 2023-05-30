package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.MealStatResource;

import java.util.List;
import java.util.Optional;

public interface MealStatService {
    List<MealStatResource> getAll();
    List<MealStatResource> getAllByUserId(Long userId);
    Optional<MealStatResource> getById(Long id);
    MealStatResource create(MealStatResource mealStatResource);
    MealStatResource update(MealStatResource mealStatResource, Long id);
    void delete(Long id);
}
