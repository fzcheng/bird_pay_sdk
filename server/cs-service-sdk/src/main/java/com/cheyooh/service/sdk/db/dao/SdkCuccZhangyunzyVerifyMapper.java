package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkCuccZhangyunzyVerify;
import com.cheyooh.service.sdk.db.entity.SdkCuccZhangyunzyVerifyExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkCuccZhangyunzyVerifyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int countByExample(SdkCuccZhangyunzyVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int deleteByExample(SdkCuccZhangyunzyVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int insert(SdkCuccZhangyunzyVerify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int insertSelective(SdkCuccZhangyunzyVerify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    List<SdkCuccZhangyunzyVerify> selectByExample(SdkCuccZhangyunzyVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    Pagination<SdkCuccZhangyunzyVerify> selectByExample(SdkCuccZhangyunzyVerifyExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    SdkCuccZhangyunzyVerify selectOne(SdkCuccZhangyunzyVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    SdkCuccZhangyunzyVerify selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkCuccZhangyunzyVerify record, @Param("example") SdkCuccZhangyunzyVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkCuccZhangyunzyVerify record, @Param("example") SdkCuccZhangyunzyVerifyExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkCuccZhangyunzyVerify record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cucc_zhangyunzy_verify
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkCuccZhangyunzyVerify record);
}