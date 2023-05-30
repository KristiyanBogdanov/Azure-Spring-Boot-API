package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.MealStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealStatRepository extends JpaRepository<MealStat, Long> {
    List<MealStat> findAllByUserId(Long userId);
}
