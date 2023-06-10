package org.elsys.health_tracker.controller.resources;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MealResource {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 64)
    private String name;

    @NotNull
    @Min(0)
    private Integer calories;
}
