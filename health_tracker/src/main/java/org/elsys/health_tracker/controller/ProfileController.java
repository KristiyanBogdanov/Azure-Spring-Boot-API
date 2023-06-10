package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.service.ProfileAuditService;
import org.elsys.health_tracker.service.ProfileService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileAuditService profileAuditService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @GetMapping("/genders")
    public ResponseEntity<?> getAllGenders() {
        return ResponseEntity.ok(profileService.getAllGenders());
    }

    @GetMapping("/{id}/lastVersion")
    public ResponseEntity<?> getLastVerBeforeDate(
            @PathVariable Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(profileAuditService.getLastVerBeforeDate(id, date));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProfileResource profileResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.update(profileResource, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
