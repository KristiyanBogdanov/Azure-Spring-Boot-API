package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserResource {
    private Long id;

    @NotNull
    private ProfileResource profile;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @JsonProperty("sleep_stats")
    private List<SleepStatResource> sleepStats;

    @JsonProperty("meal_stats")
    private List<MealStatResource> mealStats;

    @JsonProperty("workout_stats")
    private List<WorkoutStatResource> workoutStats;
}
