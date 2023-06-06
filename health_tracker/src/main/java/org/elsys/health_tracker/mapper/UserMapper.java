package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.UserResource;
import org.elsys.health_tracker.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "profile.id", source = "profileId")
    User fromUserResource(UserResource userResource);

    @InheritInverseConfiguration
    UserResource toUserResource(User user);

    List<UserResource> toUserResources(List<User> users);
}
