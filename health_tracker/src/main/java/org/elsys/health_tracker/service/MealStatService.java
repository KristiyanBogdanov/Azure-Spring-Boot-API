package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.MealStatResource;
import org.elsys.health_tracker.controller.resources.UserMealStatResource;

import java.util.List;

public interface MealStatService {
    List<MealStatResource> getAll();
    MealStatResource getById(Long id);
    List<UserMealStatResource> getAllByUserId(Long userId);
    MealStatResource create(MealStatResource mealStatResource);
    MealStatResource update(MealStatResource mealStatResource, Long id);
    void delete(Long id);
}
