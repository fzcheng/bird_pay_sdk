package com.cheyooh.service.sdk.action.client;

import java.util.List;

import com.cheyooh.service.dal.DAL;
import com.cheyooh.service.dal.DALFactory;
import com.cheyooh.service.framework.idata.Result;
import com.cheyooh.service.framework.idata.ResultContentList;
import com.cheyooh.service.framework.idata.StatusCode;
import com.cheyooh.service.sdk.cfg.Cfg;
import com.cheyooh.service.sdk.db.dao.*;
import com.cheyooh.service.sdk.db.entity.*;
import com.cheyooh.service.sdk.idata.CmdGeneral;
import com.cheyooh.service.sdk.idata.ResultAdvertisementList;

public class Game_logout extends AbstractSdkClientService<CmdGeneral> {
  @Override
  protected boolean isLoginRequired() {

    return false;
  }

  @Override
  protected Result execute(CmdGeneral cmd) {
    DAL dal = DALFactory.createDAL();
    try {

      SdkGameAdvertisementListMapper sdkGameAdvertisementListMapper = dal.getMapper(SdkGameAdvertisementListMapper.class);
      SdkGameAdvertisementListExample sdkGameAdvertisementListExample = new SdkGameAdvertisementListExample();
      sdkGameAdvertisementListExample.createCriteria().andIfshowEqualTo(1);
      List<SdkGameAdvertisementList> sdkGameAdvertisementLists = sdkGameAdvertisementListMapper.selectByExample(sdkGameAdvertisementListExample);
      
      if (sdkGameAdvertisementLists == null || sdkGameAdvertisementLists.size() < 1) {
        return StatusCode.ERR_NOTFOUND();
      }
      
      SdkGameMapper sdkGameMapper = dal.getMapper(SdkGameMapper.class);
      SdkGameExample sdkGameExample = new SdkGameExample();
      sdkGameExample.createCriteria().andAppKeyEqualTo(cmd.getAppkey()).andIfPushEqualTo(1);
      SdkGame sdkGame = sdkGameMapper.selectOne(sdkGameExample);
      if (sdkGame == null) {
        return StatusCode.ERR_NOTFOUND();
      }
      
      ResultContentList contentList = new ResultContentList();
      String basePath = Cfg.cfg.getString("advertisement.url");
      for (SdkGameAdvertisementList sdkGameAdvertisementList : sdkGameAdvertisementLists) {
        ResultAdvertisementList resultAdvertisementList = new ResultAdvertisementList();
        logger.debug("the sdkGameAdvertisementLists gameshow");
        logger.debug("the sdkGameAdvertisementLists ifshow");
        resultAdvertisementList.setAdvertisement_url(sdkGameAdvertisementList.getAdvertisementUrl());
        String iconUrl = basePath + sdkGameAdvertisementList.getIconUrl();
        resultAdvertisementList.setIcon_url(iconUrl);
        contentList.addContent(resultAdvertisementList);
      }
      
      Result result = new Result(contentList);
      logger.debug("advertisementlist result : " + result.getXml("get_advertisement_list"));
      dal.commit();
      return result;

    } catch (Exception e) {
      logger.error("the game_logout error is : " + e);
      return StatusCode.ERR_NOTFOUND();
    } finally {
      dal.close();
    }
  }
}
