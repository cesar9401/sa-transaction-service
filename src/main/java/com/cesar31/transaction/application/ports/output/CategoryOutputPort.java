package com.cesar31.transaction.application.ports.output;

import com.cesar31.transaction.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryOutputPort {

    Optional<Category> findById(Long categoryId);

    Category findBy(Long categoryId);

    List<Category> findByParentId(Long parentId);
}
