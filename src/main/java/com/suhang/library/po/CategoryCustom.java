package com.suhang.library.po;

import java.util.List;

/**
 * @author hang.su
 * @since 2017-11-09 8:17
 */
public class CategoryCustom extends Category {
    private CategoryCustom parent;
    private List<CategoryCustom> childs;

    @Override
    public String toString() {
        return "CategoryCustom{" + "parent=" + parent + ", childs=" + childs + '}';
    }

    public void setParent(CategoryCustom parent) {
        this.parent = parent;
    }

    public void setChilds(List<CategoryCustom> childs) {
        this.childs = childs;
    }

    public CategoryCustom getParent() {
        return parent;
    }

    public List<CategoryCustom> getChilds() {
        return childs;
    }
}
