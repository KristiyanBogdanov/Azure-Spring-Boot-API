package org.elsys.health_tracker.controller.resources;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MealResource {
    private Long id;

    @NotEmpty
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    private Integer calories;
}
