package com.smart.homelink.cmd;

import java.util.List;
import java.util.Map;

/**
 * Created by liuhan on 2017-12-23.
 */
public interface ICommand {

    public List<String> getCommand();
    /**
     * 处理字符串
     * @param str
     */

    public void dealReturn(String str);

}
