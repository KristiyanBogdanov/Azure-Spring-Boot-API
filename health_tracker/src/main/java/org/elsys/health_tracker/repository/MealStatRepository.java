package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.MealStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealStatRepository extends JpaRepository<MealStat, Long> {
}
