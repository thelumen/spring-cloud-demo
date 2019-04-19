package com.ri.eurekaclientorder.bo;

import java.math.BigDecimal;

public class DashboardBo {
    private String availableZone;

    private String pool;

    private String time;

    private Integer serviceType;

    private BigDecimal total;

    private Integer used;

    private Integer margin;

    private Long[] orderArray;

    public Long[] getOrderArray() {
        return orderArray;
    }

    public DashboardBo setOrderArray(Long[] orderArray) {
        this.orderArray = orderArray;
        return this;
    }

    public String getAvailableZone() {
        return availableZone;
    }

    public DashboardBo setAvailableZone(String availableZone) {
        this.availableZone = availableZone;
        return this;
    }

    public String getPool() {
        return pool;
    }

    public DashboardBo setPool(String pool) {
        this.pool = pool;
        return this;
    }

    public String getTime() {
        return time;
    }

    public DashboardBo setTime(String time) {
        this.time = time;
        return this;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public DashboardBo setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public DashboardBo setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public Integer getUsed() {
        return used;
    }

    public DashboardBo setUsed(Integer used) {
        this.used = used;
        return this;
    }

    public Integer getMargin() {
        return margin;
    }

    public DashboardBo setMargin(Integer margin) {
        this.margin = margin;
        return this;
    }

    @Override
    public String toString() {
        return "DashboardBo{" +
                "availableZone='" + availableZone + '\'' +
                ", pool='" + pool + '\'' +
                ", time='" + time + '\'' +
                ", serviceType=" + serviceType +
                ", total=" + total +
                ", used=" + used +
                ", margin=" + margin +
                '}';
    }
}
