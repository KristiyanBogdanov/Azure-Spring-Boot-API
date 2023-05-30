package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.Profile;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ProfileMapper {
    ProfileMapper PROFILE_MAPPER = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = "birthday", source = "birthday", qualifiedByName = "mapStringToLocalDate")
    Profile fromProfileResource(ProfileResource profileResource);

    @InheritInverseConfiguration
    @Mapping(target = "birthday", source = "birthday", qualifiedByName = "mapLocalDateToString")
    ProfileResource toProfileResource(Profile profile);

    List<ProfileResource> toProfileResources(List<Profile> profiles);

    @Named("mapStringToLocalDate")
    default LocalDate mapStringToLocalDate(String date) {
        return DateMapper.toSQLDate(date);
    }

    @Named("mapLocalDateToString")
    default String mapLocalDateToString(LocalDate date) {
        return DateMapper.toString(date);
    }
}