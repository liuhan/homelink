package com.smart.homelink.dto;


import java.io.Serializable;

/**
 * Author : syl
 * datetime ：2018/4/10.
 */
public class CloudCabinetVo extends TerminalConfig implements Serializable {

    private Long fridgeId;//冰柜Id
    private Long goodsTableId;//

    /**
     * CPU序列号
     *
     * field:cpuSn  column:cpu_sn
     *
     */
    private String cpuSn;

    /**
     * MAC地址
     *
     * field:macAddress  column:mac_address
     *
     */
    private String macAddress;



    /**
     * 状态；1-有效；2-禁用；3-无效(删除)
     *
     * field:status  column:status
     *
     */
    private Short status;


    /**
     * 云柜名称
     *
     * field:name  column:name
     *
     */
    private String name;

    /**
     * 1-在线 2-离线
     *
     * field:onlineStatus  column:online_status
     * default:2
     *
     */
    private Short onlineStatus;
    private String onlineUuid;//
    private String remark;//备注
    private String qrcodeAddress;//二维码地址
    private String xcxQrcodeAddress;//小程序二维码
    private Long userId;// 登录人，员工扫码开门字段
    private  String machineCode;//手机机器码 ，员工扫码开门字段
    private long weightWarningErrorRange;//重量警告容错范围g
    private long weightLayerErrorRange;//重量每层容错范围g
    public Long getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(Long fridgeId) {
        this.fridgeId = fridgeId;
    }

    public Long getGoodsTableId() {
        return goodsTableId;
    }

    public void setGoodsTableId(Long goodsTableId) {
        this.goodsTableId = goodsTableId;
    }

    public String getCpuSn() {
        return cpuSn;
    }

    public void setCpuSn(String cpuSn) {
        this.cpuSn = cpuSn;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQrcodeAddress() {
        return qrcodeAddress;
    }

    public void setQrcodeAddress(String qrcodeAddress) {
        this.qrcodeAddress = qrcodeAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Short onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getOnlineUuid() {
        return onlineUuid;
    }

    public void setOnlineUuid(String onlineUuid) {
        this.onlineUuid = onlineUuid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public long getWeightWarningErrorRange() {
        return weightWarningErrorRange;
    }

    public void setWeightWarningErrorRange(long weightWarningErrorRange) {
        this.weightWarningErrorRange = weightWarningErrorRange;
    }

    public long getWeightLayerErrorRange() {
        return weightLayerErrorRange;
    }

    public void setWeightLayerErrorRange(long weightLayerErrorRange) {
        this.weightLayerErrorRange = weightLayerErrorRange;
    }

    public String getXcxQrcodeAddress() {
        return xcxQrcodeAddress;
    }

    public void setXcxQrcodeAddress(String xcxQrcodeAddress) {
        this.xcxQrcodeAddress = xcxQrcodeAddress;
    }
}
