package org.elsys.health_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false, referencedColumnName = "id")
    private Profile profile;

    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR(30)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(72)")
    private String password; // bcrypt hash

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SleepStat> sleepStats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealStat> mealStats;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutStat> workoutStats;
}
