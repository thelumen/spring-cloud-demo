package com.ri.eurekaclientuser.service.impl;

import com.ri.eurekaclientuser.dao.InstanceRepository;
import com.ri.eurekaclientuser.dao.VmInstanceRepository;
import com.ri.eurekaclientuser.pojo.Instances;
import com.ri.eurekaclientuser.pojo.VmInstance;
import com.ri.eurekaclientuser.service.MySQLService;
import com.ri.eurekaclientuser.util.DateUtil;
import com.ri.eurekaclientuser.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MySQLServiceImpl implements MySQLService {

    @Autowired
    VmInstanceRepository vmInstanceRepository;

    @Autowired
    InstanceRepository instanceRepository;

    @Override
    public void insertTestValue() {
        int count = 100 * 1000;
        long idBase = 100000000000030L;
        int vmInstanceServiceType = 1001;
        String instanceStatus = "running";
        String pool1 = "pool1";
        String pool2 = "pool2";
        String az1 = "az1";
        String az2 = "az2";
        List<Instances> instancesList = new ArrayList<>(count);
        List<VmInstance> vmInstanceList = new ArrayList<>(count);

        for (int i = 1; i < count; i++) {
            Date date = DateUtil.getDateFromNow(Calendar.MINUTE, -i);
            Instances instances = new Instances()
                    .setInstanceId(idBase + i).setCreateDate(date)
                    .setServiceType(vmInstanceServiceType)
                    .setInstanceStatus(instanceStatus);
            if (i % 5 == 0) {
                instances.setPool(pool2);
            } else {
                instances.setPool(pool1);
            }
            if (i % 3 == 0) {
                instances.setAvailableZone(az2);
            } else {
                instances.setAvailableZone(az1);
            }
            instancesList.add(instances);
            VmInstance vmInstance = new VmInstance()
                    .setInstanceId(idBase + i)
                    .setCpu(Random.getRandom(1, 10))
                    .setMemory(Random.getRandom(1, 16));
            vmInstanceList.add(vmInstance);
        }

        instanceRepository.save(instancesList);
        vmInstanceRepository.save(vmInstanceList);
    }


}
