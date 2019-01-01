package com.smart.homelink.service.impl;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.smart.homelink.cmd.CmdExecuter;
import com.smart.homelink.cmd.MplayerCommand;
import com.smart.homelink.service.PlayerService;
import com.smart.util.security.MD5;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static Logger logg = LoggerFactory.getLogger(PlayerServiceImpl.class);
    @Value("${speech.file.basePath}")
    private String speechFileBasePath ;

    //设置APPID/AK/SK
    public static final String APP_ID = "15317139";
    public static final String API_KEY = "hRUD2xDdv3c0XQa9V7vYGALa";
    public static final String SECRET_KEY = "vBCc7Q3fmCBIpC5wpgilymF5GizrkGOF";

    @Override
    public void play(String text) {
        String mp3Address = speechFileBasePath + "/" + MD5.getMD5(text) + ".mp3";
        File file = new File(mp3Address);
        //如果已存在
        if (file.exists()) {
            MplayerCommand mc = new MplayerCommand(mp3Address);
            CmdExecuter.exec(mc);
            return;
        }

        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        TtsResponse res = client.synthesis(text, "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();

        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, mp3Address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            logg.info("报错:{}",res1.toString(2));
        } else {
            MplayerCommand mc = new MplayerCommand(mp3Address);
            CmdExecuter.exec(mc);
        }
    }
}
