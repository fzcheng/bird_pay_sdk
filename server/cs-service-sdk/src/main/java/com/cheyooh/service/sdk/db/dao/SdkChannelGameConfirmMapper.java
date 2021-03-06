package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkChannelGameConfirm;
import com.cheyooh.service.sdk.db.entity.SdkChannelGameConfirmExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkChannelGameConfirmMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int countByExample(SdkChannelGameConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int deleteByExample(SdkChannelGameConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int insert(SdkChannelGameConfirm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int insertSelective(SdkChannelGameConfirm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    List<SdkChannelGameConfirm> selectByExample(SdkChannelGameConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    Pagination<SdkChannelGameConfirm> selectByExample(SdkChannelGameConfirmExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    SdkChannelGameConfirm selectOne(SdkChannelGameConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    SdkChannelGameConfirm selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkChannelGameConfirm record, @Param("example") SdkChannelGameConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkChannelGameConfirm record, @Param("example") SdkChannelGameConfirmExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkChannelGameConfirm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_channel_game_confirm
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkChannelGameConfirm record);
}