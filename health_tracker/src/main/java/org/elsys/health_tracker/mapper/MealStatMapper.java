package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.MealStatResource;
import org.elsys.health_tracker.entity.MealStat;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper(uses = MealMapper.class)
public interface MealStatMapper {
    MealStatMapper MEAL_STAT_MAPPER = Mappers.getMapper(MealStatMapper.class);

    @Mapping(target = "date", source = "date", qualifiedByName = "mapStringToLocalDate")
    MealStat fromMealStatResource(MealStatResource mealStatResource);

    @InheritInverseConfiguration
    @Mapping(target = "date", source = "date", qualifiedByName = "mapLocalDateToString")
    MealStatResource toMealStatResource(MealStat mealStat);

    List<MealStatResource> toMealStatResources(List<MealStat> mealStats);

    @Named("mapStringToLocalDate")
    default LocalDate mapStringToLocalDate(String date) {
        return DateMapper.toSQLDate(date);
    }

    @Named("mapLocalDateToString")
    default String mapLocalDateToString(LocalDate date) {
        return DateMapper.toString(date);
    }
}
