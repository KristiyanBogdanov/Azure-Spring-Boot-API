package org.elsys.health_tracker.repository;

import org.elsys.health_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteByProfileId(Long profileId);
}
