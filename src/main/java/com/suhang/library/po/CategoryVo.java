package com.suhang.library.po;

import java.util.List;

/**
 * @author hang.su
 * @since 2017-11-09 8:17
 */
public class CategoryVo {
    private List<CategoryCustom> list;

    @Override
    public String toString() {
        return "CategoryVo{" + "list=" + list + '}';
    }

    public void setList(List<CategoryCustom> list) {
        this.list = list;
    }

    public List<CategoryCustom> getList() {

        return list;
    }
}
