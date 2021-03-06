package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkImsiIccid;
import com.cheyooh.service.sdk.db.entity.SdkImsiIccidExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkImsiIccidMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int countByExample(SdkImsiIccidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int deleteByExample(SdkImsiIccidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int insert(SdkImsiIccid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int insertSelective(SdkImsiIccid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    List<SdkImsiIccid> selectByExample(SdkImsiIccidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    Pagination<SdkImsiIccid> selectByExample(SdkImsiIccidExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    SdkImsiIccid selectOne(SdkImsiIccidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    SdkImsiIccid selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkImsiIccid record, @Param("example") SdkImsiIccidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkImsiIccid record, @Param("example") SdkImsiIccidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkImsiIccid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_imsi_iccid
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkImsiIccid record);
}