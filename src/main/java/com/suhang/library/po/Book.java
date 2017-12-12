package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-10 14:17
 */
public class Book {
    private String bid;
    private String bname;
    private String author;
    private double price;
    private double currPrice;
    private double discount;
    private String press;
    private String publishtime;
    private int edition;
    private int pageNum;
    private int wordNum;
    private String printtime;
    private int booksize;
    private String paper;
    private String cid;
    private String image_w;
    private String image_b;
    private int orderBy;

    public String getBid() {
        return bid;
    }

    public String getBname() {
        return bname;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public String getPress() {
        return press;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public int getEdition() {
        return edition;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public String getPrinttime() {
        return printtime;
    }

    public int getBooksize() {
        return booksize;
    }

    public String getPaper() {
        return paper;
    }

    public String getCid() {
        return cid;
    }

    public String getImage_w() {
        return image_w;
    }

    public String getImage_b() {
        return image_b;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCurrPrice(double currPrice) {
        this.currPrice = currPrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public void setPrinttime(String printtime) {
        this.printtime = printtime;
    }

    public void setBooksize(int booksize) {
        this.booksize = booksize;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setImage_w(String image_w) {
        this.image_w = image_w;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "Book{" + "bid='" + bid + '\'' + ", bname='" + bname + '\'' + ", author='" + author + '\'' + ", price=" + price + ", currPrice=" + currPrice + ", discount=" + discount + ", press='" + press + '\'' + ", publishtime='" + publishtime + '\'' + ", edition=" + edition + ", pageNum=" + pageNum + ", wordNum=" + wordNum + ", printtime='" + printtime + '\'' + ", booksize=" + booksize + ", paper='" + paper + '\'' + ", cid='" + cid + '\'' + ", image_w='" + image_w + '\'' + ", image_b='" + image_b + '\'' + ", orderBy=" + orderBy + '}';
    }
}
