package com.hyh.cstore.vo;

import java.util.Objects;

public class CartVO {
    private Integer uid;
    private Integer pid;
    private Integer cid;
    private String image;
    private String title;
    private Long price;
    private Integer num;
    private Long realPrice;

    public CartVO() {
    }

    public CartVO(Integer uid, Integer pid, Integer cid, String image, String title, Long price, Integer num, Long realPrice) {
        this.uid = uid;
        this.pid = pid;
        this.cid = cid;
        this.image = image;
        this.title = title;
        this.price = price;
        this.num = num;
        this.realPrice = realPrice;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVO)) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(uid, cartVO.uid) && Objects.equals(pid, cartVO.pid) && Objects.equals(cid, cartVO.cid) && Objects.equals(image, cartVO.image) && Objects.equals(title, cartVO.title) && Objects.equals(price, cartVO.price) && Objects.equals(num, cartVO.num) && Objects.equals(realPrice, cartVO.realPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, pid, cid, image, title, price, num, realPrice);
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", cid=" + cid +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num='" + num + '\'' +
                ", realPrice=" + realPrice +
                '}';
    }
}