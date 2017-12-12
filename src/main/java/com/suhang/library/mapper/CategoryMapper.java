package com.suhang.library.mapper;

import java.util.List;

import com.suhang.library.po.CategoryCustom;
import com.suhang.library.po.CategoryVo;

/**
 * @author hang.su
 * @since 2017-11-09 8:20
 */
public interface CategoryMapper {
    public List<CategoryCustom> findCategoryAll()throws Exception;

    public List<CategoryCustom> findFistClassList()throws Exception;

    public List<CategoryCustom> findSecondClassList(String cid)throws Exception;

    public void addFirstClass(CategoryCustom categoryCustom)throws Exception;

    public void addSecondClass(CategoryCustom categoryCustom)throws Exception;

    public CategoryCustom findClassByCid(String cid)throws Exception;

    public void updateCategoryByCid(CategoryCustom categoryCustom)throws Exception;

    public void updateSecondClassByCid(CategoryCustom categoryCustom)throws Exception;

    public Integer findChildCountByParentId(String cid)throws Exception;

    public void deleteByCid(String cid)throws Exception;
}
