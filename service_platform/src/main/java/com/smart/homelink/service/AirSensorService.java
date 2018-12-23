package com.smart.homelink.service;

import com.smart.homelink.model.AirSensor;

import java.util.List;

public interface AirSensorService {
    public void save(AirSensor airSensor);

    public List<AirSensor> getLastData(int len);
}
