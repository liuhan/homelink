package com.smart.util.redis;

/**
 * Created by syl on 2017/12/20.
 */
public class RedisKey {

    /**
     * 用户信息
     */
    public final static String HASH_CONSOLE_USER_LOGIN_PREFIX = "string:console:user:login:";//console 登录用户
    public final static String HASH_CONSOLE_APP_USER_LOGIN_PREFIX = "string:console:app:user:login:";//console app登录用户
    public final static String ZSET_CONSOLE_USER_LOGIN_MENUS = "zset:console:user:login:meuns:";//console登陆用户菜单列表 改成zset
    public final static int  CONSOLE_USER_LOGIN_VALID_TIME = 3 * 24 * 60 * 60;//登录用户有效时间
    public final static int  APP_EMPLOYEE_USER_LOGIN_VALID_TIME = 30 * 24 * 60 * 60;//App员工登录用户有效时间(小时)
    //用户信息
    public final static String STRING_CONSOLE_USER_INFO = "string:console:user:";//key 前缀 +  ID，value ：对象json
    public final static int  STRING_CONSOLE_USER_INFO_VALID_TIME  = 1 * 60 * 60;//用户基本信息缓存时间

    public final static String HASH_WECHAT_USER_LOGIN_PREFIX = "hash:wechat:user:login:";// 微信系统登录用户
    public final static String HASH_ALIPAY_USER_LOGIN_PREFIX = "hash:alipay:user:login:";// 支付宝系统登录用户
    public final static String HASH_WECHAT_USER_WMW_LOGIN_PREFIX = "string:wechat:user:wmw:login:";// 微信我买网系统登录用户


    public final static int  WECHAT_USER_LOGIN_VALID_TIME = 2 * 60 * 60;//登录用户有效时间

    public final static String HASH_APP_USER_LOGIN_PREFIX = "string:app:user:login:";//移动端登录用户


    /**
     * 微信公众号
     */
    public final static int WECHAT_ACCESS_TOKEN_USE_TIME =  7000 ;//微信accessToken有效时间，微信为7200秒
    public final static String STRING_WECHAT_ACCESS_TOKEN_PRE = "string:wechat:access:token:";//多个授权公众号前缀
    //public final static String STRING_WECHAT_APPSECRET_PRE = "string:wechat:appsecret:pre:";//多个授权公众号前缀  APPSECRET
    public final static String STRING_WECHAT_JSAPI_TICKET = "string:wechat:jsapi:ticket";//js票据


    /**
     * 微信第三方平台
     */
    public final static String STRING_THIRD_WECHAT_COMPONENT_VERIFY_TICKET = "string:third:wechat:component:verify:ticket";//verify票据
    public final static String STRING_THIRD_WECHAT_COMPONENT_ACCESS_TOKEN = "string:third:wechat:component:access:token";//第三方公众号token

    public final static String HASH_THIRD_WECHAT_TEMPLATEMSG = "hash:third:wechat:templatemsg:";//公众号消息模板

    /*用户信息*/

    //app用户存入redis opendid  map散列 key--> openid value-->id
    public final static String HASH_WECHAT_OPENID_APP_USER_ID = "hash:wechat:openid:app_user:Id"; //thirdUser  第三方用户存入redis   map散列 key--> openid value-->appUser id
    public final static String STRING_APP_USER_INFO_PRE = "string:app:user:info:pre:";// AppUser 用户新 key id  keys字段 value值,已改成string
    public final static int APP_USER_INFO_VALID_TIME = 12 * 60 * 60;//appUser缓存有效期

    //云柜设备cpu,判断是否存在 key -设备 cpusn value - terminalNo + qrAddress + fridgeId
    public final static String HASH_CLOUD_CABINET_TERMINAL_CPU = "hash:cloudCabinet:terminalCpu";
    //冰箱状态信息 key terminalNo  value  1-启用 2-未启用
    public final static String HASH_CLOUD_CABINET_TERMINAL_STATUS = "hash:cloudCabinet:status";
    //冰箱信息
    public final static String HASH_CLOUD_FRIDGE_INFO = "hash:fridge";//KEY : ID , VALUE {id:1,terminalNo:"dbssssswwsndl223l4nfd00mo"},对象

    //云柜设备用户使用状态 key terminalNo  value  1-使用人ID
    public final static String STRING_CLOUD_CABINET_TERMINAL_USE_STATUS = "string:cloudCabinet:useStatus:";
    public final static String STRING_CLOUD_CABINET_APPUSER_USE_STATUS = "string:cloudCabinet:appuser:useStatus:";
    public final static int CLOUD_CABINET_TERMINAL_USE_STATUS_VALID_TIME = 2 * 60;//柜设备用户使用状态缓存有效期

    //设备APK更新版本
    public final static String STRING_TERMINAL_UPGRADE_APK = "string:terminal:upgradeApk";
    //云柜设备开门记录
    public final static String HASH_TERMINAL_OPENDOOR_RECORD = "hash:terminal:opendoor";

    //系统参数散列
    public final static String HASH_SYSTEM_PARAM = "hash_system_param";

    public final static String STRING_NONCE_PRE = "string:nonce:pre:";//参数签名nonce存储

    //用户支付状态
    public final static String HASH_USER_ORDER_PAY_STATUS = "hash:user:orderpaystatus:"; //每个用户一个  status 0-开门 1-已关门 2-支付成功 3-支付失败  orderNo 订单支付串
    public final static  int USER_ORDER_PAY_STATUS_VALID_TIME = 2 * 60;

    /**
     * 城市编码
     */
    public final static String LIST_CONSOLE_CITY_INFO = "list:console:cityInfo";//城市编码
    public final static int LIST_CONSOLE_CITY_INFO_VALID_TIME = 12 * 60 * 60;//城市编码有效时间(秒)

    //云仓对象 VO
    public final static String HASH_WAREHOUSE_RECORD = "hash:warehouse";

    //合伙人对象
    public final static String HASH_PARTNER_RECORD = "hash:partner";
    public final static String HASH_WAREHOUSE_USER_RECORD = "hash:warehouseusers";


    //IM服务器节点连接的设备客户端记录
    public final static String HASH_IMSERVER_TERMINAL_NODE = "hash:imserver:terminalNo";//key 设备编号 value 记录对象
    //记录数量，可做负载用的
    public final static String INT_IM_SERVER_NODE_COUNT_PRE = "int:imserver:NodeCountPre";
    public final static int  INT_IM_SERVER_NODE_VALID_TIME = 5 * 24 * 60 * 60;//有效时间


    public final static String STRING_REGISTER_CODE = "string:register:code:";//注册短信验证码
    public final static String STRING_LOGIN_CODE = "string:login:code:";//登录短信验证码
    public final static String STRING_UPDATE_CODE = "string:update:code:";//更换手机号短信验证码

    public static final String STRING_ACCOUNTORDER_CALLBACK_DOING_PRE = "string:accountorder:callback:doing:" ;//该账户订单回调处理进行中，防止重复进入
    public static final int STRING_ACCOUNTORDER_CALLBACK_DOING_EXP = 5 * 60 ;//账户订单回调处理中（5分钟过期时间，防止重试）
    public static final String HASH_JPUSH_CODE_KEY = "hash:jpush:user:code" ;//存储用户和设备号的对应关系
    public static final String STRING_APP_VERSION_INFO_PRE = "string:app:version:info:";//APP版本信息缓存 android和ios

    public static final String HASH_THIRD_MERCHANT = "hash:third:merchant:";  //商户号类型
    public static final String HASH_USER_ACCOUNT = "hash:user:account:";  //用户类型
    public static final String HASH_WECHAT_OFFICIAL = "hash:wechat:official:";  //公众号类型
    public static final String HASH_USERACCOUNT_APPID = "hash:userAccount:appid:";  //帐套服务类型

    public static final String STRING_PROGRAM_PLAY = "string:program:play"; //广告播放记录

    public static final String SET_APP_USER_OLD = "set:app:user:old"; //所有的老用户的mobile

    public static final String SET_OBJECT_COUNT_ORDER_NUMBER = "set:object:count:order:number:";//云柜订单数
    public static final String SORT_OBJECT_COUNT_ORDER_AMOUNT = "sort:object:count:order:amount:";//云柜订单金额
    public static final String SORT_OBJECT_COUNT_ORDER_FIDGE_GOOD = "sort:object:count:order:fidge:good:";//单个云柜商品数量
    public static final String SORT_OBJECT_COUNT_ORDER_PARTNER_GOOD = "sort:object:count:order:partner:good:";//所有云柜的商品
    public static final String SET_OBJECT_COUNT_ORDER_APP_USER = "set:object:count:app:user:";//云柜会员数
    public static final String STRING_OBJECT_COUNT_ORDER_APP_USER = "string:object:count:app:user:";//云柜商品标记

    //商圈id信息
    public final static String HASH_CONSOLE_CITY_BUSINESS_AREA_ID = "hash:console:city:businessAreaId:";//key 前缀 +  ID，value ：对象json

    public static final String STRING_ORDER_NOSECRETPAYING = "string:order:nosecretpaying:";//key 前缀 +  ID，value ：1 免密支付中标志

    public final static  int ORDER_NOSECRETPAYING_VALID_TIME =  60;//免密支付中标志过期时间
    public static final String STRING_USER_FRIDGE_OPEN_STATUS = "STRING:USER:FRIDGE:OPEN:STATUS:"; //我买网，打开柜门状态。用户：柜子
}
