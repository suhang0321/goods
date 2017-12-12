package com.suhang.library.mapper;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suhang.library.po.CategoryCustom;
import com.suhang.library.po.CategoryVo;

/**
 * @author hang.su
 * @since 2017-11-09 8:25
 */
public class CategoryMapperTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    }

    @Test
    public void findCategoryAllTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        List<CategoryCustom> categoryCustomList = categoryMapper.findCategoryAll();
        for (CategoryCustom categoryCustom : categoryCustomList) {
            System.out.println(categoryCustom);
        }
    }

    @Test
    public void findFirstClassListTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        List<CategoryCustom> list = categoryMapper.findFistClassList();
        for (CategoryCustom categoryCustom : list) {
            System.out.println(categoryCustom.getCname());
        }
    }

    @Test
    public void findSecondClassListTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        List<CategoryCustom> categoryCustomList = categoryMapper.findSecondClassList("1");
        for (CategoryCustom categoryCustom:categoryCustomList){
            System.out.println(categoryCustom.getCname());
        }
    }

    @Test
    public void addFirstClassTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCid(UUID.randomUUID().toString().substring(0,32));
        categoryCustom.setCname("python");
        categoryCustom.setDesc("ai");
        categoryMapper.addFirstClass(categoryCustom);
    }

    @Test
    public void addSecondClassTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCid(UUID.randomUUID().toString().substring(0,32));
        categoryCustom.setCname("nlp");
        categoryCustom.setPid("4b22c6f8-1edf-48bf-9e53-f521a328");
        categoryCustom.setDesc("机器学习");
        categoryMapper.addSecondClass(categoryCustom);
    }

    @Test
    public void findFirstClassByCid() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        CategoryCustom categoryCustom = categoryMapper.findClassByCid("1");
        System.out.println(categoryCustom);
    }

    @Test
    public void updateCategoryByCidTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCname("网络与数据通讯1");
        categoryCustom.setDesc("网络与数据通讯2");
        categoryCustom.setCid("6");
        categoryMapper.updateCategoryByCid(categoryCustom);
    }

    @Test
    public void updateSecondClassByCidTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        CategoryCustom categoryCustom = new CategoryCustom();
        categoryCustom.setCname("网页设计1");
        categoryCustom.setDesc("网页设计2");
        categoryCustom.setCid("757BDAB506A445EC8DEDA4CE04303B9F");
        categoryMapper.updateSecondClassByCid(categoryCustom);
    }

    @Test
    public void findChildCountByParentIdTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        Integer num = categoryMapper.findChildCountByParentId("1");
        System.out.println(num);
    }

    @Test
    public void deleteParentByCidTest() throws Exception {
        CategoryMapper categoryMapper = (CategoryMapper) applicationContext.getBean("categoryMapper");
        categoryMapper.deleteByCid("97dbc352-5f49-449c-a6ae-99c3bf95");
    }
}
