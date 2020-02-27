package com.lazydev.stksongbook.webapp.dto;

import com.lazydev.stksongbook.webapp.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    UserDTO userToUserDTO(User entity);

    User userDTOToUser(UserDTO dto);
}
