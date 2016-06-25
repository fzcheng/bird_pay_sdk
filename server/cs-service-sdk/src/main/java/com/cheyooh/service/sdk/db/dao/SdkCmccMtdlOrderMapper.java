package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkCmccMtdlOrder;
import com.cheyooh.service.sdk.db.entity.SdkCmccMtdlOrderExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkCmccMtdlOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int countByExample(SdkCmccMtdlOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int deleteByExample(SdkCmccMtdlOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int insert(SdkCmccMtdlOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int insertSelective(SdkCmccMtdlOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    List<SdkCmccMtdlOrder> selectByExample(SdkCmccMtdlOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    Pagination<SdkCmccMtdlOrder> selectByExample(SdkCmccMtdlOrderExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    SdkCmccMtdlOrder selectOne(SdkCmccMtdlOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    SdkCmccMtdlOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkCmccMtdlOrder record, @Param("example") SdkCmccMtdlOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkCmccMtdlOrder record, @Param("example") SdkCmccMtdlOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkCmccMtdlOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_cmcc_mtdl_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkCmccMtdlOrder record);
}