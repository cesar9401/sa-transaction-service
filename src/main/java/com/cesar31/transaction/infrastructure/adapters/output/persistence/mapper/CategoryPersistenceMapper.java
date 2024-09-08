package com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.transaction.domain.Category;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.CategoryEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CategoryPersistenceMapper {

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "parentId", target = "parentCategoryId")
    @Mapping(source = "description", target = "description")
    Category toCategory(CategoryEntity entity);

    List<Category> toCategoryList(List<CategoryEntity> entities);


    @InheritInverseConfiguration
    CategoryEntity toCategoryEntity(Category category);

    List<CategoryEntity> toCategoryEntities(List<Category> categories);
}
