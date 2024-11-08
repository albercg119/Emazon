package com.emzon.user.adapters.driving.http.mapper;

import com.emzon.user.adapters.utilities.UserMappersConstants;
import com.emzon.user.adapters.driving.http.dto.request.AddUserRequest;
import com.emzon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = UserMappersConstants.SPRING_COMPONENT_MODEL)
public interface IUserRequestMapper {
    @Mapping(target = UserMappersConstants.TARGET_ID, ignore = true)
    @Mapping(target = UserMappersConstants.TARGET_ROLE,
            constant = UserMappersConstants.AUXILIARY_WAREHOUSE_ROLE)
    User toUser(AddUserRequest addUserRequest);
}