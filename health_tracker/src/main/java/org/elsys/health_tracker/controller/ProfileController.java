package org.elsys.health_tracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Gender;
import org.elsys.health_tracker.service.ProfileAuditService;
import org.elsys.health_tracker.service.ProfileService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileAuditService profileAuditService;

    @Operation(summary = "Get all profiles")
    @ApiResponse(responseCode = "200", description = "All profiles retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAll() {
        return ResponseEntity.ok(profileService.getAll());
    }

    @Operation(summary = "Get a profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResource> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @Operation(summary = "Get all gender options")
    @ApiResponse(responseCode = "200", description = "All gender options retrieved successfully")
    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getAllGenders() {
        return ResponseEntity.ok(profileService.getAllGenders());
    }

    @Operation(summary = "Get last version of a profile before certain date providing profile id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/{id}/lastVersion")
    public ResponseEntity<ProfileResource> getLastVerBeforeDate(
            @PathVariable Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(profileAuditService.getLastVerBeforeDate(id, date));
    }

    @Operation(summary = "Update a profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "Profile is not changed", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfileResource> update(@Valid @RequestBody ProfileResource profileResource, @PathVariable("id") Long id) {
        return ResponseEntity.ok(profileService.update(profileResource, id));
    }

    @Operation(summary = "Delete a profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profile deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
