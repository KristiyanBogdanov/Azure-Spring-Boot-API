package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.SleepQualityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepQualityStatusRepository extends JpaRepository<SleepQualityStatus, Long> {
}
