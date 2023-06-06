package org.elsys.health_tracker.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealResource;
import org.elsys.health_tracker.entity.Meal;
import org.elsys.health_tracker.exception.DuplicateEntityFieldException;
import org.elsys.health_tracker.repository.MealRepository;
import org.elsys.health_tracker.service.MealService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.elsys.health_tracker.mapper.MealMapper.MEAL_MAPPER;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Override
    public List<MealResource> getAll() {
        return MEAL_MAPPER.toMealResources(mealRepository.findAll());
    }

    @Override
    public Optional<MealResource> getById(Long id) {
        return mealRepository.findById(id).map(MEAL_MAPPER::toMealResource);
    }

    @Override
    public Optional<MealResource> getByName(String name) {
        return mealRepository.findByName(name).map(MEAL_MAPPER::toMealResource);
    }

    @Override
    public MealResource create(MealResource mealResource) {
        try {
            Meal  meal = mealRepository.save(MEAL_MAPPER.fromMealResource(mealResource));
            mealResource.setId(meal.getId());
            return mealResource;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityFieldException("Meal with name " + mealResource.getName() + " already exists.");
        }
    }

    @Override
    public MealResource update(MealResource mealResource, Long id) {
        try {
            Meal meal = mealRepository.getReferenceById(id);

            meal.setName(mealResource.getName());
            meal.setCalories(mealResource.getCalories());

            return MEAL_MAPPER.toMealResource(mealRepository.save(meal));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Unable to find meal with id " + id + ".");
        }
    }

    @Override
    public void delete(Long id) {
        if (mealRepository.existsById(id)) {
            mealRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find meal with id " + id + ".");
        }
    }
}
