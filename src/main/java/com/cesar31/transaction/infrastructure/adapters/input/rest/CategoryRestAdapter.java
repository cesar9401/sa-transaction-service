package com.cesar31.transaction.infrastructure.adapters.input.rest;

import com.cesar31.transaction.application.ports.input.CategoryUseCase;
import com.cesar31.transaction.domain.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryRestAdapter {

    private final CategoryUseCase categoryUseCase;

    public CategoryRestAdapter(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Category> findById(@PathVariable("categoryId") Long categoryId) {
        return categoryUseCase.findById(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("by-parent/{parentCategoryId}")
    public ResponseEntity<List<Category>> findAllByParent(@PathVariable("parentCategoryId") Long categoryId) {
        var categories = categoryUseCase.findByParentId(categoryId);
        return ResponseEntity.ok(categories);
    }
}
