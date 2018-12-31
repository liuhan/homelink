package com.smart.homelink.service.impl;

import com.smart.homelink.dao.AirSensorRecordMapper;
import com.smart.homelink.model.AirSensorRecord;
import com.smart.homelink.service.AirSensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("airSensorRecordService")
public class AirSensorRecordServiceImpl implements AirSensorRecordService {

    @Autowired
    AirSensorRecordMapper airSensorRecordMapper;


    @Override
    public List<AirSensorRecord> getLastData(int len) {
        List<AirSensorRecord> list = airSensorRecordMapper.getLastData(len);
        return list;
    }

}
