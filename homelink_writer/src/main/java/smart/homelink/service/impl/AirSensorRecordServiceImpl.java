package smart.homelink.service.impl;

import com.smart.homelink.dao.AirSensorRecordMapper;
import com.smart.homelink.model.AirSensorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.homelink.service.AirSensorRecordService;

import java.util.List;


@Service("airSensorRecordService")
public class AirSensorRecordServiceImpl implements AirSensorRecordService {

    @Autowired
    AirSensorRecordMapper airSensorRecordMapper;

    @Override
    public void save(AirSensorRecord airSensorRecord) {
        airSensorRecordMapper.insert(airSensorRecord);
    }


}
