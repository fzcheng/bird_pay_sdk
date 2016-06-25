/**
 * 
 */
package jeecg.ext.sdk.controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannel;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameChannel;
import jeecg.system.service.SystemService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Merlin
 *
 */
@Controller
@RequestMapping("/gameChannel")
public class GameChannelController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(GameChannelController.class);
  @Autowired
  private SystemService systemService;
  
  @RequestMapping(params = "list")
  public ModelAndView list() {
    List<SdkGame> games = systemService.getList(SdkGame.class);
    
    ModelAndView mv = new ModelAndView("gameChannel/list");
    if (games != null && games.size() > 0) {
      StringBuilder gamesReplace = new StringBuilder();
      for (SdkGame game : games) {
        gamesReplace.append(game.getName()).append("_").append(game.getGameId()).append(",");
      }
      mv.addObject("gamesReplace", gamesReplace.toString());
    } else {
      mv.addObject("gamesReplace", "0_0");
    }
    
    List<SdkChannel> channels = systemService.getList(SdkChannel.class);
    if (channels != null && channels.size() > 0) {
      StringBuffer channelsReplace = new StringBuffer();
      for (SdkChannel channel : channels) {
        channelsReplace.append(channel.getChannelName()).append("_").append(channel.getId()).append(",");
      }
      mv.addObject("channelsReplace", channelsReplace.toString());
    } else {
      mv.addObject("channelsReplace", "0_0");
    }
    
    return mv;
  }
  
  @RequestMapping(params = "data")
  public void getData(SdkGameChannel gameChannel, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    CriteriaQuery cq = new CriteriaQuery(SdkGameChannel.class, dataGrid);
    cq.addOrder("id", SortDirection.desc);
    cq.add();
    String gameId=request.getParameter("gameId");
	if(StringUtils.isNotEmpty(gameId)&&StringUtils.isNotBlank(gameId)){
		cq.add(Restrictions.eq("gameId", parseInteger(gameId)));
	}
    HqlGenerateUtil.installHql(cq, gameChannel);
    this.systemService.getDataGridReturn(cq, true);
    @SuppressWarnings("unchecked")
    List<SdkGameChannel> gameChannels = dataGrid.getReaults();
    if (gameChannels != null) {
      NumberFormat format = NumberFormat.getPercentInstance();
      format.setMaximumFractionDigits(2);
      format.setMinimumFractionDigits(2);
      for (SdkGameChannel gc : gameChannels) {
        gc.setPayDeductPctStr(format.format(gc.getPayDeductPct()));
        gc.setRegDeductPctStr(format.format(gc.getRegDeductPct()));
      }
    }
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params = "edit")
  public ModelAndView edit(Integer id) {
    ModelAndView mv = new ModelAndView("gameChannel/edit");
    
    List<SdkGame> games = systemService.getList(SdkGame.class);
    mv.addObject("games", games);
    
    List<SdkChannel> channels = systemService.getList(SdkChannel.class);
    mv.addObject("channels", channels);
    
    if (id != null) {
      SdkGameChannel gameChannel = systemService.getEntity(SdkGameChannel.class, id);
      NumberFormat format = NumberFormat.getPercentInstance();
      format.setMaximumFractionDigits(2);
      format.setMinimumFractionDigits(2);
      gameChannel.setPayDeductPctStr(format.format(gameChannel.getPayDeductPct()));
      gameChannel.setRegDeductPctStr(format.format(gameChannel.getRegDeductPct()));
      
      mv.addObject("gameChannel", gameChannel);
    }

    return mv;
  }
  
  @RequestMapping(params = "save")
  public @ResponseBody AjaxJson save(SdkGameChannel gameChannel) {
    AjaxJson json = new AjaxJson();
    
    SdkGameChannel entity = null;
    CriteriaQuery cq = new CriteriaQuery(SdkGameChannel.class);
    cq.eq("gameId", gameChannel.getGameId());
    cq.eq("channelId", gameChannel.getChannelId());
    cq.add();
    List<SdkGameChannel> list = systemService.getListByCriteriaQuery(cq, true);
    if (list != null & list.size() > 0) {
      entity = list.get(0);
      if (gameChannel.getId() == null || !entity.getId().equals(gameChannel.getId())) {
        json.setMsg("操作失败，游戏已设置了此渠道");
        json.setSuccess(false);
        return json;
      }
    }
    Date time = new Date();
    if (entity == null) {
      entity = gameChannel;
      entity.setCreatedTime(time);
    }
    
    NumberFormat format = NumberFormat.getPercentInstance();
    format.setMaximumFractionDigits(2);
    format.setMinimumFractionDigits(2);
    try {
      double payDeductPct = format.parse(gameChannel.getPayDeductPctStr()).doubleValue();
      entity.setPayDeductPct(payDeductPct);
      entity.setPayDividePct(1 - payDeductPct);
    } catch (ParseException e) {
      json.setMsg("操作失败，渠道支付扣量不是百分数！");
      json.setSuccess(false);
      return json;
    }
    
    try {
      double regDeductPct = format.parse(gameChannel.getRegDeductPctStr()).doubleValue();
      entity.setRegDeductPct(regDeductPct);
      entity.setRegDividePct(1 - regDeductPct);
    } catch (ParseException e) {
      json.setMsg("操作失败，渠道注册扣量不是百分数！");
      json.setSuccess(false);
      return json;
    }
    
    //entity.setChannelCode(channelCode);
    entity.setChannelId(gameChannel.getChannelId());
    entity.setGameId(gameChannel.getGameId());
    entity.setUpdatedTime(time);
    
    systemService.saveOrUpdate(entity);
    json.setMsg("操作成功");
    return json;
  }
  
  @RequestMapping(params = "del")
  public @ResponseBody AjaxJson delete(Integer id) {
    AjaxJson json = new AjaxJson();
    if (id == null) {
      json.setMsg("游戏投放渠道不存在！");
      json.setSuccess(false);
      return json;
    }
    
    SdkGameChannel gameChannel = systemService.getEntity(SdkGameChannel.class, id);
    if (gameChannel == null) {
      json.setMsg("游戏投放渠道不存在！");
      json.setSuccess(false);
      return json;
    }
    
    systemService.delete(gameChannel);
    
    json.setMsg("删除成功！");
    return json;
  }
  
  private Integer parseInteger(String str) {
		Integer num = null;
		try {
			if (StringUtils.isNotBlank(str)) {
				num = Integer.valueOf(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("parse number string error! str = " + str);
		}
		return num;
	}
}
