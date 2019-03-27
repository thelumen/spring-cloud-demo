package com.ri.product.pojo;

import org.springframework.data.mybatis.annotations.Entity;
import org.springframework.data.mybatis.domains.LongId;

import java.io.Serializable;

@Entity(table = "vm_instance")
public class VmInstance extends LongId implements Serializable {

    private Integer cpu;

    private Integer disk;

    private String imageId;

    private String ipAddress;

    private Integer memory;

    private String physicalHost;

    private Long instanceId;

    public Integer getCpu() {
        return cpu;
    }

    public VmInstance setCpu(Integer cpu) {
        this.cpu = cpu;
        return this;
    }

    public Integer getDisk() {
        return disk;
    }

    public VmInstance setDisk(Integer disk) {
        this.disk = disk;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public VmInstance setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public VmInstance setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Integer getMemory() {
        return memory;
    }

    public VmInstance setMemory(Integer memory) {
        this.memory = memory;
        return this;
    }

    public String getPhysicalHost() {
        return physicalHost;
    }

    public VmInstance setPhysicalHost(String physicalHost) {
        this.physicalHost = physicalHost;
        return this;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public VmInstance setInstanceId(Long instanceId) {
        this.instanceId = instanceId;
        return this;
    }
}
