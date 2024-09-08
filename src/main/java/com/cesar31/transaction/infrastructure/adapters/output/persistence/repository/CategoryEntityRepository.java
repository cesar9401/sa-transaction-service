package com.cesar31.transaction.infrastructure.adapters.output.persistence.repository;

import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryId(Long categoryId);

    List<CategoryEntity> findByParentId(Long parentCategoryId);
}
