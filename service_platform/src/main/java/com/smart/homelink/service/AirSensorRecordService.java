package com.smart.homelink.service;

import com.smart.homelink.model.AirSensorRecord;
import com.smart.homelink.model.AirSensorRecord;

import java.util.List;

public interface AirSensorRecordService {

    public List<AirSensorRecord> getLastData(int len);
}
