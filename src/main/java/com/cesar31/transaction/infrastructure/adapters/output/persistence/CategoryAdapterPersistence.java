package com.cesar31.transaction.infrastructure.adapters.output.persistence;

import com.cesar31.transaction.application.ports.output.CategoryOutputPort;
import com.cesar31.transaction.domain.Category;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.cesar31.transaction.infrastructure.adapters.output.persistence.repository.CategoryEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryAdapterPersistence implements CategoryOutputPort {

    private final CategoryEntityRepository entityRepository;
    private final CategoryPersistenceMapper mapper;

    public CategoryAdapterPersistence(CategoryEntityRepository entityRepository, CategoryPersistenceMapper mapper) {
        this.entityRepository = entityRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return entityRepository.findById(categoryId)
                .map(mapper::toCategory);
    }

    @Override
    public Category findBy(Long categoryId) {
        var categoryEntity = entityRepository.findByCategoryId(categoryId);
        return mapper.toCategory(categoryEntity);
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return mapper.toCategoryList(entityRepository.findByParentId(parentId));
    }
}
