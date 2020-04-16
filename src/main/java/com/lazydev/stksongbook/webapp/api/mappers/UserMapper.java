package com.lazydev.stksongbook.webapp.api.mappers;

import com.lazydev.stksongbook.webapp.api.dto.UserDTO;
import com.lazydev.stksongbook.webapp.api.mappers.decorator.UserMapperDecorator;
import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.data.model.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {Song.class})
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

  @Mapping(target = "userRoleId", expression = "java(entity.getUserRole().getId())")
  @Mapping(target = "songs", expression = "java(getIds(entity.getSongs()))")
  UserDTO userToUserDTO(User entity);

  @Mapping(target = "songs", ignore = true)
  User userDTOToUser(UserDTO dto);

  default Set<Long> getIds(Set<Song> list) {
    return list.stream().map(Song::getId).collect(Collectors.toSet());
  }
}
