package com.hyh.cstore.vo;

import java.util.Date;
import java.util.Objects;

public class OrderVO {
    private String image;
    private String title;
    private Long price;
    private Integer num;
    private Long totalPrice;
    private Date orderTime;
    private Integer oid;
    private Integer id;
    private String recvName;

    public OrderVO() {
    }

    public OrderVO(String image, String title, Long price, Integer num, Long totalPrice, Date orderTime, Integer oid, Integer id, String recvName) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.num = num;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.oid = oid;
        this.id = id;
        this.recvName = recvName;
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

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderVO)) return false;
        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(image, orderVO.image) && Objects.equals(title, orderVO.title) && Objects.equals(price, orderVO.price) && Objects.equals(num, orderVO.num) && Objects.equals(totalPrice, orderVO.totalPrice) && Objects.equals(orderTime, orderVO.orderTime) && Objects.equals(oid, orderVO.oid) && Objects.equals(id, orderVO.id) && Objects.equals(recvName, orderVO.recvName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, title, price, num, totalPrice, orderTime, oid, id, recvName);
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", totalPrice=" + totalPrice +
                ", orderTime=" + orderTime +
                ", oid=" + oid +
                ", id=" + id +
                ", recvName='" + recvName + '\'' +
                '}';
    }
}
