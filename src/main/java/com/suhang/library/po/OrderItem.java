package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-15 23:05
 */
public class OrderItem {
    private String orderItemId;
    private int quantity;
    private double subtotal;
    private String bid;
    private String bname;
    private double currPrice;
    private String image_b;
    private String oid;

    @Override
    public String toString() {
        return "OrderItem{" + "orderItemId='" + orderItemId + '\'' + ", quantity=" + quantity + ", subtotal=" + subtotal + ", bid='" + bid + '\'' + ", bname='" + bname + '\'' + ", currPrice=" + currPrice + ", image_b='" + image_b + '\'' + ", oid='" + oid + '\'' + '}';
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setCurrPrice(double currPrice) {
        this.currPrice = currPrice;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getBid() {
        return bid;
    }

    public String getBname() {
        return bname;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public String getImage_b() {
        return image_b;
    }

    public String getOid() {
        return oid;
    }
}
