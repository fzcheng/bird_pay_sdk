package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkCmcc49youVerify;
import com.cheyooh.service.sdk.db.entity.SdkCmcc49youVerifyExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkCmcc49youVerifyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int countByExample(SdkCmcc49youVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int deleteByExample(SdkCmcc49youVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int insert(SdkCmcc49youVerify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int insertSelective(SdkCmcc49youVerify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    List<SdkCmcc49youVerify> selectByExample(SdkCmcc49youVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    Pagination<SdkCmcc49youVerify> selectByExample(SdkCmcc49youVerifyExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    SdkCmcc49youVerify selectOne(SdkCmcc49youVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    SdkCmcc49youVerify selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkCmcc49youVerify record, @Param("example") SdkCmcc49youVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkCmcc49youVerify record, @Param("example") SdkCmcc49youVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkCmcc49youVerify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc49you_verify
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkCmcc49youVerify record);
}