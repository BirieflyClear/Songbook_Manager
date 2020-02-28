package com.lazydev.stksongbook.webapp.dto;

import com.lazydev.stksongbook.webapp.model.Song;
import com.lazydev.stksongbook.webapp.model.Tag;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = Tag.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SongMapper {

    @Mappings({
            @Mapping(target="additionTime", source = "additionTime", dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target="categoryId", source = "entity.category.id"),
            @Mapping(target="tagsId", expression = "java(convertTagsToIDs(entity.getTags()))") })
    SongDTO songToSongDTO(Song entity);

    @Mappings({
            @Mapping(target="additionTime", source = "additionTime", dateFormat = "dd-MM-yyyy HH:mm:ss"),
            /*@Mapping(target="category", source = "java(convertIDToCategory(dto.categoryId))"),
            @Mapping(target="tagsId", expression = "java(convertIDsToTags(dto.getTagsId()))")*/ })
    Song songDTOToSong(SongDTO dto);

    default List<Long> convertTagsToIDs(Set<Tag> list) {
        return list.stream().mapToLong(Tag::getId).boxed().collect(Collectors.toList());
    }

    /*default List<Tag> convertIDsToTags(List<Long> list) {
        //return list.stream().mapToLong(Tag::getId).boxed().collect(Collectors.toList());
    }*/
}
