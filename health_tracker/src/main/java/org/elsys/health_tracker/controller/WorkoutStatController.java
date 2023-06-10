package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.WorkoutStatResource;
import org.elsys.health_tracker.service.WorkoutStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/workouts-stats")
@RequiredArgsConstructor
public class WorkoutStatController {
    private final WorkoutStatService workoutStatService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(workoutStatService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutStatService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody WorkoutStatResource workoutStatResource) {
        WorkoutStatResource createdWorkoutStat = workoutStatService.create(workoutStatResource);

        return ResponseEntity.created(
                org.springframework.web.util.UriComponentsBuilder.fromPath("/api/v1/workout-stats/{id}").buildAndExpand(createdWorkoutStat.getId()).toUri()
        ).body(createdWorkoutStat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody WorkoutStatResource workoutStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(workoutStatService.update(workoutStatResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        workoutStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
