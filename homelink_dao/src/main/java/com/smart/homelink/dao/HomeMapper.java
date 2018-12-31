package com.smart.homelink.dao;

import com.smart.homelink.model.Home;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HomeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home
     *
     * @mbg.generated
     */
    @Delete({
        "delete from home",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home
     *
     * @mbg.generated
     */
    @Insert({
        "insert into home (id, home_name, ",
        "belong_user_id, status, ",
        "create_time)",
        "values (#{id,jdbcType=BIGINT}, #{homeName,jdbcType=VARCHAR}, ",
        "#{belongUserId,jdbcType=BIGINT}, #{status,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Home record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home
     *
     * @mbg.generated
     */
    @InsertProvider(type=HomeSqlProvider.class, method="insertSelective")
    int insertSelective(Home record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, home_name, belong_user_id, status, create_time",
        "from home",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="home_name", property="homeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="belong_user_id", property="belongUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Home selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home
     *
     * @mbg.generated
     */
    @UpdateProvider(type=HomeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Home record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home
     *
     * @mbg.generated
     */
    @Update({
        "update home",
        "set home_name = #{homeName,jdbcType=VARCHAR},",
          "belong_user_id = #{belongUserId,jdbcType=BIGINT},",
          "status = #{status,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Home record);
}