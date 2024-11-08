package com.emzon.user.adapters.driving.http.mapper;

import com.emzon.user.adapters.utilities.UserMappersConstants;
import com.emzon.user.adapters.driving.http.dto.response.UserResponse;
import com.emzon.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = UserMappersConstants.SPRING_COMPONENT_MODEL)
public interface IUserResponseMapper {
    UserResponse toResponse(User user);
}
