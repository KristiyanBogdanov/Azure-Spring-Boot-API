package org.elsys.health_tracker.controller;

import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;

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
        Optional<UserResource> userResource = userService.getById(id);

        if (userResource.isPresent()) {
            return ResponseEntity.ok(userResource.get());
        } else {
            throw new EntityNotFoundException("Unable to find user with id " + id + ".");
        }
    }

    @GetMapping("/{id}/sleep-stats")
    public ResponseEntity<?> getSleepStatsByUserId(@PathVariable("id") Long id) {
        Optional<List<UserSleepStatResource>> userSleepStatResources = sleepStatService.getAllByUserId(id);

        if (userSleepStatResources.isPresent()) {
            return ResponseEntity.ok(userSleepStatResources.get());
        } else {
            throw new EntityNotFoundException("Unable to find sleep stats for user with id " + id + ".");
        }
    }

    @GetMapping("/{id}/meal-stats")
    public ResponseEntity<?> getMealStatsByUserId(@PathVariable("id") Long id) {
        Optional<List<UserMealStatResource>> userMealStatResources = mealStatService.getAllByUserId(id);

        if (userMealStatResources.isPresent()) {
            return ResponseEntity.ok(userMealStatResources.get());
        } else {
            throw new EntityNotFoundException("Unable to find meal stats for user with id " + id + ".");
        }
    }

    @GetMapping("/{id}/workout-stats")
    public ResponseEntity<?> getWorkoutStatsByUserId(@PathVariable("id") Long id) {
        Optional<List<UserWorkoutStatResource>> userWorkoutStatResources = workoutStatService.getAllByUserId(id);

        if (userWorkoutStatResources.isPresent()) {
            return ResponseEntity.ok(userWorkoutStatResources.get());
        } else {
            throw new EntityNotFoundException("Unable to find workout stats for user with id " + id + ".");
        }
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
