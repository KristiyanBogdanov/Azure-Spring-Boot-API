package org.elsys.health_tracker.controller.resources;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MealStatResource {
    private Long id;

    @NotNull
    private MealResource meal;

    @NotEmpty
    private String date;
}
