package com.smart.homelink.dao;

import com.smart.homelink.model.TTest2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TTest2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test2
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_test2",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test2
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_test2 (id, name)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
    })
    int insert(TTest2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test2
     *
     * @mbg.generated
     */
    @InsertProvider(type=TTest2SqlProvider.class, method="insertSelective")
    int insertSelective(TTest2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test2
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name",
        "from t_test2",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    TTest2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test2
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TTest2SqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TTest2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_test2
     *
     * @mbg.generated
     */
    @Update({
        "update t_test2",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TTest2 record);
}