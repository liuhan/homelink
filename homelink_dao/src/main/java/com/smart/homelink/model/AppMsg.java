package com.smart.homelink.model;

import java.util.Date;

public class AppMsg {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.ID
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.APP_ID
     *
     * @mbg.generated
     */
    private Long appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.TYPE
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.TABLE_ID
     *
     * @mbg.generated
     */
    private Long tableId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.CONTENT
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.STATUS
     *
     * @mbg.generated
     */
    private Long status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.READ_TIME
     *
     * @mbg.generated
     */
    private Date readTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.C_ACT
     *
     * @mbg.generated
     */
    private String cAct;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.COURSE_STATUS
     *
     * @mbg.generated
     */
    private String courseStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.TEACHER_ID
     *
     * @mbg.generated
     */
    private Long teacherId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.IS_SERIES_COURSE
     *
     * @mbg.generated
     */
    private String isSeriesCourse;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.SERIES_COURSE_ID
     *
     * @mbg.generated
     */
    private Long seriesCourseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.ROOM_ID
     *
     * @mbg.generated
     */
    private Long roomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.IS_UPDATE
     *
     * @mbg.generated
     */
    private String isUpdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_msg.PUSH_URL
     *
     * @mbg.generated
     */
    private String pushUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.ID
     *
     * @return the value of app_msg.ID
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.ID
     *
     * @param id the value for app_msg.ID
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.APP_ID
     *
     * @return the value of app_msg.APP_ID
     *
     * @mbg.generated
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.APP_ID
     *
     * @param appId the value for app_msg.APP_ID
     *
     * @mbg.generated
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.TYPE
     *
     * @return the value of app_msg.TYPE
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.TYPE
     *
     * @param type the value for app_msg.TYPE
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.TABLE_ID
     *
     * @return the value of app_msg.TABLE_ID
     *
     * @mbg.generated
     */
    public Long getTableId() {
        return tableId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.TABLE_ID
     *
     * @param tableId the value for app_msg.TABLE_ID
     *
     * @mbg.generated
     */
    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.CONTENT
     *
     * @return the value of app_msg.CONTENT
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.CONTENT
     *
     * @param content the value for app_msg.CONTENT
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.STATUS
     *
     * @return the value of app_msg.STATUS
     *
     * @mbg.generated
     */
    public Long getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.STATUS
     *
     * @param status the value for app_msg.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.CREATE_TIME
     *
     * @return the value of app_msg.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.CREATE_TIME
     *
     * @param createTime the value for app_msg.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.READ_TIME
     *
     * @return the value of app_msg.READ_TIME
     *
     * @mbg.generated
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.READ_TIME
     *
     * @param readTime the value for app_msg.READ_TIME
     *
     * @mbg.generated
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.C_ACT
     *
     * @return the value of app_msg.C_ACT
     *
     * @mbg.generated
     */
    public String getcAct() {
        return cAct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.C_ACT
     *
     * @param cAct the value for app_msg.C_ACT
     *
     * @mbg.generated
     */
    public void setcAct(String cAct) {
        this.cAct = cAct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.COURSE_STATUS
     *
     * @return the value of app_msg.COURSE_STATUS
     *
     * @mbg.generated
     */
    public String getCourseStatus() {
        return courseStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.COURSE_STATUS
     *
     * @param courseStatus the value for app_msg.COURSE_STATUS
     *
     * @mbg.generated
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.TEACHER_ID
     *
     * @return the value of app_msg.TEACHER_ID
     *
     * @mbg.generated
     */
    public Long getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.TEACHER_ID
     *
     * @param teacherId the value for app_msg.TEACHER_ID
     *
     * @mbg.generated
     */
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.IS_SERIES_COURSE
     *
     * @return the value of app_msg.IS_SERIES_COURSE
     *
     * @mbg.generated
     */
    public String getIsSeriesCourse() {
        return isSeriesCourse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.IS_SERIES_COURSE
     *
     * @param isSeriesCourse the value for app_msg.IS_SERIES_COURSE
     *
     * @mbg.generated
     */
    public void setIsSeriesCourse(String isSeriesCourse) {
        this.isSeriesCourse = isSeriesCourse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.SERIES_COURSE_ID
     *
     * @return the value of app_msg.SERIES_COURSE_ID
     *
     * @mbg.generated
     */
    public Long getSeriesCourseId() {
        return seriesCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.SERIES_COURSE_ID
     *
     * @param seriesCourseId the value for app_msg.SERIES_COURSE_ID
     *
     * @mbg.generated
     */
    public void setSeriesCourseId(Long seriesCourseId) {
        this.seriesCourseId = seriesCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.ROOM_ID
     *
     * @return the value of app_msg.ROOM_ID
     *
     * @mbg.generated
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.ROOM_ID
     *
     * @param roomId the value for app_msg.ROOM_ID
     *
     * @mbg.generated
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.IS_UPDATE
     *
     * @return the value of app_msg.IS_UPDATE
     *
     * @mbg.generated
     */
    public String getIsUpdate() {
        return isUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.IS_UPDATE
     *
     * @param isUpdate the value for app_msg.IS_UPDATE
     *
     * @mbg.generated
     */
    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_msg.PUSH_URL
     *
     * @return the value of app_msg.PUSH_URL
     *
     * @mbg.generated
     */
    public String getPushUrl() {
        return pushUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_msg.PUSH_URL
     *
     * @param pushUrl the value for app_msg.PUSH_URL
     *
     * @mbg.generated
     */
    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }
}