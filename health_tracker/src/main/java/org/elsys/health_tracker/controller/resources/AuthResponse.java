package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AuthResponse {
    private Long id;
    private String username;
    private ProfileResource profile;

    @JsonProperty("sleep_stats")
    private List<UserSleepStatResource> sleepStats;

    @JsonProperty("meal_stats")
    private List<UserMealStatResource> mealStats;

    @JsonProperty("workout_stats")
    private List<UserWorkoutStatResource> workoutStats;
}
