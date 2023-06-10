package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.service.SleepStatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sleep-stats")
@RequiredArgsConstructor
public class SleepStatController {
    private final SleepStatService sleepStatService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(sleepStatService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(sleepStatService.getById(id));
    }

    @GetMapping("/quality-statuses")
    public ResponseEntity<?> getAllQualityStatuses() {
        return ResponseEntity.ok(sleepStatService.getAllQualityStatuses());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SleepStatResource sleepStatResource) {
        SleepStatResource createdSleepStat = sleepStatService.create(sleepStatResource);

        return ResponseEntity.created(
                org.springframework.web.util.UriComponentsBuilder.fromPath("api/v1/sleep-stats/{id}").buildAndExpand(createdSleepStat.getId()).toUri()
        ).body(createdSleepStat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody SleepStatResource sleepStatResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(sleepStatService.update(sleepStatResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        sleepStatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}