package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserResource {
    private Long id;

    @JsonProperty("profile_id")
    private Long profileId;

    @NotEmpty
    @Size(min = 2, max = 64)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 64)
    private String password;
}
