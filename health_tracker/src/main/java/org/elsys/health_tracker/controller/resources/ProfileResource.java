package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.elsys.health_tracker.entity.Gender;

@Data
public class ProfileResource {
    private Long id;

    @NotNull
    private Gender gender;

    @NotEmpty
    private String birthday;

    @DecimalMin(value = "0.0", inclusive = false)
    private float height;

    @DecimalMin(value = "0.0", inclusive = false)
    private float weight;

    @DecimalMin(value = "0.0", inclusive = false)
    @JsonProperty("body_fat")
    private float bodyFat;

    @JsonProperty("health_bio")
    private String healthBio;
}
