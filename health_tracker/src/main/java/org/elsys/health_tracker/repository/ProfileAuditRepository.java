package org.elsys.health_tracker.repository;

import jakarta.transaction.Transactional;
import org.elsys.health_tracker.entity.ProfileAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProfileAuditRepository extends JpaRepository<ProfileAudit, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE ProfileAudit p SET p.endDate = :endDate WHERE p.profileId = :profileId AND p.endDate IS NULL")
    void updateEndDate(@Param("profileId") Long profileId, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT p FROM ProfileAudit p WHERE p.profileId = :profileId AND p.beginDate <= :date AND (p.endDate IS NULL OR p.endDate > :date)")
    Optional<ProfileAudit> findLastVerBeforeDate(@Param("profileId") Long profileId, @Param("date") LocalDateTime date);
}
