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
 * 唤醒电脑
 */
public class WakeComputerCommand implements ICommand {
    private static Logger logg = LoggerFactory.getLogger(WakeComputerCommand.class);
    private Map result = new HashMap();

    private String computerIp ;

    public WakeComputerCommand(String computerIp) {
        this.computerIp  = computerIp;
    }


    @Override
    public List<String> getCommand() {
        List<String> commend = new ArrayList<>();
        commend.add("/bin/ping");
        commend.add(this.computerIp);
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

