package org.elsys.health_tracker.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.elsys.health_tracker.entity.QualityStatus;

@Data
public class SleepStatResource {
    private Long id;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;

    @NotNull
    @Min(1)
    private Integer duration;

    @NotNull
    @JsonProperty("quality_status")
    private QualityStatus qualityStatus;

    @NotEmpty
    private String date;
}
