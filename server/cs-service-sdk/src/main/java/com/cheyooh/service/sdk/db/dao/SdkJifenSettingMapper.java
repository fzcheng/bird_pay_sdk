package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkJifenSetting;
import com.cheyooh.service.sdk.db.entity.SdkJifenSettingExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkJifenSettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int countByExample(SdkJifenSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int deleteByExample(SdkJifenSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int insert(SdkJifenSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int insertSelective(SdkJifenSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    List<SdkJifenSetting> selectByExample(SdkJifenSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    Pagination<SdkJifenSetting> selectByExample(SdkJifenSettingExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    SdkJifenSetting selectOne(SdkJifenSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    SdkJifenSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkJifenSetting record, @Param("example") SdkJifenSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkJifenSetting record, @Param("example") SdkJifenSettingExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkJifenSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_jifen_setting
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkJifenSetting record);
}