package com.smart.homelink.dao;

import com.smart.homelink.model.SmartComponentType;
import org.apache.ibatis.jdbc.SQL;

public class SmartComponentTypeSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table smart_component_type
     *
     * @mbg.generated
     */
    public String insertSelective(SmartComponentType record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("smart_component_type");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getTypeName() != null) {
            sql.VALUES("type_name", "#{typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsControllerCenter() != null) {
            sql.VALUES("is_controller_center", "#{isControllerCenter,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table smart_component_type
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(SmartComponentType record) {
        SQL sql = new SQL();
        sql.UPDATE("smart_component_type");
        
        if (record.getTypeName() != null) {
            sql.SET("type_name = #{typeName,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIsControllerCenter() != null) {
            sql.SET("is_controller_center = #{isControllerCenter,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}