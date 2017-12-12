package com.suhang.library.mapper;

import java.util.List;
import java.util.Map;

import com.suhang.library.po.OrderCustom;
import com.suhang.library.po.OrderItem;
import com.suhang.library.po.OrderVo;

/**
 * @author hang.su
 * @since 2017-11-15 23:09
 */
public interface OrderMapper {
    public List<OrderCustom> findOrderByUid(OrderVo orderVo)throws Exception;

    public Integer findOrderCountByUid(String uid)throws Exception;

    public List<OrderItem> findOrderItemsByOid(String oid)throws Exception;

    public void insertOrder(OrderCustom orderCustom)throws Exception;

    public void insertOrderItem(OrderItem orderItem)throws Exception;

    public OrderCustom findOrderByOid(String oid)throws Exception;

    public void updateStatusByOid(OrderCustom orderCustom)throws Exception;

    public List<OrderCustom> findAll(OrderVo orderVo)throws Exception;

    public Integer findAllOrderCount()throws Exception;

    public List<OrderCustom> findOrderByStatus(OrderVo orderVo)throws Exception;

    public Integer findCountByStatus(Integer status)throws Exception;
}
