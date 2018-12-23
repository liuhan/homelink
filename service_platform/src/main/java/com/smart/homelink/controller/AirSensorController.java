package com.smart.homelink.controller;

import com.smart.homelink.dto.Result;
import com.smart.homelink.service.AirSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@RestController
@RequestMapping("/airSensor")
public class AirSensorController {

    @Autowired
    AirSensorService airSensorService;

    @GetMapping(value = "/data")
    public Result<List> data() throws Exception {
        System.out.println("aaaa");
        return Result.success(airSensorService.getLastData(20));
    }

}
