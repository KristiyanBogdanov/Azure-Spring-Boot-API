package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SleepQualityStatuses")
@Data
public class SleepQualityStatus {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "status", nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    private String status;
}
