package com.cesar31.transaction.application.service;

import com.cesar31.transaction.application.ports.input.CategoryUseCase;
import com.cesar31.transaction.application.ports.output.CategoryOutputPort;
import com.cesar31.transaction.domain.Category;

import java.util.List;
import java.util.Optional;

public class CategoryService implements CategoryUseCase {

    private final CategoryOutputPort categoryOutputPort;

    public CategoryService(CategoryOutputPort categoryOutputPort) {
        this.categoryOutputPort = categoryOutputPort;
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryOutputPort.findById(categoryId);
    }

    @Override
    public Category findBy(Long categoryId) {
        return categoryOutputPort.findBy(categoryId);
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return categoryOutputPort.findByParentId(parentId);
    }
}
