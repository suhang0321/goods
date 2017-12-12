package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-15 23:02
 */
public class Order {
    private String oid;
    private String ordertime;
    private double total;
    private int status;
    private String address;
    private String uid;

    @Override
    public String toString() {
        return "Order{" + "oid='" + oid + '\'' + ", ordertime='" + ordertime + '\'' + ", total=" + total + ", status=" + status + ", address='" + address + '\'' + ", uid='" + uid + '\'' + '}';
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public double getTotal() {
        return total;
    }

    public int getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getUid() {
        return uid;
    }
}
