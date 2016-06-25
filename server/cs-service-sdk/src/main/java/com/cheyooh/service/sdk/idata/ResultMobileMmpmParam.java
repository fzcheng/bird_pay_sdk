/**
 * 
 */
package com.cheyooh.service.sdk.idata;

import com.cheyooh.service.framework.idata.ResultContent;

/**
 * @author Merlin
 * 
 */
public class ResultMobileMmpmParam extends ResultContent {
  private static final long serialVersionUID = 7068419084871983795L;
  private String appId;
  private String appKey;

  /**
   * 
   */
  public ResultMobileMmpmParam() {
    setTagName("mobilemmpm");
  }

  /**
   * @param tagName
   */
  public ResultMobileMmpmParam(String tagName) {
    super(tagName);
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

}
