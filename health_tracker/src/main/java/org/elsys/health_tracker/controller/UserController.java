package org.elsys.health_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.UserMealStatResource;
import org.elsys.health_tracker.controller.resources.UserResource;
import org.elsys.health_tracker.controller.resources.UserSleepStatResource;
import org.elsys.health_tracker.controller.resources.UserWorkoutStatResource;
import org.elsys.health_tracker.service.MealStatService;
import org.elsys.health_tracker.service.SleepStatService;
import org.elsys.health_tracker.service.UserService;
import org.elsys.health_tracker.service.WorkoutStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SleepStatService sleepStatService;
    private final MealStatService mealStatService;
    private final WorkoutStatService workoutStatService;

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "All users retrieved successfully")
    @GetMapping
    public ResponseEntity<List<UserResource>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Operation(summary = "Get all sleep stats of a user providing id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All sleep stats retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}/sleep-stats")
    public ResponseEntity<List<UserSleepStatResource>> getSleepStatsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sleepStatService.getAllByUserId(id));
    }

    @Operation(summary = "Get all meal stats of a user providing id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All meal stats retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}/meals-stats")
    public ResponseEntity<List<UserMealStatResource>> getMealStatsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mealStatService.getAllByUserId(id));
    }

    @Operation(summary = "Get all workout stats of a user providing id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All workout stats retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}/workouts-stats")
    public ResponseEntity<List<UserWorkoutStatResource>> getWorkoutStatsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutStatService.getAllByUserId(id));
    }

    @Operation(summary = "Update a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "User is not changed", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResource> update(@Valid @RequestBody UserResource userResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.update(userResource, id));
    }

    @Operation(summary = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
