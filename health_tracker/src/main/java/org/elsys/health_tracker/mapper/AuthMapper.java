package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.AuthResponse;
import org.elsys.health_tracker.controller.resources.RegisterRequest;
import org.elsys.health_tracker.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ProfileMapper.class, SleepStatMapper.class, MealStatMapper.class, WorkoutStatMapper.class})
public interface AuthMapper {
    AuthMapper MAPPER = Mappers.getMapper(AuthMapper.class);

    User fromRegisterRequest(RegisterRequest registerRequest);
    AuthResponse toAuthResponse(User user);
}
