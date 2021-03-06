package com.smart.homelink.controller;

import com.smart.homelink.config.ControllerMQTTConfig;
import com.smart.homelink.dto.Result;
import com.smart.homelink.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 */
@RestController
@RequestMapping("/play")
public class PlayerController {
    @Autowired
    public PlayerService playerService;
    @GetMapping(value = "/text")
    public Result data(String text) throws Exception {
        playerService.play(text);
        return Result.success("");
    }
    @GetMapping(value = "/nowTemp")
    public Result nowTemp() throws Exception {
        if (ControllerMQTTConfig.temp != null) {
            playerService.play("你家目前温度为：" + ControllerMQTTConfig.temp.intValue() + "摄氏度");
        } else {
            playerService.play("温度正在探测中，请稍后询问！");
        }
        return Result.success("");
    }
}
