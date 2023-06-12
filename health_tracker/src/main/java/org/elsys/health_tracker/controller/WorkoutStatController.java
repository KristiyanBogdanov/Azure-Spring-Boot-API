package org.elsys.health_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;
import org.elsys.health_tracker.service.WorkoutStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workouts-stats")
@RequiredArgsConstructor
public class WorkoutStatController {
    private final WorkoutStatService workoutStatService;

    @Operation(summary = "Get all workout stats")
    @ApiResponse(responseCode = "200", description = "All workout stats retrieved successfully")
    @GetMapping
    public ResponseEntity<List<WorkoutStatResource>> getAll() {
        return ResponseEntity.ok(workoutStatService.getAll());
    }

    @Operation(summary = "Get a workout stat by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout stat retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Workout stat not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutStatResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutStatService.getById(id));
    }

    @Operation(summary = "Create a workout stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workout stat created successfully"),
            @ApiResponse(responseCode = "404", description = "Foreign key constraint violation", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<WorkoutStatResource> create(@Valid @RequestBody WorkoutStatResource workoutStatResource) {
        WorkoutStatResource createdWorkoutStat = workoutStatService.create(workoutStatResource);

        return ResponseEntity.created(
                org.springframework.web.util.UriComponentsBuilder.fromPath("/api/v1/workout-stats/{id}").buildAndExpand(createdWorkoutStat.getId()).toUri()
        ).body(createdWorkoutStat);
    }

    @Operation(summary = "Update a workout stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout stat updated successfully"),
            @ApiResponse(responseCode = "404", description = "Workout stat not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Workout stat is not changed", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<WorkoutStatResource> update(@Valid @RequestBody WorkoutStatResource workoutStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutStatService.update(workoutStatResource, id));
    }

    @Operation(summary = "Delete a workout stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout stat deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Workout stat not found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        workoutStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
