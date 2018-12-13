package com.smart.homelink.model;

import java.math.BigDecimal;
import java.util.Date;

public class AirSensor {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column air_sensor.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column air_sensor.temp
     *
     * @mbg.generated
     */
    private BigDecimal temp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column air_sensor.humidity
     *
     * @mbg.generated
     */
    private BigDecimal humidity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column air_sensor.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column air_sensor.altitude
     *
     * @mbg.generated
     */
    private BigDecimal altitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column air_sensor.qnh
     *
     * @mbg.generated
     */
    private BigDecimal qnh;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column air_sensor.id
     *
     * @return the value of air_sensor.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column air_sensor.id
     *
     * @param id the value for air_sensor.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column air_sensor.temp
     *
     * @return the value of air_sensor.temp
     *
     * @mbg.generated
     */
    public BigDecimal getTemp() {
        return temp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column air_sensor.temp
     *
     * @param temp the value for air_sensor.temp
     *
     * @mbg.generated
     */
    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column air_sensor.humidity
     *
     * @return the value of air_sensor.humidity
     *
     * @mbg.generated
     */
    public BigDecimal getHumidity() {
        return humidity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column air_sensor.humidity
     *
     * @param humidity the value for air_sensor.humidity
     *
     * @mbg.generated
     */
    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column air_sensor.create_time
     *
     * @return the value of air_sensor.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column air_sensor.create_time
     *
     * @param createTime the value for air_sensor.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column air_sensor.altitude
     *
     * @return the value of air_sensor.altitude
     *
     * @mbg.generated
     */
    public BigDecimal getAltitude() {
        return altitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column air_sensor.altitude
     *
     * @param altitude the value for air_sensor.altitude
     *
     * @mbg.generated
     */
    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column air_sensor.qnh
     *
     * @return the value of air_sensor.qnh
     *
     * @mbg.generated
     */
    public BigDecimal getQnh() {
        return qnh;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column air_sensor.qnh
     *
     * @param qnh the value for air_sensor.qnh
     *
     * @mbg.generated
     */
    public void setQnh(BigDecimal qnh) {
        this.qnh = qnh;
    }
}