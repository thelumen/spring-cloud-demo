package com.ri.eurekaclientorder.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection="order_detail")
public class OrderDetail {

    //唯一id
    private String _id;
    //用户id
    private String userId;
    //订单id
    private Long orderId;
    //规格名称
    private String flavorName;
    //规格编码
    private Integer flavorCode;
    //产品类型
    private String prodType;
    //产品编码
    private String prodCode;
    //计费方式
    private String billingMethod;
    //产品单价
    private BigDecimal realPrice;
    //产品原价
    private BigDecimal prodOriginalPrice;
    //产品折扣后价格
    private BigDecimal prodPrice;
    //cpu数量
    private Integer cpu;
    //内存数量
    private Integer memory;
    //存储数量
    private Integer volume;
    //带宽数量
    private Integer bandwidth;
    //创建时间
    private Date createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public Integer getFlavorCode() {
        return flavorCode;
    }

    public void setFlavorCode(Integer flavorCode) {
        this.flavorCode = flavorCode;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getProdOriginalPrice() {
        return prodOriginalPrice;
    }

    public void setProdOriginalPrice(BigDecimal prodOriginalPrice) {
        this.prodOriginalPrice = prodOriginalPrice;
    }

    public BigDecimal getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
