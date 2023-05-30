package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class WorkoutStatResource {
    private Long id;

    @DecimalMin(value = "0", inclusive = false)
    private Integer duration;

    @DecimalMin(value = "0", inclusive = false)
    @JsonProperty("burned_calories")
    private Integer burnedCalories;

    @JsonProperty("workout_bio")
    private String workoutBio;

    @NotEmpty
    private String date;
}
