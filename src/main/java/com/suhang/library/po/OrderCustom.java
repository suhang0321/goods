package com.suhang.library.po;

import java.util.List;

/**
 * @author hang.su
 * @since 2017-11-15 23:10
 */
public class OrderCustom extends Order {
    private List<OrderItem> orderItemList;

    @Override
    public String toString() {
        return "OrderCustom{" + "orderItemList=" + orderItemList + '}';
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }
}
