package com.lazydev.stksongbook.webapp.service.mappers;

import com.lazydev.stksongbook.webapp.service.dto.SongDTO;
import com.lazydev.stksongbook.webapp.service.mappers.decorator.SongMapperDecorator;
import com.lazydev.stksongbook.webapp.data.model.Song;
import com.lazydev.stksongbook.webapp.data.model.Tag;
import com.lazydev.stksongbook.webapp.service.CategoryService;
import com.lazydev.stksongbook.webapp.service.TagService;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
    uses = {Tag.class, SongAuthorMapper.class, TagService.class, CategoryService.class},
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@DecoratedWith(SongMapperDecorator.class)
public interface SongMapper {

  @Mapping(target = "additionTime", source = "additionTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
  @Mapping(target = "categoryId", source = "entity.category.id")
  @Mapping(target = "tagsId", expression = "java(convertTagsToIDs(entity.getTags()))")
  SongDTO songToSongDTO(Song entity);

  @Mapping(target = "additionTime", source = "additionTime", dateFormat = "dd-MM-yyyy HH:mm:ss")
  @Mapping(target = "tags", ignore = true)
  @Mapping(target = "category", ignore = true)
  Song songDTOToSong(SongDTO dto);

  default List<Long> convertTagsToIDs(Set<Tag> list) {
    return list.stream().mapToLong(Tag::getId).boxed().collect(Collectors.toList());
  }
}
