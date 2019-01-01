package com.smart.homelink.cmd;

/**
 * Created by liuhan on 2017-12-23.
 */
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 处理音频转化音频命令
 */
public class MplayerCommand implements ICommand {
    private static Logger logg = LoggerFactory.getLogger(MplayerCommand.class);
    private Map result = new HashMap();

    private String payFile ;

    public MplayerCommand(String payFile) {
        this.payFile  = payFile;
    }


    @Override
    public List<String> getCommand() {
        List<String> commend = new ArrayList<>();
        commend.add("/usr/bin/mplayer");
        commend.add(this.payFile);
        return commend;
    }

    /**
     * 处理字符串
     *
     * @param str
     */
    @Override
    public void dealReturn(String str) {
        if (StringUtils.isEmpty(str)) {
            return ;
        }
    }
}

