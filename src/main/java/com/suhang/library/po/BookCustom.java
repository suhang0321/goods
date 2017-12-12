package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-10 14:40
 */
public class BookCustom extends Book {
    private CategoryCustom category;

    public void setCategory(CategoryCustom category) {
        this.category = category;
    }

    public CategoryCustom getCategory() {
        return category;
    }
}
