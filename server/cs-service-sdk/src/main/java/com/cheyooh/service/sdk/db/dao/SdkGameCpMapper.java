package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkGameCp;
import com.cheyooh.service.sdk.db.entity.SdkGameCpExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkGameCpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int countByExample(SdkGameCpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int deleteByExample(SdkGameCpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer cpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int insert(SdkGameCp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int insertSelective(SdkGameCp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    List<SdkGameCp> selectByExample(SdkGameCpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    Pagination<SdkGameCp> selectByExample(SdkGameCpExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    SdkGameCp selectOne(SdkGameCpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    SdkGameCp selectByPrimaryKey(Integer cpId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkGameCp record, @Param("example") SdkGameCpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkGameCp record, @Param("example") SdkGameCpExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkGameCp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_cp
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkGameCp record);
}