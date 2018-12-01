package com.smart.homelink.netty.util;


/**
 * Created by think on 2018/3/29.
 */
public class NettyIMMessageType {

    //"云柜注册"
    public final static int  CLOUD_ABINET_CLIENT_REGISTE = 1000;
    //"云柜注册成功"
    public final static int  CLOUD_ABINET_SERVER_REGISTE_SUCCESS = 1001;
    //"云柜注册失败
    public final static int  CLOUD_ABINET_SERVER_REGISTE_FAIL= 1002;

    //微信--》云柜开门
    public final static int  CLOUD_ABINET_SERVER_OPEN_DOOR = 1010;

    //云柜开门成功 /客户端给我发
    public final static int  CLOUD_ABINET_CLIENT_OPEN_DOOR_SUCCESS = 1011;

    //云柜用户拉开开门
    public final static int  CLOUD_ABINET_CLIENT_USER_OPEN_DOOR = 1012;

    //APP --> 云柜开门
    public final static int  CLOUD_ABINET_SERVER_APP_OPEN_DOOR = 1013;

    //云柜APK升级更新
    public final static int  CLOUD_ABINET_SERVER_AKP_UPDATE = 1020 ;
    //云柜开关机（时间）
    public final static int  CLOUD_ABINET_SERVER_ON_OFF = 1021;
    //云柜立即重启
    public final static int  CLOUD_ABINET_SERVER_REBOT = 1022 ;

    //云柜更新密码
    public final static int  CLOUD_ABINET_SERVER_UPDATE_PASSWORD = 1023 ;

    //云柜警告提示音量广告播放音量以及云柜屏幕亮度调整
    public final static int  CLOUD_ABINET_SERVER_ALARMANDADVERTAND_VOLUME_SCREEN_BRIGHTNESS = 1030;
    //云柜广告播放音量调整
/*    public final static int  CLOUD_ABINET_SERVER_ADVERT_VOLUME = 1031;
    //云柜屏幕亮度调整
    public final static int  CLOUD_ABINET_SERVER_SCREEN_BRIGHTNESS = 1032;*/

    //云柜更新二维码
    public final static int  CLOUD_ABINET_SERVER_UPDATE_QRCODE = 1035;

    //云柜广告更新
    public final static int  CLOUD_ABINET_SERVER_ADVERT = 1040;
    //云柜广告播放记录回传
    public final static int  CLOUD_ABINET_CLIENT_ADVERT_PLAY = 1041;

    //商品表更新
    public final static int  CLOUD_ABINET_SERVER_UPDATE_GOODS_TABLE = 1050;

    //云柜设备重量警告容错范围g
    public final static int  CLOUD_ABINET_SERVER_UPDATE_WEIGHT_WARNING_ERROR_RANGE = 1060;

    // 云柜设备重量每层容错范围g
    public final static int  CLOUD_ABINET_SERVER_UPDATE_WEIGHT_LAYER_ERROR_RANGE = 1061;

    //释放云柜设备
    public final static int  CLOUD_ABINET_CLIENT_RELEASE_TERMINAL = 1100;

    //云柜设备计重模块异常
    public final static int  CLOUD_ABINET_CLIENT_WEIGHT_EXCEPTION = 8000;

    //客户端服务器发送心跳
    public final static int  CLOUD_ABINET_CLENT_SERVER_HEART = 9998 ;
    //第一次连接发送
    public final static int  CLOUD_ABINET_SERVER_ONLINE = 9999;


    /**
     * 系统应用客户端
     */

    //注册
    public final static int SYS_APPLICATION_CLIENT_REGISTER = 5000;
    //服务器返回注册
    public final static int SYS_APPLICATION_SERVER_RETURN_REGISTER = 5001;
    //系统发送开门指令
    public final static int SYS_APPLICATION_CLIENT_OPEN_DOOR= 5002;

    //系统发送支付状态
    public final static int SYS_APPLICATION_CLIENT_ORDER_PAY_STATUS= 5010;//发送给用户客户端（websocket）

    //云柜给sys系统发送拿走的商品明细 时时
    public final static int SYS_CLOUD_ABINET_CLIENT_GOODS_DETAIL = 5100;//extData扩展字段 map明细 id，name，price，count ，layer（1/2/3/4/5），status(1-加 2-减)

    /**
     * 后台发送
     */
    public final static int CONSOLE_APPLICATION_SERVER_ON_OFF_DOOR_WEIGHT_VOLUME = 6000;//console 后台发送获取开关门状态 + 重量 + 音量
    public final static int CONSOLE_APPLICATION_CLOUD_ABINET_CLIENT_ON_OFF_DOOR_WEIGHT_VOLUME = 6001;//云柜客户端发送柜子开关门状态 0-关门 1-开门 + 重量 + 音量
    public final static int CONSOLE_APPLICATION_SERVER_GET_NO_PAY_ORDER= 6002;//console 后台发送获取是否存在未处理订单
    public final static int CONSOLE_APPLICATION_CLOUD_ABINET_CLIENT_SEND_NO_PAY_ORDER = 6003;//云柜客户端发送未处理的订单，包括订单信息
    public final static int CONSOLE_APPLICATION_SERVER_GET_GOODS_TABLE_TYPE= 6004;//console 后台发送获取商品表类型
    public final static int CONSOLE_APPLICATION_CLOUD_ABINET_CLIENT_GET_GOODS_TABLE_TYPE = 6005;//云柜客户端发送商品表类型




}
