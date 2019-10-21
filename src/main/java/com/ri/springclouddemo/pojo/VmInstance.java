package com.ri.springclouddemo.pojo;

import com.ri.springclouddemo.imp.CustomizeCheck;

import java.util.List;

public class VmInstance {

    @CustomizeCheck(Pattern = "[a-zA-Z\\d\\u4E00-\\u9FA5]{1,5}")
    private Integer cpu;

    @CustomizeCheck(Pattern = "[a-zA-Z\\d\\u4E00-\\u9FA5]{1,5}", canNull = true)
    private Integer disk;

    @CustomizeCheck(Pattern = "[a-zA-Z\\d\\u4E00-\\u9FA5]{1,5}")
    private String imageId;

    @CustomizeCheck(Pattern = "[a-zA-Z\\d\\u4E00-\\u9FA5]{1,5}")
    private String ipAddress;

    private Integer memory;

    private String physicalHost;

    private Long instanceId;

    private List<VmInstance> vmInstances;

    public VmInstance() {
    }

    public VmInstance(Integer cpu, Integer disk, String imageId, String ipAddress) {
        this.cpu = cpu;
        this.disk = disk;
        this.imageId = imageId;
        this.ipAddress = ipAddress;
    }

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

    public List<VmInstance> getVmInstances() {
        return vmInstances;
    }

    public void setVmInstances(List<VmInstance> vmInstances) {
        this.vmInstances = vmInstances;
    }
}
