package org.elsys.health_tracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealResource;
import org.elsys.health_tracker.controller.resources.UserResource;
import org.elsys.health_tracker.service.MealService;
import org.elsys.health_tracker.service.MealStatService;
import org.elsys.health_tracker.service.ProfileService;
import org.elsys.health_tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// IMPORTANT: This controller is not used in the project. It is only for demonstration purposes.
// =====================================================================================================================
// TODO: Remove this controller
// DEMO CONTROLLER - NOT USED IN THE PROJECT!!!
// =====================================================================================================================

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final MealService mealService;
    private final MealStatService mealStatService;
    private final ProfileService profileService;
    private final UserService userService;


    @GetMapping("/all/meals")
    public ResponseEntity<List<MealResource>> findAllMeals() {
        return new ResponseEntity<>(mealService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/users")
    public ResponseEntity<List<UserResource>> findAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/user/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @Valid @RequestBody UserResource userResource) {
        return new ResponseEntity<>(userService.update(userResource, id), HttpStatus.OK);
    }

    @PostMapping("/create/user")
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody UserResource userResource) {
        return new ResponseEntity<>(userService.create(userResource), HttpStatus.OK);
    }

//    @GetMapping("/all-meals")
//    public ResponseEntity<List<MealStatResource>> findAllMeals() {
//        return new ResponseEntity<>(mealStatService.getAllMealStats(), HttpStatus.OK);
//    }
//
//    @GetMapping("/find")
//    public ResponseEntity<Optional<MealResource>> findByName() {
//        return new ResponseEntity<>(mealService.getMealByName("snack"), HttpStatus.OK);
//    }
//
//
//
//    @PostMapping("/update")
//    public ResponseEntity<ProfileResource> update(@Valid @RequestBody ProfileResource profileResource) {
//        return new ResponseEntity<>(profileService.updateProfile(profileResource), HttpStatus.OK);
//    }
}
