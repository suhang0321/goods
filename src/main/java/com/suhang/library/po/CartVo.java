package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-15 13:26
 */
public class CartVo {
    private String cartItemId;
    private int quantity;
    private String bid;
    private String uid;
    private int orderBy;
    private UserCustom userCustom;
    private BookCustom bookCustom;

    @Override
    public String toString() {
        return "CartVo{" + "cartItemId='" + cartItemId + '\'' + ", quantity=" + quantity + ", bid='" + bid + '\'' + ", uid='" + uid + '\'' + ", orderBy=" + orderBy + ", userCustom=" + userCustom + ", bookCustom=" + bookCustom + '}';
    }

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

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }

    public void setBookCustom(BookCustom bookCustom) {
        this.bookCustom = bookCustom;
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

    public int getOrderBy() {
        return orderBy;
    }

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public BookCustom getBookCustom() {
        return bookCustom;
    }
}
