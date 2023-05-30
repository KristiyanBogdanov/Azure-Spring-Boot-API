package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
}
