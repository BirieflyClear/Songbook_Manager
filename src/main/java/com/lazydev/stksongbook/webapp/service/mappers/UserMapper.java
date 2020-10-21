package com.lazydev.stksongbook.webapp.service.mappers;

import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.data.model.User;
import com.lazydev.stksongbook.webapp.service.SongService;
import com.lazydev.stksongbook.webapp.service.UserRoleService;
import com.lazydev.stksongbook.webapp.service.dto.UserDTO;
import com.lazydev.stksongbook.webapp.service.mappers.decorator.UserMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = {Song.class, SongService.class, UserRoleService.class})
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

  @Mapping(target = "userRoleId", expression = "java(entity.getUserRole().getId())")
  UserDTO map(User entity);

  @Mapping(target = "songs", ignore = true)
  @Mapping(target = "userRole", ignore = true)
  @Mapping(target = "userRatings", ignore = true)
  @Mapping(target = "playlists", ignore = true)
  @Mapping(target = "addedSongs", ignore = true)
  @Mapping(target = "editedSongs", ignore = true)
  @Mapping(target = "activated", ignore = true)
  @Mapping(target = "resetKey", ignore = true)
  @Mapping(target = "activationKey", ignore = true)
  @Mapping(target = "registrationDate", ignore = true)
  @Mapping(target = "imageUrl", source = "imageUrl")
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "id", source = "id")
  User map(UserDTO dto);
}
