package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.SdkGameDomain;
import com.cheyooh.service.sdk.db.entity.SdkGameDomainExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SdkGameDomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int countByExample(SdkGameDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int deleteByExample(SdkGameDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int insert(SdkGameDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int insertSelective(SdkGameDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    List<SdkGameDomain> selectByExample(SdkGameDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    Pagination<SdkGameDomain> selectByExample(SdkGameDomainExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    SdkGameDomain selectOne(SdkGameDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    SdkGameDomain selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") SdkGameDomain record, @Param("example") SdkGameDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") SdkGameDomain record, @Param("example") SdkGameDomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SdkGameDomain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sdk_game_domain
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SdkGameDomain record);
}