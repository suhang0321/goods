package com.suhang.library.po;

/**
 * @author hang.su
 * @since 2017-11-13 14:34
 */
public class CartItemVo {
    private String image_w;
    private String bname;
    private double currPrice;
    private int quantity;
    private double subtotal;
    private String cartItemId;
    private String bid;
    private String author;
    private double price;
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
    private String image_b;
    private int orderBy;

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setImage_b(String image_b) {
        this.image_b = image_b;
    }

    public String getImage_b() {
        return image_b;
    }

    @Override
    public String toString() {
        return "CartItemVo{" + "image_w='" + image_w + '\'' + ", bname='" + bname + '\'' + ", currPrice=" + currPrice + ", quantity=" + quantity + ", subtotal=" + subtotal + ", cartItemId='" + cartItemId + '\'' + ", bid='" + bid + '\'' + ", author='" + author + '\'' + ", price=" + price + ", discount=" + discount + ", press='" + press + '\'' + ", publishtime='" + publishtime + '\'' + ", edition=" + edition + ", pageNum=" + pageNum + ", wordNum=" + wordNum + ", printtime='" + printtime + '\'' + ", booksize=" + booksize + ", paper='" + paper + '\'' + ", cid='" + cid + '\'' + '}';
    }

    public void setImage_w(String image_w) {
        this.image_w = image_w;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setCurrPrice(double currPrice) {
        this.currPrice = currPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getImage_w() {
        return image_w;
    }

    public String getBname() {
        return bname;
    }

    public double getCurrPrice() {
        return currPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public String getBid() {
        return bid;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
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
}
