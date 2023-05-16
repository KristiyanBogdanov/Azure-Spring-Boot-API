package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "SleepStats")
@Data
public class SleepStat {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "duration", nullable = false, columnDefinition = "INTEGER")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "quality_status_id", nullable = false, referencedColumnName = "id")
    private SleepQualityStatus qualityStatus;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private Date date;
}
