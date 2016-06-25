package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.GmiPkg;
import com.cheyooh.service.sdk.db.entity.GmiPkgExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GmiPkgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int countByExample(GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int deleteByExample(GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String pkg);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int insert(GmiPkg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int insertSelective(GmiPkg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    List<GmiPkg> selectByExample(GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    Pagination<GmiPkg> selectByExample(GmiPkgExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    List<GmiPkg> selectByExampleWithBLOBs(GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    Pagination<GmiPkg> selectByExampleWithBLOBs(GmiPkgExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    GmiPkg selectByPrimaryKey(String pkg);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GmiPkg record, @Param("example") GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") GmiPkg record, @Param("example") GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GmiPkg record, @Param("example") GmiPkgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GmiPkg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(GmiPkg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_pkg
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GmiPkg record);
}