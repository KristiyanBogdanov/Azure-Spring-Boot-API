package org.elsys.health_tracker.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(profileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<ProfileResource> profileResource = profileService.getById(id);

        if (profileResource.isPresent()) {
            return ResponseEntity.ok(profileResource.get());
        } else {
            throw new EntityNotFoundException("Unable to find profile with id " + id + ".");
        }
    }

    @GetMapping("/genders")
    public ResponseEntity<?> getAllGenders() {
        return ResponseEntity.ok(profileService.getAllGenders());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProfileResource profileResource) {
        ProfileResource createdProfile = profileService.create(profileResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/api/v1/profiles/{id}").buildAndExpand(createdProfile.getId()).toUri()
        ).body(createdProfile);
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
