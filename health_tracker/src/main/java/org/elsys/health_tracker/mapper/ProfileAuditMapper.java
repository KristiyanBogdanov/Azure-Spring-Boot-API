package org.elsys.health_tracker.mapper;

import org.elsys.health_tracker.controller.resources.ProfileResource;
import org.elsys.health_tracker.entity.ProfileAudit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProfileAuditMapper {
    ProfileAuditMapper PROFILE_AUDIT_MAPPER = Mappers.getMapper(ProfileAuditMapper.class);

    @Mapping(target = "id", source = "profileId")
    ProfileResource toProfileResource(ProfileAudit profileAudit);

    List<ProfileResource> toProfileResources(List<ProfileAudit> profileAudits);
}
