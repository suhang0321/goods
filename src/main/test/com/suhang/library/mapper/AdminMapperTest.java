package com.suhang.library.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suhang.library.po.Admin;

/**
 * @author hang.su
 * @since 2017-11-21 13:53
 */
public class AdminMapperTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    }

    @Test
    public void findAdminByNameAndPwdTest() throws Exception {
        AdminMapper adminMapper = (AdminMapper) applicationContext.getBean("adminMapper");
        Admin admin = new Admin();
        admin.setAdminname("liuBei");
        admin.setAdminpwd("123");
        Admin adminDb= adminMapper.findAdminByNameAndPwd(admin);
        System.out.println(adminDb);
    }
}
