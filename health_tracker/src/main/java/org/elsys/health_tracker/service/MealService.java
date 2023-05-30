package org.elsys.health_tracker.service;

import org.elsys.health_tracker.controller.resources.MealResource;

import java.util.List;
import java.util.Optional;

public interface MealService {
    List<MealResource> getAll();
    Optional<MealResource> getById(Long id);
    Optional<MealResource> getByName(String name);
    MealResource create(MealResource mealResource);
    MealResource update(MealResource mealResource, Long id);
    void delete(Long id);
}
