package com.ri.product.service.impl;

import com.ri.product.dao.InstanceRepository;
import com.ri.product.dao.VmInstanceRepository;
import com.ri.product.pojo.Instances;
import com.ri.product.pojo.VmInstance;
import com.ri.product.service.MySQLService;
import com.ri.product.util.DateUtil;
import com.ri.product.util.Random;
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
    public long insertTestValue() {
        int count = 900 * 1000;
        long idBase = 100000000100029L;
        int vmInstanceServiceType = 1001;
        String instanceStatus = "running";
        String pool1 = "pool1";
        String pool2 = "pool2";
        String az1 = "az1";
        String az2 = "az2";
        List<Instances> instancesList = new ArrayList<>(count);
        List<VmInstance> vmInstanceList = new ArrayList<>(count);
        int endCount = 1000 * 1000;
        Date dateEnd = DateUtil.getDateFromNow(Calendar.MINUTE, -endCount);

        for (int i = 1; i < count; i++) {
            Date date = DateUtil.getDateFrom(dateEnd, Calendar.MINUTE, -i);
            Instances instances = new Instances()
                    .setInstanceId(idBase + i).setCreateDate(date)
                    .setServiceType(vmInstanceServiceType)
                    .setInstanceStatus(instanceStatus);
            if (i % 2 == 0) {
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
                    .setMemory(Random.getRandom(8, 16));
            vmInstanceList.add(vmInstance);
        }
        Date startDate = new Date();
        instanceRepository.save(instancesList);
        vmInstanceRepository.save(vmInstanceList);
        return (new Date().getTime() - startDate.getTime()) / 1000 / 60;
    }


}
