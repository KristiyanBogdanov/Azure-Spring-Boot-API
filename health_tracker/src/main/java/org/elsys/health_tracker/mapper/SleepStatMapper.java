package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.SleepStatResource;
import org.elsys.health_tracker.entity.SleepStat;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SleepStatMapper {
    SleepStatMapper SLEEP_STAT_MAPPER = Mappers.getMapper(SleepStatMapper.class);

    @Mapping(target = "date", source = "date", qualifiedByName = "mapStringToLocalDate")
    SleepStat fromSleepStatResource(SleepStatResource sleepStatResource);

    @InheritInverseConfiguration
    @Mapping(target = "date", source = "date", qualifiedByName = "mapLocalDateToString")
    SleepStatResource toSleepStatResource(SleepStat sleepStat);

    List<SleepStatResource> toSleepStatResources(List<SleepStat> sleepStats);

    @Named("mapStringToLocalDate")
    default LocalDate mapStringToLocalDate(String date) {
        return DateMapper.toSQLDate(date);
    }

    @Named("mapLocalDateToString")
    default String mapLocalDateToString(LocalDate date) {
        return DateMapper.toString(date);
    }
}
