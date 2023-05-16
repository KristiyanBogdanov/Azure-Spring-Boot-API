package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "Workouts")
@Data
public class WorkoutStat {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "duration", nullable = false, columnDefinition = "INTEGER")
    private Integer duration;

    @Column(name = "burned_calories", nullable = false, columnDefinition = "INTEGER")
    private Integer burnedCalories;

    @Column(name = "workout_bio", columnDefinition = "TEXT")
    private String workoutBio;

    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private Date date;
}
