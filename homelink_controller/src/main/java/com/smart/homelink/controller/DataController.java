package com.smart.homelink.controller;

import com.smart.homelink.config.ControllerMQTTConfig;
import com.smart.homelink.dto.Result;
import com.smart.homelink.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @GetMapping(value = "/nowTemp")
    public Result nowTemp() throws Exception {
        if (ControllerMQTTConfig.temp != null) {
            return Result.success("你家目前温度为：" + ControllerMQTTConfig.temp.intValue() + "摄氏度");
        } else {
            return Result.success("温度正在探测中，请稍后询问！");
        }
    }
}
