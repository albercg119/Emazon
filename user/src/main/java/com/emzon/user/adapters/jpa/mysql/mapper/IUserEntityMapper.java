package com.emzon.user.adapters.jpa.mysql.mapper;

import com.emzon.user.adapters.jpa.mysql.adapter.entity.UserEntity;
import com.emzon.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);
    User toDomain(UserEntity userEntity);
}