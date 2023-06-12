package org.elsys.health_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.entity.QualityStatus;
import org.elsys.health_tracker.service.SleepStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sleep-stats")
@RequiredArgsConstructor
public class SleepStatController {
    private final SleepStatService sleepStatService;

    @Operation(summary = "Get all sleep stats")
    @ApiResponse(responseCode = "200", description = "All sleep stats retrieved successfully")
    @GetMapping
    public ResponseEntity<List<SleepStatResource>> getAll() {
        return ResponseEntity.ok(sleepStatService.getAll());
    }

    @Operation(summary = "Get a sleep stat by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sleep stat retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Sleep stat not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<SleepStatResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sleepStatService.getById(id));
    }

    @Operation(summary = "Get all quality statuses")
    @ApiResponse(responseCode = "200", description = "All quality statuses retrieved successfully")
    @GetMapping("/quality-statuses")
    public ResponseEntity<List<QualityStatus>> getAllQualityStatuses() {
        return ResponseEntity.ok(sleepStatService.getAllQualityStatuses());
    }

    @Operation(summary = "Create a new sleep stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sleep stat created successfully"),
            @ApiResponse(responseCode = "404", description = "Foreign key constraint violation", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping
    public ResponseEntity<SleepStatResource> create(@Valid @RequestBody SleepStatResource sleepStatResource) {
        SleepStatResource createdSleepStat = sleepStatService.create(sleepStatResource);

        return ResponseEntity.created(
                org.springframework.web.util.UriComponentsBuilder.fromPath("api/v1/sleep-stats/{id}").buildAndExpand(createdSleepStat.getId()).toUri()
        ).body(createdSleepStat);
    }

    @Operation(summary = "Update a sleep stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sleep stat updated successfully"),
            @ApiResponse(responseCode = "404", description = "Sleep stat not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Sleep stat is not changed", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<SleepStatResource> update(@Valid @RequestBody SleepStatResource sleepStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(sleepStatService.update(sleepStatResource, id));
    }

    @Operation(summary = "Delete a sleep stat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sleep stat deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sleep stat not found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sleepStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
