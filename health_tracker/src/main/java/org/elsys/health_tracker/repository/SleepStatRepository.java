package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.SleepStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepStatRepository extends JpaRepository<SleepStat, Long> {
}
