package com.suhang.library.serviceimp;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.suhang.library.mapper.CategoryMapper;
import com.suhang.library.po.CategoryCustom;
import com.suhang.library.service.CategoryService;

/**
 * @author hang.su
 * @since 2017-11-09 8:38
 */
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryCustom> findAllClass() throws Exception {
        List<CategoryCustom> firstClassList = categoryMapper.findFistClassList();
        for (CategoryCustom categoryCustom: firstClassList){
           List<CategoryCustom> secondClassList = categoryMapper.findSecondClassList(categoryCustom.getCid());
           categoryCustom.setChilds(secondClassList);
        }
        return firstClassList;
    }

    @Override
    public void addFirstClass(String cname, String desc) throws Exception {
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setDesc(desc);
        categoryCustom.setCname(cname);
        categoryCustom.setCid(UUID.randomUUID().toString().substring(0,32));
        categoryMapper.addFirstClass(categoryCustom);
    }

    @Override
    public List<CategoryCustom> findFirstClass() throws Exception {
       List<CategoryCustom> categoryCustomList= categoryMapper.findFistClassList();
        return categoryCustomList;
    }

    @Override
    public void addSecondClass(String cname, String desc, String pid) throws Exception {
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCid(UUID.randomUUID().toString().substring(0,32));
        categoryCustom.setCname(cname);
        categoryCustom.setPid(pid);
        categoryCustom.setDesc(desc);
        categoryMapper.addSecondClass(categoryCustom);
    }

    @Override
    public CategoryCustom findClassByCid(String cid) throws Exception {
        CategoryCustom categoryCustom = categoryMapper.findClassByCid(cid);
        return categoryCustom;
    }

    @Override
    public void updateFirstClassByCid(String cid, String cname, String desc) throws Exception {
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCid(cid);
        categoryCustom.setDesc(desc);
        categoryCustom.setCname(cname);
        categoryMapper.updateCategoryByCid(categoryCustom);
    }

    @Override
    public void updateSecondClassByCid(String cid, String cname, String desc) throws Exception {
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCid(cid);
        categoryCustom.setCname(cname);
        categoryCustom.setDesc(desc);
        categoryMapper.updateSecondClassByCid(categoryCustom);
    }

    @Override
    public Integer findChildCountByParentId(String cid) throws Exception {
        Integer num = categoryMapper.findChildCountByParentId(cid);
        return num;
    }

    @Override
    public void deleteByCid(String cid) throws Exception {
        categoryMapper.deleteByCid(cid);
    }


    @Override
    public List<CategoryCustom> findSecondClassByPid(String pid) throws Exception {
        List<CategoryCustom> categoryCustomList = categoryMapper.findSecondClassList(pid);
        return categoryCustomList;
    }
}
