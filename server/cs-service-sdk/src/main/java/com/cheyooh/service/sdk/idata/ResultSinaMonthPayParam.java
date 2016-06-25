/**
 * 
 */
package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

/**
 * @author Merlin
 * 
 */
public class ResultSinaMonthPayParam extends ResultContent {
  private static final long serialVersionUID = 7004287978968228639L;
  //商户ID
  private String customId;
  //商品类型（默认11）
  private String customType;
  //应用名称
  private String appName;
  //应用类型
  private String appType;

  /**
   * 
   */
  public ResultSinaMonthPayParam() {
    setTagName("sinamonthpm");
  }

  /**
   * @param tagName
   */
  public ResultSinaMonthPayParam(String tagName) {
    setTagName(tagName);
  }

  public String getCustomId() {
    return customId;
  }

  public void setCustomId(String customId) {
    this.customId = customId;
  }

  public String getCustomType() {
    return customType;
  }

  public void setCustomType(String customType) {
    this.customType = customType;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getAppType() {
    return appType;
  }

  public void setAppType(String appType) {
    this.appType = appType;
  }

}
