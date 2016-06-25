package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkSecondarySms;
import com.cheyooh.service.sdk.db.entity.SdkSecondarySmsExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkSecondarySmsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int countByExample(SdkSecondarySmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int deleteByExample(SdkSecondarySmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int insert(SdkSecondarySms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int insertSelective(SdkSecondarySms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    List<SdkSecondarySms> selectByExample(SdkSecondarySmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    Pagination<SdkSecondarySms> selectByExample(SdkSecondarySmsExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    SdkSecondarySms selectOne(SdkSecondarySmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    SdkSecondarySms selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkSecondarySms record, @Param("example") SdkSecondarySmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkSecondarySms record, @Param("example") SdkSecondarySmsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkSecondarySms record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_secondary_sms
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkSecondarySms record);
}