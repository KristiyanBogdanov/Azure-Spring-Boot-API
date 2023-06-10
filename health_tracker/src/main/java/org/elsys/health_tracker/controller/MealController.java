package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealResource;
import org.elsys.health_tracker.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(mealService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mealService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(mealService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MealResource mealResource) {
        MealResource createdMeal = mealService.create(mealResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/meals/{id}").buildAndExpand(createdMeal.getId()).toUri()
        ).body(createdMeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody MealResource mealResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(mealService.update(mealResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }
}