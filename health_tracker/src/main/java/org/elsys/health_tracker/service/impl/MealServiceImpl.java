package org.elsys.health_tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.elsys.health_tracker.controller.resources.MealResource;
import org.elsys.health_tracker.entity.Meal;
import org.elsys.health_tracker.repository.MealRepository;
import org.elsys.health_tracker.service.MealService;
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
        Meal  meal = mealRepository.save(MEAL_MAPPER.fromMealResource(mealResource));
        mealResource.setId(meal.getId());
        return mealResource;
    }

    @Override
    public MealResource update(MealResource mealResource, Long id) {
        Meal meal = mealRepository.getReferenceById(id);

        meal.setName(mealResource.getName());
        meal.setCalories(mealResource.getCalories());

        return MEAL_MAPPER.toMealResource(mealRepository.save(meal));
    }

    @Override
    public void delete(Long id) {
        mealRepository.deleteById(id);
    }
}
