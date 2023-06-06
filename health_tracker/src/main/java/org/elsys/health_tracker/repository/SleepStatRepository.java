package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.SleepStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SleepStatRepository extends JpaRepository<SleepStat, Long> {
    Optional<List<SleepStat>> findAllByUserId(Long userId);
}
