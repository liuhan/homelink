package com.smart.homelink.controller;

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

}
