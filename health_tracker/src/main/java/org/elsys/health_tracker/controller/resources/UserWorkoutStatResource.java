package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserWorkoutStatResource {
    private Long id;

    @Min(1)
    private Integer duration;

    @Min(1)
    @JsonProperty("burned_calories")
    private Integer burnedCalories;

    @JsonProperty("workout_bio")
    private String workoutBio;

    @NotEmpty
    private String date;
}
