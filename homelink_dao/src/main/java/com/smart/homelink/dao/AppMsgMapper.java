package com.smart.homelink.dao;

import com.smart.homelink.model.AppMsg;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AppMsgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_msg
     *
     * @mbg.generated
     */
    @Delete({
        "delete from app_msg",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_msg
     *
     * @mbg.generated
     */
    @Insert({
        "insert into app_msg (ID, APP_ID, ",
        "TYPE, TABLE_ID, CONTENT, ",
        "STATUS, CREATE_TIME, ",
        "READ_TIME, C_ACT, ",
        "COURSE_STATUS, TEACHER_ID, ",
        "IS_SERIES_COURSE, SERIES_COURSE_ID, ",
        "ROOM_ID, IS_UPDATE, ",
        "PUSH_URL)",
        "values (#{id,jdbcType=BIGINT}, #{appId,jdbcType=BIGINT}, ",
        "#{type,jdbcType=INTEGER}, #{tableId,jdbcType=BIGINT}, #{content,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{readTime,jdbcType=TIMESTAMP}, #{cAct,jdbcType=VARCHAR}, ",
        "#{courseStatus,jdbcType=VARCHAR}, #{teacherId,jdbcType=BIGINT}, ",
        "#{isSeriesCourse,jdbcType=VARCHAR}, #{seriesCourseId,jdbcType=BIGINT}, ",
        "#{roomId,jdbcType=BIGINT}, #{isUpdate,jdbcType=VARCHAR}, ",
        "#{pushUrl,jdbcType=VARCHAR})"
    })
    int insert(AppMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_msg
     *
     * @mbg.generated
     */
    @InsertProvider(type=AppMsgSqlProvider.class, method="insertSelective")
    int insertSelective(AppMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_msg
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, APP_ID, TYPE, TABLE_ID, CONTENT, STATUS, CREATE_TIME, READ_TIME, C_ACT, ",
        "COURSE_STATUS, TEACHER_ID, IS_SERIES_COURSE, SERIES_COURSE_ID, ROOM_ID, IS_UPDATE, ",
        "PUSH_URL",
        "from app_msg",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="APP_ID", property="appId", jdbcType=JdbcType.BIGINT),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TABLE_ID", property="tableId", jdbcType=JdbcType.BIGINT),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.BIGINT),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="READ_TIME", property="readTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="C_ACT", property="cAct", jdbcType=JdbcType.VARCHAR),
        @Result(column="COURSE_STATUS", property="courseStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEACHER_ID", property="teacherId", jdbcType=JdbcType.BIGINT),
        @Result(column="IS_SERIES_COURSE", property="isSeriesCourse", jdbcType=JdbcType.VARCHAR),
        @Result(column="SERIES_COURSE_ID", property="seriesCourseId", jdbcType=JdbcType.BIGINT),
        @Result(column="ROOM_ID", property="roomId", jdbcType=JdbcType.BIGINT),
        @Result(column="IS_UPDATE", property="isUpdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUSH_URL", property="pushUrl", jdbcType=JdbcType.VARCHAR)
    })
    AppMsg selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_msg
     *
     * @mbg.generated
     */
    @UpdateProvider(type=AppMsgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AppMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_msg
     *
     * @mbg.generated
     */
    @Update({
        "update app_msg",
        "set APP_ID = #{appId,jdbcType=BIGINT},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "TABLE_ID = #{tableId,jdbcType=BIGINT},",
          "CONTENT = #{content,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=BIGINT},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "READ_TIME = #{readTime,jdbcType=TIMESTAMP},",
          "C_ACT = #{cAct,jdbcType=VARCHAR},",
          "COURSE_STATUS = #{courseStatus,jdbcType=VARCHAR},",
          "TEACHER_ID = #{teacherId,jdbcType=BIGINT},",
          "IS_SERIES_COURSE = #{isSeriesCourse,jdbcType=VARCHAR},",
          "SERIES_COURSE_ID = #{seriesCourseId,jdbcType=BIGINT},",
          "ROOM_ID = #{roomId,jdbcType=BIGINT},",
          "IS_UPDATE = #{isUpdate,jdbcType=VARCHAR},",
          "PUSH_URL = #{pushUrl,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AppMsg record);
}