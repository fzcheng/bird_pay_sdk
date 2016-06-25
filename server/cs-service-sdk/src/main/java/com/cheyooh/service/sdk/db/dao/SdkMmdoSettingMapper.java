package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkMmdoSetting;
import com.cheyooh.service.sdk.db.entity.SdkMmdoSettingExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkMmdoSettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int countByExample(SdkMmdoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int deleteByExample(SdkMmdoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int insert(SdkMmdoSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int insertSelective(SdkMmdoSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    List<SdkMmdoSetting> selectByExample(SdkMmdoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    Pagination<SdkMmdoSetting> selectByExample(SdkMmdoSettingExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    SdkMmdoSetting selectOne(SdkMmdoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    SdkMmdoSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkMmdoSetting record, @Param("example") SdkMmdoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkMmdoSetting record, @Param("example") SdkMmdoSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkMmdoSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_mmdo_setting
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkMmdoSetting record);
}