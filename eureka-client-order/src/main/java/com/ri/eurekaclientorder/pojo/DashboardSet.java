package com.ri.eurekaclientorder.pojo;


import java.io.Serializable;
import java.util.Date;

public class DashboardSet implements Serializable {

    private String id;

    private String userId;

    private Integer layoutNum;

    private String setType;

    private String setName;

    private String chartType;

    private String availablezone;

    private Date startTime;

    private Date endTime;

    private String timeDivision;

    private String resourcepool;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public DashboardSet setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public DashboardSet setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getLayoutNum() {
        return layoutNum;
    }

    public DashboardSet setLayoutNum(Integer layoutNum) {
        this.layoutNum = layoutNum;
        return this;
    }

    public String getSetType() {
        return setType;
    }

    public DashboardSet setSetType(String setType) {
        this.setType = setType;
        return this;
    }

    public String getSetName() {
        return setName;
    }

    public DashboardSet setSetName(String setName) {
        this.setName = setName;
        return this;
    }

    public String getChartType() {
        return chartType;
    }

    public DashboardSet setChartType(String chartType) {
        this.chartType = chartType;
        return this;
    }

    public String getAvailablezone() {
        return availablezone;
    }

    public DashboardSet setAvailablezone(String availablezone) {
        this.availablezone = availablezone;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public DashboardSet setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public DashboardSet setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getTimeDivision() {
        return timeDivision;
    }

    public DashboardSet setTimeDivision(String timeDivision) {
        this.timeDivision = timeDivision;
        return this;
    }

    public String getResourcepool() {
        return resourcepool;
    }

    public DashboardSet setResourcepool(String resourcepool) {
        this.resourcepool = resourcepool;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DashboardSet setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public DashboardSet setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "DashboardSet{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", layoutNum=" + layoutNum +
                ", setType='" + setType + '\'' +
                ", setName='" + setName + '\'' +
                ", chartType='" + chartType + '\'' +
                ", availablezone='" + availablezone + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeDivision='" + timeDivision + '\'' +
                ", resourcepool='" + resourcepool + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
