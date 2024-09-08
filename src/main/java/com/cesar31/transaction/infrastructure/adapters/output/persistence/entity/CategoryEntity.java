package com.cesar31.transaction.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sa_category")
public class CategoryEntity {

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "parent_category_id")
    private Long parentId;

    @Column(name = "cat_description")
    private String description;
}
