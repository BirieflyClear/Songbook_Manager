package com.lazydev.stksongbook.webapp.service.mappers;

import com.lazydev.stksongbook.webapp.data.model.Author;
import com.lazydev.stksongbook.webapp.service.dto.AuthorDTO;
import com.lazydev.stksongbook.webapp.service.dto.creational.UniversalCreateDTO;
import com.lazydev.stksongbook.webapp.service.mappers.decorator.AuthorMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@DecoratedWith(AuthorMapperDecorator.class)
public interface AuthorMapper {

    AuthorDTO map(Author entity);

    @Mapping(target = "songs", ignore = true)
    Author map(AuthorDTO dto);

    @Mapping(target = "coauthorSongs", ignore = true)
    @Mapping(target = "songs", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    Author map(UniversalCreateDTO dto);
}
