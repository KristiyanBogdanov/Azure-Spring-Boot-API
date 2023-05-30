package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.MealResource;
import org.elsys.health_tracker.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MealMapper {
    MealMapper MEAL_MAPPER = Mappers.getMapper(MealMapper.class);

    Meal fromMealResource(MealResource mealResource);
    MealResource toMealResource(Meal meal);
    List<MealResource> toMealResources(List<Meal> meals);
}
