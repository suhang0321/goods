package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-13 14:30
 */
public class CartItem {
    private String cartItemId;
    private int quantity;
    private String bid;
    private String uid;
    private int orderby;

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setOrderby(int orderby) {
        this.orderby = orderby;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getBid() {
        return bid;
    }

    public String getUid() {
        return uid;
    }

    public int getOrderby() {
        return orderby;
    }
}
