package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-10 14:40
 */
public class BookVo {
    private BookCustom bookCustom;
    private String cid;
    private String author;
    private int pc;//pagecode 当前的页码
    private int ps;//pagesize 一页显示多少条book
    private int tr;//totalrecord 一共有多少条记录
    private int tp;//totalpage 一共有多少页码 由tr/ps 计算得出
    private String url;
    private int snep;//startnumeachpage,执行sqllimit语句后的第一个数字
    private String press;
    private String bname;

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBname() {

        return bname;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPress() {

        return press;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {

        return author;
    }

    public void setSnep(int snep) {
        this.snep = snep;
    }

    public int getSnep() {

        return snep;
    }

    public BookCustom getBookCustom() {
        return bookCustom;
    }

    public String getCid() {
        return cid;
    }

    public int getPc() {
        return pc;
    }

    public int getPs() {
        return ps;
    }

    public int getTr() {
        return tr;
    }

    public int getTp() {
        return tp;
    }

    public String getUrl() {
        return url;
    }

    public void setBookCustom(BookCustom bookCustom) {
        this.bookCustom = bookCustom;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setTr(int tr) {
        this.tr = tr;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
