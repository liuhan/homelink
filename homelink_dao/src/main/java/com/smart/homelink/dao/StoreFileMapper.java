package com.smart.homelink.dao;

import com.smart.homelink.model.StoreFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface StoreFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table store_file
     *
     * @mbg.generated
     */
    @Delete({
        "delete from store_file",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table store_file
     *
     * @mbg.generated
     */
    @Insert({
        "insert into store_file (id, name, ",
        "type, md5, is_del, ",
        "url, module, size, ",
        "create_time)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{md5,jdbcType=VARCHAR}, #{isDel,jdbcType=INTEGER}, ",
        "#{url,jdbcType=VARCHAR}, #{module,jdbcType=VARCHAR}, #{size,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=DATE})"
    })
    int insert(StoreFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table store_file
     *
     * @mbg.generated
     */
    @InsertProvider(type=StoreFileSqlProvider.class, method="insertSelective")
    int insertSelective(StoreFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table store_file
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name, type, md5, is_del, url, module, size, create_time",
        "from store_file",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="md5", property="md5", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="module", property="module", jdbcType=JdbcType.VARCHAR),
        @Result(column="size", property="size", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE)
    })
    StoreFile selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table store_file
     *
     * @mbg.generated
     */
    @UpdateProvider(type=StoreFileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(StoreFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table store_file
     *
     * @mbg.generated
     */
    @Update({
        "update store_file",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "md5 = #{md5,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=INTEGER},",
          "url = #{url,jdbcType=VARCHAR},",
          "module = #{module,jdbcType=VARCHAR},",
          "size = #{size,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=DATE}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(StoreFile record);
}