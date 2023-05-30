package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "SleepStats")
@Data
public class SleepStat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "duration", nullable = false, columnDefinition = "INTEGER")
    private Integer duration;

    @Column(name = "quality_status", nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    @Enumerated(EnumType.STRING)
    private QualityStatus qualityStatus;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;
}
