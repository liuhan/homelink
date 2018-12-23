package com.smart.homelink.service.impl;

import com.smart.homelink.dao.AirSensorMapper;
import com.smart.homelink.model.AirSensor;
import com.smart.homelink.service.AirSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("airSensorService")
public class AirSensorServiceImpl implements AirSensorService {

    @Autowired
    AirSensorMapper airSensorMapper;

    @Override
    public void save(AirSensor airSensor) {
        airSensorMapper.insert(airSensor);
    }

    @Override
    public List<AirSensor> getLastData(int len) {
        System.out.println("bb");
        List<AirSensor> list = airSensorMapper.getLastData(len);
        return list;
    }

}
