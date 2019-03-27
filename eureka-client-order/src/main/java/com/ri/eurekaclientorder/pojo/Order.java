package com.ri.eurekaclientorder.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="order")
public class Order {

    //唯一id
    private String _id;
    //用户Id
    private String userId;
    //订单编号
    private Long orderId;
    //产品名称
    private String prodName;
    //订单类型（新购 升级 续费 退款）
    private String orderType;
    //创建时间
    private Date createTime;
    //支付时间
    private Date payTime;
    //状态（支付状态-未支付 已支付 作废）
    private String status;
    //应付金额
    private Double paymentAmount;
    //原价
    private Double originalPrice;
    //产品描述
    private String prodDesc;
    //数量
    private Integer quantity;
    //配置信息
    private String configInfo;
    //付费方式
    private String billingMethod;
    //产品生效时间
    private Date startTime;
    //产品失效时间
    private Date endTime;
    //开通参数
    private Object openParam;
    //订购周期
    private Integer orderPeriod;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(String configInfo) {
        this.configInfo = configInfo;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Object getOpenParam() {
        return openParam;
    }

    public void setOpenParam(Object openParam) {
        this.openParam = openParam;
    }

    public Integer getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(Integer orderPeriod) {
        this.orderPeriod = orderPeriod;
    }
}
