package com.suhang.library.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suhang.library.po.OrderCustom;
import com.suhang.library.po.OrderItem;
import com.suhang.library.po.OrderVo;

/**
 * @author hang.su
 * @since 2017-11-16 8:52
 */
public class OrderMapperTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
    }

    @Test
    public void findOrderByUidTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderVo orderVo = new OrderVo();
        orderVo.setSnep(0);
        orderVo.setPs(8);
        orderVo.setUid("32DB3700D2564254982BC58B0E4D95BC");
        List<OrderCustom> orderCustoms = orderMapper.findOrderByUid(orderVo);
        for (OrderCustom orderCustom : orderCustoms) {
            System.out.println(orderCustom);
        }
    }

    @Test
    public void findOrderCountByUidTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        Integer tr = orderMapper.findOrderCountByUid("32DB3700D2564254982BC58B0E4D95BC");
        System.out.println(tr);
    }

    @Test
    public void findOrderItemByOid() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        List<OrderItem> orderItems = orderMapper.findOrderItemsByOid("159B58B3BBCB4069A550BF1A358B4905");
        for (OrderItem orderItem:orderItems){
            System.out.println(orderItem);
            System.out.println(orderItem.getCurrPrice());
        }
    }

    @Test
    public void insertOrderTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderCustom orderCustom = new OrderCustom();
        orderCustom.setOid(UUID.randomUUID().toString().substring(0,32));
        orderCustom.setAddress("jilin");
        orderCustom.setStatus(1);
        orderCustom.setTotal(95);
        orderCustom.setOrdertime(String.format("%tF %<tT",new Date()));
        orderCustom.setUid("32DB3700D2564254982BC58B0E4D95BC");
        orderMapper.insertOrder(orderCustom);
    }

    @Test
    public void insertOrderItemTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderItem orderItem = new OrderItem();
        orderItem.setBid("B7A7DA7A94E54054841EED1F70C3027C");
        orderItem.setBname("java");
        orderItem.setCurrPrice(33);
        orderItem.setImage_b("book_img/22786088-1_b.jpg");
        orderItem.setOid("96C6D91D916E472681EEC37B1770DE87");
        orderItem.setOrderItemId(UUID.randomUUID().toString().substring(0,32));
        orderItem.setSubtotal(66);
        orderItem.setQuantity(2);
        orderMapper.insertOrderItem(orderItem);
    }

    @Test
    public void findOrderByOidTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderCustom orderCustom = orderMapper.findOrderByOid("058F48DA33694C6D8F5C2C13F3D26CEA");
        System.out.println(orderCustom.getAddress());
    }

    @Test
    public void updateStatusByOidTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderCustom orderCustom = new OrderCustom();
        orderCustom.setStatus(3);
        orderCustom.setOid("00b95e67-a596-4f46-a701-b05a1178");
        orderMapper.updateStatusByOid(orderCustom);
    }

    @Test
    public void findAllTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderVo orderVo = new OrderVo();
        orderVo.setSnep(0);
        orderVo.setPs(10);
        List<OrderCustom> orderCustomList = orderMapper.findAll(orderVo);
        System.out.println(orderCustomList);
    }

    @Test
    public void findAllOrderCountTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        Integer num = orderMapper.findAllOrderCount();
        System.out.println(num);
    }

    @Test
    public void findOrderByStatusTest() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        OrderVo orderVo = new OrderVo();
        orderVo.setSnep(0);
        orderVo.setPs(10);
        orderVo.setStatus(1);
        List<OrderCustom> orderCustomList = orderMapper.findOrderByStatus(orderVo);
        System.out.println(orderCustomList);
    }

    @Test
    public void findCountByStatus() throws Exception {
        OrderMapper orderMapper = (OrderMapper) applicationContext.getBean("orderMapper");
        Integer num = orderMapper.findCountByStatus(1);
        System.out.println(num);
    }
}
