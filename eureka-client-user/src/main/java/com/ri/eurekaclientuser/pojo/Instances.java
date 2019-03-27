package com.ri.eurekaclientuser.pojo;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.domains.LongId;

import java.io.Serializable;
import java.util.Date;

@Entity(table = "instances")
public class Instances extends LongId implements Serializable {
    private Long accountId;

    private Date activeDate;

    private String availableZone;

    private Date createDate;

    private Date inactiveDate;

    private Long instanceId;

    private String instanceStatus;

    private String name;

    private Date normalizeDate;

    private String optState;

    private Integer period;

    private String pool;

    private Integer serviceType;

    private Long tenantId;

    private String uuid;

    private Integer productId;

    private String userId;

    private String billingMethod;

    public Long getAccountId() {
        return accountId;
    }

    public Instances setAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public Instances setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
        return this;
    }

    public String getAvailableZone() {
        return availableZone;
    }

    public Instances setAvailableZone(String availableZone) {
        this.availableZone = availableZone;
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Instances setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public Instances setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
        return this;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public Instances setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public Instances setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
        return this;
    }

    public String getName() {
        return name;
    }

    public Instances setName(String name) {
        this.name = name;
        return this;
    }

    public Date getNormalizeDate() {
        return normalizeDate;
    }

    public Instances setNormalizeDate(Date normalizeDate) {
        this.normalizeDate = normalizeDate;
        return this;
    }

    public String getOptState() {
        return optState;
    }

    public Instances setOptState(String optState) {
        this.optState = optState;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public Instances setPeriod(Integer period) {
        this.period = period;
        return this;
    }

    public String getPool() {
        return pool;
    }

    public Instances setPool(String pool) {
        this.pool = pool;
        return this;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public Instances setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Instances setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Instances setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Integer getProductId() {
        return productId;
    }

    public Instances setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Instances setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public Instances setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
        return this;
    }
}
