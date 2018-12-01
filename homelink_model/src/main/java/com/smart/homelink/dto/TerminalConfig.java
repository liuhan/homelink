package com.smart.homelink.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * tablename:terminal_config
 */
public class TerminalConfig implements Serializable {
    /**
     * 设备编号
     *
     * field:terminalNo  column:terminal_no
     * 
     */
    private String terminalNo;

    /**
     * 系统版本
     *
     * field:systemVersion  column:system_version
     * 
     */
    private String systemVersion;

    /**
     * apk版本
     *
     * field:apkVersion  column:apk_version
     * 
     */
    private String apkVersion;

    /**
     * APK应该升级的版本
     *
     * field:apkUpgradeVersion  column:apk_upgrade_version
     * 
     */
    private String apkUpgradeVersion;

    /**
     * 磁盘使用（字节）
     *
     * field:diskUseSize  column:disk_use_size
     * 
     */
    private Long diskUseSize;

    /**
     * 磁盘使用总大小（字节）
     *
     * field:diskSize  column:disk_size
     * 
     */
    private Long diskSize;

    /**
     * IP地址
     *
     * field:ipAddress  column:ip_address
     * 
     */
    private String ipAddress;

    /**
     * 重显率
     *
     * field:redisplayRatio  column:redisplay_ratio
     * default:0
     * 
     */
    private Integer redisplayRatio;

    /**
     * 警告提示音量
     *
     * field:alarmVolume  column:alarm_volume
     * default:0
     * 
     */
    private Integer alarmVolume;

    /**
     * 播放音量
     *
     * field:playVolume  column:play_volume
     * default:0
     * 
     */
    private Integer playVolume;

    /**
     * 亮度
     *
     * field:brightness  column:brightness
     * default:0
     * 
     */
    private Integer brightness;

    /**
     * 最后一次系统升级时间
     *
     * field:lastUpgradeTime  column:last_upgrade_time
     * 
     */
    private Date lastUpgradeTime;

    /**
     * 信号传输模式
     *
     * field:outputMod  column:output_mod
     * 
     */
    private String outputMod;

    /**
     * 显示比例
     *
     * field:displayScale  column:display_scale
     * 
     */
    private String displayScale;

    /**
     * 显示屏水平分辨率
     *
     * field:displayResH  column:display_res_h
     * default:0
     * 
     */
    private Integer displayResH;

    /**
     * 显示屏垂直分辨率
     *
     * field:displayResV  column:display_res_v
     * default:0
     * 
     */
    private Integer displayResV;

    /**
     * 系统密码
     *
     * field:systemPassword  column:system_password
     * 
     */
    private String systemPassword;

    /**
     * 系统重启开机时间
     *
     * field:rebootTime  column:reboot_time
     * default:02:00:00
     * 
     */
    private Date rebootTime;

    /**
     * 系统重启关机时间
     *
     * field:rebootTime  column:reboot_time
     * default:02:00:00
     *
     */
    private Date rebootStopTime;

    /**
     * 创建时间
     *
     * field:createTime  column:create_time
     * 
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * field:updateTime  column:update_time
     * 
     */
    private Date updateTime;

    /**
     * terminal_config
     */
    private static final long serialVersionUID = 1L;

    public Date getRebootStopTime() {
        return rebootStopTime;
    }

    public void setRebootStopTime(Date rebootStopTime) {
        this.rebootStopTime = rebootStopTime;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo == null ? null : terminalNo.trim();
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
    }

    public String getApkVersion() {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion) {
        this.apkVersion = apkVersion == null ? null : apkVersion.trim();
    }

    public String getApkUpgradeVersion() {
        return apkUpgradeVersion;
    }

    public void setApkUpgradeVersion(String apkUpgradeVersion) {
        this.apkUpgradeVersion = apkUpgradeVersion == null ? null : apkUpgradeVersion.trim();
    }

    public Long getDiskUseSize() {
        return diskUseSize;
    }

    public void setDiskUseSize(Long diskUseSize) {
        this.diskUseSize = diskUseSize;
    }

    public Long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(Long diskSize) {
        this.diskSize = diskSize;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public Integer getRedisplayRatio() {
        return redisplayRatio;
    }

    public void setRedisplayRatio(Integer redisplayRatio) {
        this.redisplayRatio = redisplayRatio;
    }

    public Integer getAlarmVolume() {
        return alarmVolume;
    }

    public void setAlarmVolume(Integer alarmVolume) {
        this.alarmVolume = alarmVolume;
    }

    public Integer getPlayVolume() {
        return playVolume;
    }

    public void setPlayVolume(Integer playVolume) {
        this.playVolume = playVolume;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Date getLastUpgradeTime() {
        return lastUpgradeTime;
    }

    public void setLastUpgradeTime(Date lastUpgradeTime) {
        this.lastUpgradeTime = lastUpgradeTime;
    }

    public String getOutputMod() {
        return outputMod;
    }

    public void setOutputMod(String outputMod) {
        this.outputMod = outputMod == null ? null : outputMod.trim();
    }

    public String getDisplayScale() {
        return displayScale;
    }

    public void setDisplayScale(String displayScale) {
        this.displayScale = displayScale == null ? null : displayScale.trim();
    }

    public Integer getDisplayResH() {
        return displayResH;
    }

    public void setDisplayResH(Integer displayResH) {
        this.displayResH = displayResH;
    }

    public Integer getDisplayResV() {
        return displayResV;
    }

    public void setDisplayResV(Integer displayResV) {
        this.displayResV = displayResV;
    }

    public String getSystemPassword() {
        return systemPassword;
    }

    public void setSystemPassword(String systemPassword) {
        this.systemPassword = systemPassword == null ? null : systemPassword.trim();
    }

    public Date getRebootTime() {
        return rebootTime;
    }

    public void setRebootTime(Date rebootTime) {
        this.rebootTime = rebootTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}