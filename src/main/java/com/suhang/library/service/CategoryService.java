package com.suhang.library.service;

import java.util.List;

import com.suhang.library.po.CategoryCustom;

/**
 * @author hang.su
 * @since 2017-11-09 8:37
 */
public interface CategoryService {
    public List<CategoryCustom> findAllClass()throws Exception;

    public void addFirstClass(String cname,String desc)throws Exception;

    public List<CategoryCustom> findFirstClass()throws Exception;

    public void addSecondClass(String cname,String desc,String pid)throws Exception;

    public CategoryCustom findClassByCid(String cid)throws Exception;

    public void updateFirstClassByCid(String cid,String cname,String desc)throws Exception;

    public void updateSecondClassByCid(String cid,String cname,String desc)throws Exception;

    public Integer findChildCountByParentId(String cid)throws Exception;

    public void deleteByCid(String cid)throws Exception;

    public List<CategoryCustom> findSecondClassByPid(String pid)throws Exception;
}
