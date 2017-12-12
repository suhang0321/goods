package com.suhang.library.serviceimp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Ordering;
import com.suhang.library.mapper.OrderMapper;
import com.suhang.library.po.OrderCustom;
import com.suhang.library.po.OrderItem;
import com.suhang.library.po.OrderVo;
import com.suhang.library.service.OrderService;

/**
 * @author hang.su
 * @since 2017-11-16 8:36
 */
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderCustom> findOrderByUid(String uid, Integer pc, Integer ps) throws Exception {
        //pagecode pagesize startnumbereachpage
        OrderVo orderVo = new OrderVo();
        Integer snep = (pc-1)*ps;
        orderVo.setUid(uid);
        orderVo.setSnep(snep);
        orderVo.setPs(ps);
        List<OrderCustom> orderCustomList = orderMapper.findOrderByUid(orderVo);
        return orderCustomList;
    }

    @Override
    public List<OrderCustom> findAll(Integer pc, Integer ps) throws Exception {
        OrderVo orderVo = new OrderVo();
        Integer snep = (pc-1)*ps;
        orderVo.setSnep(snep);
        orderVo.setPs(ps);
        List<OrderCustom> orderCustomList = orderMapper.findAll(orderVo);
        return orderCustomList;
    }

    @Override
    public Integer findOrderCountByUid(String uid) throws Exception {
        Integer tr = orderMapper.findOrderCountByUid(uid);
        return tr;
    }

    @Override
    public Integer findAllOrderCount() throws Exception {
        Integer tr = orderMapper.findAllOrderCount();
        return tr;
    }

    @Override
    public List<OrderItem> findOrderItemsByOid(String oid) throws Exception {
        List<OrderItem> orderItemList = orderMapper.findOrderItemsByOid(oid);
        return orderItemList;
    }

    @Override
    public OrderCustom insertOrder(String address,double total,String uid) throws Exception {
        OrderCustom orderCustom = new OrderCustom();
        String uuid = UUID.randomUUID().toString();
        String oid = uuid.substring(0,32);
        orderCustom.setOid(oid);
        orderCustom.setAddress(address);
        orderCustom.setStatus(1);
        orderCustom.setTotal(total);
        orderCustom.setOrdertime(String.format("%tF %<tT",new Date()));
        orderCustom.setUid(uid);
        orderMapper.insertOrder(orderCustom);
        return orderCustom;
    }

    @Override
    public OrderItem insertOrderItem(String bid,String bname,String image_b,String oid,double subtotal,Integer quantity,double currPrice) throws Exception {
        OrderItem orderItem = new OrderItem();
        orderItem.setBid(bid);
        orderItem.setBname(bname);
        orderItem.setCurrPrice(currPrice);
        orderItem.setImage_b(image_b);
        orderItem.setOid(oid);
        orderItem.setOrderItemId(UUID.randomUUID().toString().substring(0,32));
        orderItem.setSubtotal(subtotal);
        orderItem.setQuantity(quantity);
        orderMapper.insertOrderItem(orderItem);
        return orderItem;
    }

    @Override
    public OrderCustom findOrderByOid(String oid) throws Exception {
        OrderCustom orderCustom = orderMapper.findOrderByOid(oid);
        return orderCustom;
    }

    @Override
    public void updateStatusByOid(String oid, Integer status) throws Exception {
        OrderCustom orderCustom = new OrderCustom();
        orderCustom.setOid(oid);
        orderCustom.setStatus(status);
        orderMapper.updateStatusByOid(orderCustom);
    }

    @Override
    public List<OrderCustom> findOrderByStatus(Integer pc, Integer ps, Integer status) throws Exception {
        Integer snep = (pc-1)*ps;
        OrderVo orderVo = new OrderVo();
        orderVo.setStatus(status);
        orderVo.setPs(ps);
        orderVo.setSnep(snep);
        List<OrderCustom> orderCustomList = orderMapper.findOrderByStatus(orderVo);
        return orderCustomList;
    }

    @Override
    public Integer findCountByStatus(Integer status) throws Exception {
        Integer tr = orderMapper.findCountByStatus(status);
        return tr;
    }
}
