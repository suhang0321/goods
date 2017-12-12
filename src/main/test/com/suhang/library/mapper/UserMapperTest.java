package com.suhang.library.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suhang.library.po.User;
import com.suhang.library.po.UserCustom;

/**
 * @author hang.su
 * @since 2017-10-31 9:37
 */
public class UserMapperTest {
    private ClassPathXmlApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    }

    @Test
    public void findUserByUidTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        UserCustom userCustom = usermapper.findUserByUid("2");
        String loginname = userCustom.getLoginname();
        System.out.println(loginname);
    }

    @Test
    public void insertUserTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        UserCustom userCustom = new UserCustom();
        userCustom.setUid("567");
        userCustom.setEmail("nlj@163.com");
        userCustom.setLoginname("dfgh");
        userCustom.setLoginpass("123");
        usermapper.insertUser(userCustom);
    }

    @Test
    public void queryCountByLoginnameTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        int count = usermapper.queryCountByLoginname("suhang");
        System.out.println(count);
    }

    @Test
    public void queryCountByEmailTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        int count = usermapper.queryCountByEmail("tomhon@qq.com");
        System.out.println(count);
    }

    @Test
    public void findUserByCodeTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        UserCustom userCustom = usermapper.findUserByCode("x");
        System.out.println(userCustom.getLoginname());
    }

    @Test
    public void updateStatusByidTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("status","1");
        map.put("uid","x");
        usermapper.updateStatusByid(map);
    }

    @Test
    public void selectByNameAndPassTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("loginname","lsh");
        map.put("loginpass","123");
        UserCustom userCustom = usermapper.findByNameAndPass(map);
        System.out.println(userCustom.getLoginname());
    }

    @Test
    public void fingByUidAndPassTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("uid","x");
        map.put("loginpass","123");
        Integer number = usermapper.findByUidAndPass(map);
        System.out.println(number);
    }

    @Test
    public void updatePassByUidTest() throws Exception {
        UserMapper usermapper = (UserMapper) applicationContext.getBean("userMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("newloginpass","12345");
        map.put("uid","x");
        usermapper.updatePassByUid(map);
    }
    @Test
    public void numberTest()throws Exception{
        Random random = new Random();
        List listnumber = new ArrayList();
        for(int i=0;i<2;i++){
            int result = random.nextInt(12);
            System.out.println(result+" ");
        }


    }
}
