package com.lazydev.stksongbook.webapp.dto;

import com.lazydev.stksongbook.webapp.model.Author;
import com.lazydev.stksongbook.webapp.model.Category;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoryMapper {

    CategoryDTO categoryToCategoryDTO(Category entity);
    Category categoryDTOToCategory(CategoryDTO dto);
}
