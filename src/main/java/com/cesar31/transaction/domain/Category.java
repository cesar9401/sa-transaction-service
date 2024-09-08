package com.cesar31.transaction.domain;

import com.cesar31.transaction.application.util.enums.CategoryEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private Long categoryId;
    private Long parentCategoryId;
    private String description;

    public Boolean is(Long categoryId) {
        return this.categoryId.equals(categoryId);
    }

    public Boolean is(CategoryEnum category) {
        return is(category.categoryId);
    }
}
