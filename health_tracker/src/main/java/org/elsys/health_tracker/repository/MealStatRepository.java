package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.MealStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealStatRepository extends JpaRepository<MealStat, Long> {
    Optional<List<MealStat>> findAllByUserId(Long userId);
}
