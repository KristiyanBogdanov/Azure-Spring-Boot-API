package org.elsys.health_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealResource;
import org.elsys.health_tracker.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @Operation(summary = "Get all meals")
    @ApiResponse(responseCode = "200", description = "All meals retrieved successfully")
    @GetMapping
    public ResponseEntity<List<MealResource>> getAll() {
        return ResponseEntity.ok(mealService.getAll());
    }

    @Operation(summary = "Get a meal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MealResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mealService.getById(id));
    }

    @Operation(summary = "Get a meal by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<MealResource> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(mealService.getByName(name));
    }

    @Operation(summary = "Create a new meal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal created successfully"),
            @ApiResponse(responseCode = "409", description = "Meal already exists", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<MealResource> create(@Valid @RequestBody MealResource mealResource) {
        MealResource createdMeal = mealService.create(mealResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/meals/{id}").buildAndExpand(createdMeal.getId()).toUri()
        ).body(createdMeal);
    }

    @Operation(summary = "Update a meal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal updated successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Meal is not changed", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<MealResource> update(@Valid @RequestBody MealResource mealResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(mealService.update(mealResource, id));
    }

    @Operation(summary = "Delete a meal by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Meal deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
