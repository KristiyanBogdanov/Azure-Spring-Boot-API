package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.UserResource;
import org.elsys.health_tracker.service.MealStatService;
import org.elsys.health_tracker.service.SleepStatService;
import org.elsys.health_tracker.service.UserService;
import org.elsys.health_tracker.service.WorkoutStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SleepStatService sleepStatService;
    private final MealStatService mealStatService;
    private final WorkoutStatService workoutStatService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/{id}/sleep-stats")
    public ResponseEntity<?> getSleepStatsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sleepStatService.getAllByUserId(id));
    }

    @GetMapping("/{id}/meals-stats")
    public ResponseEntity<?> getMealStatsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mealStatService.getAllByUserId(id));
    }

    @GetMapping("/{id}/workouts-stats")
    public ResponseEntity<?> getWorkoutStatsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutStatService.getAllByUserId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserResource userResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.update(userResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
