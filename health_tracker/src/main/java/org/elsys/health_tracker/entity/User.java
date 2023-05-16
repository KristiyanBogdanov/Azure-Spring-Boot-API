package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR(30)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(64)")
    private String password; // sha256

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SleepStat> sleepStats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MealStat> mealStats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WorkoutStat> workoutStats;
}
