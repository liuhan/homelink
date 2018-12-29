package smart.homelink.service.impl;

import com.smart.homelink.dao.AirSensorMapper;
import com.smart.homelink.model.AirSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.homelink.service.AirSensorService;

import java.util.List;


@Service("airSensorService")
public class AirSensorServiceImpl implements AirSensorService {

    @Autowired
    AirSensorMapper airSensorMapper;

    @Override
    public void save(AirSensor airSensor) {
        airSensorMapper.insert(airSensor);
    }


}
