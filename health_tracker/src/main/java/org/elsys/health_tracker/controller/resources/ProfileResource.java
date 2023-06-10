package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elsys.health_tracker.entity.Gender;

@Data
public class ProfileResource {
    private Long id;

    @NotNull
    private Gender gender;

    @NotEmpty
    private String birthday;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Float height;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Float weight;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "100.0", inclusive = false)
    @JsonProperty("body_fat")
    private Float bodyFat;

    @JsonProperty("health_bio")
    private String healthBio;
}
