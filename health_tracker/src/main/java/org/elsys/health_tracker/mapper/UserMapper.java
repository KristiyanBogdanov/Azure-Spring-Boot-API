package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.UserResource;
import org.elsys.health_tracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {SleepStatMapper.class, MealStatMapper.class, WorkoutStatMapper.class})
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User fromUserResource(UserResource userResource);
    UserResource toUserResource(User user);
    List<UserResource> toUserResources(List<User> users);
}
