package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkUserAuto;
import com.cheyooh.service.sdk.db.entity.SdkUserAutoExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkUserAutoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int countByExample(SdkUserAutoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int deleteByExample(SdkUserAutoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String deviceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int insert(SdkUserAuto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int insertSelective(SdkUserAuto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    List<SdkUserAuto> selectByExample(SdkUserAutoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    Pagination<SdkUserAuto> selectByExample(SdkUserAutoExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    SdkUserAuto selectOne(SdkUserAutoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    SdkUserAuto selectByPrimaryKey(String deviceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkUserAuto record, @Param("example") SdkUserAutoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkUserAuto record, @Param("example") SdkUserAutoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkUserAuto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_user_auto
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkUserAuto record);
}