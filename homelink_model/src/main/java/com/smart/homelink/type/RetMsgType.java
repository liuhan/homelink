package com.smart.homelink.type;

public enum RetMsgType {
    SUCCESS("0","success"),
    SIGN_ERROR("888888","签名失败",null),
    //IM HTTP 异常错误 开门系统
    IM_HTTP_REQUEST_ERROR("570000" , "IM HTTP 异常错误",null),
    IM_HTTP_REQUEST_OFF_LINE("570001" , "IM云柜设备已离线，请稍后重试！",null),
    IM_HTTP_REQUEST_OPENDOOR_ERROR("570002" , "开门错误",null),
    IM_HTTP_REQUEST_APP_OPENDOOR_ERROR("570003" , "APP员工开门失败",null),
    IM_HTTP_REQUEST_UPDATE_GOODS_TABLE_ERROR("570004" , "更新云柜商品表信息错误",null),
    IM_HTTP_REQUEST_UPDATE_ALARMANDADVERTAND_VOLUME_ERROR("570005" , "更新音量亮度错误",null),
    IM_HTTP_REQUEST_REBOT_ERROR("570006" , "重启失败",null),
    IM_HTTP_REQUEST_ON_OFF_UPDATE_ERROR("570007" , "APK更新开关机时间失败",null),
    IM_HTTP_REQUEST_PASSWORD_UPDATE_ERROR("570008" , "APK更新密码错误",null),
    IM_HTTP_REQUEST_AKP_UPDATE_ERROR("570009" , "APK升级失败",null),
    IM_HTTP_REQUEST_CLOUD_CABINET_TRADING("570010" , "云柜交易中，请稍后",null);

    private String code;
    private String message;
    private Object data;//扩展数据

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static RetMsgType get(String code) {
        RetMsgType[] vals = RetMsgType.values();
        for (RetMsgType lt : vals) {
            if (code.equals(lt.getCode())) {
                return lt;
            }
        }
        return null;
    }

    private RetMsgType(String code, String message , Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    private RetMsgType(String code, String message ) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}
