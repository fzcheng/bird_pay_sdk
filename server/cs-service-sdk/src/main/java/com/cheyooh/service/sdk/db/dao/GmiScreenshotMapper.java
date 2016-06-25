package com.cheyooh.service.sdk.db.dao;

import com.cheyooh.service.sdk.db.entity.GmiScreenshot;
import com.cheyooh.service.sdk.db.entity.GmiScreenshotExample;
import com.cheyooh.tools.utils.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GmiScreenshotMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int countByExample(GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int deleteByExample(GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer ssid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int insert(GmiScreenshot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int insertSelective(GmiScreenshot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    List<GmiScreenshot> selectByExample(GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    Pagination<GmiScreenshot> selectByExample(GmiScreenshotExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    List<GmiScreenshot> selectByExampleWithBLOBs(GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    Pagination<GmiScreenshot> selectByExampleWithBLOBs(GmiScreenshotExample example, RowBounds rowbounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    GmiScreenshot selectByPrimaryKey(Integer ssid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GmiScreenshot record, @Param("example") GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") GmiScreenshot record, @Param("example") GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GmiScreenshot record, @Param("example") GmiScreenshotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GmiScreenshot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(GmiScreenshot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gmi_screenshot
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GmiScreenshot record);
}