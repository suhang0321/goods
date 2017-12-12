package com.suhang.library.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suhang.library.po.CartItemVo;
import com.suhang.library.po.CartVo;

/**
 * @author hang.su
 * @since 2017-11-13 15:01
 */
public class CartItemMapperTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    }

    @Test
    public void findCartByUidTest() throws Exception {
       CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
       List<CartItemVo> cartItemVoList = cartItemMapper.findCartByUid("xxx");
       for (CartItemVo cartItemVo:cartItemVoList){
           System.out.println(cartItemVo.getImage_b());
       }
    }

    @Test
    public void findByBidAndUidTest() throws Exception {
        CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("uid","xxx");
        map.put("bid","CE01F15D435A4C51B0AD8202A318DCA7");
        CartItemVo cartItemVo = cartItemMapper.findByUindAndBid(map);
        System.out.println(cartItemVo);
    }

    @Test
    public void updateQuantityByCartIdTest() throws Exception {
        CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("quantity","2");
        map.put("cartItemId","B8939FC55131469CAB11E3924D40185B");
        cartItemMapper.updateQuantityByCartId(map);
    }

    @Test
    public void insertCartItemTest() throws Exception {
        CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
        Map<String,String> map = new HashMap<String,String>();
        map.put("cartItemId","123456");
        map.put("quantity","2");
        map.put("bid","000A18FDB38F470DBE9CD0972BADB23F");
        map.put("uid","5bc91cce-89d0-4d8d-aa08-c1d1a56");
        cartItemMapper.insertCartItem(map);
    }

    @Test
    public void deleteCartItemByCartItemIdTeat() throws Exception {
        CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
        cartItemMapper.deleteCartItemByCartItemId("3c80e450-77e6-4f88-9fa0-bb30ce1");

    }

    @Test
    public void findCartItemByCartItemIdTest() throws Exception {
        CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
        CartVo cartVo = cartItemMapper.findCartItemByCartItemId("1a4ee69d-57de-4ed9-9d99-6ae0d043");
        System.out.println(cartVo);
    }

    @Test
    public void findCartItemByCartItemIdTypeTest() throws Exception {
        CartItemMapper cartItemMapper = (CartItemMapper) applicationContext.getBean("cartItemMapper");
        CartItemVo cartItemVo = cartItemMapper.findCartItemByCartItemIdType("1a4ee69d-57de-4ed9-9d99-6ae0d043");
        System.out.println(cartItemVo);
    }
}
