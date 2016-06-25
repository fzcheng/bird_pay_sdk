package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkDailyStatGameCard;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.system.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;

@Controller
@RequestMapping("/sdkDailyStatGameCard")
public class SdkDailyStatGameCardController extends BaseController {
	  private static final Logger logger = Logger.getLogger(SdkDailyStatGameCardController.class);
	  
	  @Autowired
	  private SystemService systemService;
	  
	  @RequestMapping(params = "list")
	  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("sdkDailyStatGameCard/list");
		  List<SdkGame> games = this.systemService.getList(SdkGame.class);
		    
		    if (games != null && games.size() > 0) {
		      StringBuffer gameReplace = new StringBuffer();
		      for (SdkGame game : games) {
		        gameReplace.append(game.getName()).append("_").append(game.getGameId()).append(",");
		      }
		      mv.addObject("gameReplace", gameReplace.toString());
		    } else {
		      mv.addObject("gameReplace", "0_0");
		    }
		    
		    List<SdkOperatorPayChannel> payChannels = this.systemService.getList(SdkOperatorPayChannel.class);
		    if (payChannels != null && payChannels.size() > 0) {
		      StringBuffer channelReplace = new StringBuffer();
		      for (SdkOperatorPayChannel pc : payChannels) {
		        channelReplace.append(pc.getChannelName()).append("_").append(pc.getId()).append(",");
		      }
		      mv.addObject("channelReplace", channelReplace.toString());
		    } else {
		      mv.addObject("channelReplace", "0_0");
		    }
		    
		    return mv;
	  }

	  @RequestMapping(params = "datagrid")
	  public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	    CriteriaQuery cq = new CriteriaQuery(SdkDailyStatGameCard.class, dataGrid);
	    String gameId=request.getParameter("gameId");
		if(StringUtils.isNotEmpty(gameId)&&StringUtils.isNotBlank(gameId)){
			cq.add(Restrictions.like("gameId", parseInteger(gameId)));
		}
		String operatorType=request.getParameter("operatorType");
		if(StringUtils.isNotEmpty(operatorType)&&StringUtils.isNotBlank(operatorType)){
			Integer i=parseInteger(operatorType);
			cq.add(Restrictions.eq("operatorType", i));
		}
		String operatorPayChannelId=request.getParameter("operatorPayChannelId");
		if(StringUtils.isNotEmpty(operatorPayChannelId)&&StringUtils.isNotBlank(operatorPayChannelId)){
			cq.add(Restrictions.eq("operatorPayChannelId", parseInteger(operatorPayChannelId)));
		}
		String channelNum=request.getParameter("channelNum");
		if(StringUtils.isNotEmpty(channelNum)&&StringUtils.isNotBlank(channelNum)){
			cq.add(Restrictions.like("channelNum", channelNum));
		}
	    this.systemService.getDataGridReturn(cq, true);
	    TagUtil.datagrid(response, dataGrid);
	  }
	  
	  @RequestMapping(params = "addorupdate")
	  public ModelAndView addorupdate(String id) {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("sdkDailyStatGameCard/addorupdate");
	    logger.info("the sdkDailyStatGameCard id is :"+id);
	    Integer idInt = parseInteger(id);
	    logger.info("the idInt is :"+idInt);
	    if (idInt != null) {
	    	SdkDailyStatGameCard sdkDailyStatGameCard=systemService.getEntity(SdkDailyStatGameCard.class, idInt);
	      mv.addObject("sdkDailyStatGameCard", sdkDailyStatGameCard);
	    }
	    
	    List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);

		List<SdkOperatorPayChannel> payChannels = this.systemService
				.getList(SdkOperatorPayChannel.class);
		mv.addObject("payChannels", payChannels);
		
	    return mv;
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

	  @RequestMapping(params = "save")
		@ResponseBody
		public AjaxJson save(SdkDailyStatGameCard sdkDailyStatGameCard) {
			AjaxJson json = new AjaxJson();
			try {
				SdkDailyStatGameCard entity=null;
				if (sdkDailyStatGameCard.getId() != null) {
					entity = systemService.getEntity(SdkDailyStatGameCard.class,
							sdkDailyStatGameCard.getId());
				}
				Date now = new Date();
				if (entity != null) {
					entity.setGameId(sdkDailyStatGameCard.getGameId());
					entity.setOperatorPayChannelId(sdkDailyStatGameCard.getOperatorPayChannelId());
					entity.setUpdatedTime(now);
				} else {
					entity = sdkDailyStatGameCard;
					entity.setCreatedTime(now);
				}

				this.systemService.saveOrUpdate(entity);
				json.setMsg("保存成功");
			} catch (Exception e) {
				json.setMsg("保存异常");
			}

			return json;
		}
		
		@RequestMapping(params = "del", method = RequestMethod.POST)
		@ResponseBody
		public AjaxJson del(String id) {
			AjaxJson json = new AjaxJson();
			Integer idInt = parseInteger(id);
			if (idInt == null) {
				json.setMsg("请选择要删除的项目");
				return json;
			}

			SdkDailyStatGameCard entity=systemService.getEntity(
					SdkDailyStatGameCard.class, idInt);
			if (entity == null) {
				json.setMsg("id=" + id + "，记录不存在");
				return json;
			}

			systemService.delete(entity);
			json.setMsg("删除成功");

			return json;
		}
}
