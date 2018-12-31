package com.smart.homelink.controller;

import com.smart.homelink.dto.Result;
import com.smart.homelink.service.AirSensorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@RestController
@RequestMapping("/airSensorRecord")
public class AirSensorRecordController {

    @Autowired
    AirSensorRecordService airSensorRecordService;

    @GetMapping(value = "/data")
    public Result<List> data() throws Exception {
        return Result.success(airSensorRecordService.getLastData(20));
    }

}
