package com.suhang.library.service;

import java.util.List;

import com.suhang.library.po.Order;
import com.suhang.library.po.OrderCustom;
import com.suhang.library.po.OrderItem;

/**
 * @author hang.su
 * @since 2017-11-16 8:36
 */
public interface OrderService {
    public List<OrderCustom> findOrderByUid(String uid,Integer pc,Integer ps)throws Exception;

    public Integer findOrderCountByUid(String uid)throws Exception;

    public List<OrderItem> findOrderItemsByOid(String oid)throws Exception;

    public OrderCustom insertOrder(String address,double total,String uid)throws Exception;

    public OrderItem insertOrderItem(String bid,String bname,String image_b,String oid,double subtotal,Integer quantity,double currPrice)throws Exception;

    public OrderCustom findOrderByOid(String oid)throws Exception;

    public void updateStatusByOid(String oid ,Integer status)throws Exception;

    public List<OrderCustom> findAll(Integer pc,Integer ps)throws Exception;

    public Integer findAllOrderCount()throws Exception;

    public List<OrderCustom> findOrderByStatus(Integer pc,Integer ps,Integer status)throws Exception;

    public Integer findCountByStatus(Integer status)throws Exception;


}
