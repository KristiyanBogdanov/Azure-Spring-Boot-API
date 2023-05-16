package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.WorkoutStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutStatRepository extends JpaRepository<WorkoutStat, Long> {
}
