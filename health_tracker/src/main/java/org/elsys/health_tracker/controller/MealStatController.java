package org.elsys.health_tracker.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealStatResource;
import org.elsys.health_tracker.service.MealStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/meal-stats")
@RequiredArgsConstructor
public class MealStatController {
    private final MealStatService mealStatService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(mealStatService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<MealStatResource> mealStatResource = mealStatService.getById(id);

        if (mealStatResource.isPresent()) {
            return ResponseEntity.ok(mealStatResource.get());
        } else {
            throw new EntityNotFoundException("Unable to find mealStat with id " + id + ".");
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MealStatResource mealStatResource) {
        MealStatResource createdMealStat = mealStatService.create(mealStatResource);

        return ResponseEntity.created(
                org.springframework.web.util.UriComponentsBuilder.fromPath("/api/v1/meal-stats/{id}").buildAndExpand(createdMealStat.getId()).toUri()
        ).body(createdMealStat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody MealStatResource mealStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(mealStatService.update(mealStatResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
