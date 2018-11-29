package com.smart.util.netty;


/**
 * Created by think on 2018/7/2.
 * websocket 消息类型
 */
public class NettyIMWebsocketMessageType {
    //sys 用户订单支付类型
    public final static int SYS_USER_ORDER_PAY = 1000;//

    //云柜给sys系统发送拿走的商品明细 时时
    public final static int SYS_CLOUD_ABINET_CLIENT_GOODS_DETAIL = 5100;//data  map明细 id，name，price，count ，layer（1/2/3/4/5），status(1-加 2-减)
}
