package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkGameAlipay;
import com.cheyooh.service.sdk.db.entity.SdkGameAlipayExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkGameAlipayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int countByExample(SdkGameAlipayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int deleteByExample(SdkGameAlipayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int insert(SdkGameAlipay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int insertSelective(SdkGameAlipay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    List<SdkGameAlipay> selectByExample(SdkGameAlipayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    Pagination<SdkGameAlipay> selectByExample(SdkGameAlipayExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    SdkGameAlipay selectOne(SdkGameAlipayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    SdkGameAlipay selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkGameAlipay record, @Param("example") SdkGameAlipayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkGameAlipay record, @Param("example") SdkGameAlipayExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkGameAlipay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_alipay
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkGameAlipay record);
}