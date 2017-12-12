package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-09 8:14
 */
public class Category {
    private String cid;
    private String cname;
    private String pid;
    private String desc;
    private int orderBy;

    @Override
    public String toString() {
        return "Category{" + "cid='" + cid + '\'' + ", cname='" + cname + '\'' + ", pid='" + pid + '\'' + ", desc='" + desc + '\'' + ", orderBy=" + orderBy + '}';
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public String getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public String getPid() {
        return pid;
    }

    public String getDesc() {
        return desc;
    }

    public int getOrderBy() {
        return orderBy;
    }
}
