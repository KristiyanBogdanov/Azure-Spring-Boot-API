package org.elsys.health_tracker.controller.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotNull
    @Size(min = 2, max = 64)
    private String username;

    @NotNull
    @Size(min = 8, max = 64)
    private String password;

    @NotNull
    private ProfileResource profile;
}
