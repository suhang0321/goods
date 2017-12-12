package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-16 9:09
 */
public class OrderVo {
    private int snep;//start number each page
    private int ps;//pagesize
    private int tr;//total record
    private int pc;//pagecode
    private int tp;//totalpage
    private String url;
    private String uid;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "OrderVo{" + "snep=" + snep + ", ps=" + ps + ", tr=" + tr + ", pc=" + pc + ", tp=" + tp + ", url='" + url + '\'' + ", uid='" + uid + '\'' + '}';
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setSnep(int snep) {
        this.snep = snep;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSnep() {
        return snep;
    }

    public int getPs() {
        return ps;
    }

    public int getTr() {
        return tr;
    }

    public int getPc() {
        return pc;
    }

    public int getTp() {
        return tp;
    }

    public String getUrl() {
        return url;
    }
}
