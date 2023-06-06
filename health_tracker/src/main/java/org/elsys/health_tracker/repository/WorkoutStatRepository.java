package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.WorkoutStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutStatRepository extends JpaRepository<WorkoutStat, Long> {
    Optional<List<WorkoutStat>> findAllByUserId(Long userId);
}
