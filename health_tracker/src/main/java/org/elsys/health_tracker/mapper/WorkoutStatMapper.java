package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.WorkoutStatResource;
import org.elsys.health_tracker.entity.WorkoutStat;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface WorkoutStatMapper {
    WorkoutStatMapper WORKOUT_STAT_MAPPER = Mappers.getMapper(WorkoutStatMapper.class);

    @Mapping(target = "date", source = "date", qualifiedByName = "mapStringToLocalDate")
    WorkoutStat fromWorkoutStatResource(WorkoutStatResource workoutStatResource);

    @InheritInverseConfiguration
    @Mapping(target = "date", source = "date", qualifiedByName = "mapLocalDateToString")
    WorkoutStatResource toWorkoutStatResource(WorkoutStat workoutStat);

    List<WorkoutStatResource> toWorkoutStatResources(List<WorkoutStat> workoutStats);

    @Named("mapStringToLocalDate")
    default LocalDate mapStringToLocalDate(String date) {
        return DateMapper.toSQLDate(date);
    }

    @Named("mapLocalDateToString")
    default String mapLocalDateToString(LocalDate date) {
        return DateMapper.toString(date);
    }
}
