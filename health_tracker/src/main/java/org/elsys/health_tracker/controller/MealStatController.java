package org.elsys.health_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealStatResource;
import org.elsys.health_tracker.service.MealStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meals-stats")
@RequiredArgsConstructor
public class MealStatController {
    private final MealStatService mealStatService;

    @Operation(summary = "Get all meal stats")
    @ApiResponse(responseCode = "200", description = "All meal stats retrieved successfully")
    @GetMapping
    public ResponseEntity<List<MealStatResource>> getAll() {
        return ResponseEntity.ok(mealStatService.getAll());
    }

    @Operation(summary = "Get a meal stat by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal stat retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Meal stat not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MealStatResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mealStatService.getById(id));
    }

    @Operation(summary = "Create a new meal stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal stat created successfully"),
            @ApiResponse(responseCode = "404", description = "Foreign key constraint violation", content = @Content(mediaType = "text/plain")),
    })
    @PostMapping
    public ResponseEntity<MealStatResource> create(@Valid @RequestBody MealStatResource mealStatResource) {
        MealStatResource createdMealStat = mealStatService.create(mealStatResource);

        return ResponseEntity.created(
                org.springframework.web.util.UriComponentsBuilder.fromPath("/api/v1/meal-stats/{id}").buildAndExpand(createdMealStat.getId()).toUri()
        ).body(createdMealStat);
    }

    @Operation(summary = "Update a meal stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal stat updated successfully"),
            @ApiResponse(responseCode = "404", description = "Meal stat not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Meal stat is not changed", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<MealStatResource> update(@Valid @RequestBody MealStatResource mealStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(mealStatService.update(mealStatResource, id));
    }

    @Operation(summary = "Delete a meal stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Meal stat deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Meal stat not found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mealStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
