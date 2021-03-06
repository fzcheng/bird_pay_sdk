package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkGameWechat;
import com.cheyooh.service.sdk.db.entity.SdkGameWechatExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkGameWechatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int countByExample(SdkGameWechatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int deleteByExample(SdkGameWechatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int insert(SdkGameWechat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int insertSelective(SdkGameWechat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    List<SdkGameWechat> selectByExample(SdkGameWechatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    Pagination<SdkGameWechat> selectByExample(SdkGameWechatExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    SdkGameWechat selectOne(SdkGameWechatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    SdkGameWechat selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkGameWechat record, @Param("example") SdkGameWechatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkGameWechat record, @Param("example") SdkGameWechatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkGameWechat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_wechat
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkGameWechat record);
}