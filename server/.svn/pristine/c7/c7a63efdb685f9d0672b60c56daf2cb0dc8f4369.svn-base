package jeecg.ext.sdk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkMmdoSetting;
import jeecg.ext.sdk.entity.SdkOperatorPayChannel;
import jeecg.ext.sdk.entity.SdkOperatorPayLimit;
import jeecg.ext.sdk.entity.SdkProps;
import jeecg.ext.sdk.vo.BillingPeriodVo;
import jeecg.system.service.SystemService;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mmdoSetting")
public class MmdoSettingController extends BaseController {
  private static final Logger logger = Logger.getLogger(MmdoSettingController.class);
  private static final ObjectMapper mapper = new ObjectMapper();
  private String splitGroupString = ",";
  private String splitString = ResourceUtil.getConfigByName("mmdo_sub_content_split", "\\|");
  
  @Autowired
  private SystemService systemService;

  @RequestMapping(params = "list")
  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("mmdoSetting/list");

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
    CriteriaQuery cq = new CriteriaQuery(SdkMmdoSetting.class, dataGrid);
	String operatorPayChannelId = request.getParameter("operatorPayChannelId");
	String amount = request.getParameter("amount");
	String useStatus = request.getParameter("useStatus");
	String originalGameId = request.getParameter("originalGameId");
	String chargetip = request.getParameter("chargetip");
	String chargesuceesstip = request.getParameter("chargesuceesstip");
	if (operatorPayChannelId != null) {
		if(StringUtils.isNotBlank(operatorPayChannelId)){
			cq.add(Restrictions.eq("operatorPayChannelId", parseInteger(operatorPayChannelId.trim())));	
    	}
	}
	if (amount != null) {
		if(StringUtils.isNotBlank(amount)){
			float f=Float.parseFloat(amount);
			cq.add(Restrictions.eq("amount", f));
		}
	}
	if(StringUtils.isNotBlank(useStatus)&&StringUtils.isNotEmpty(useStatus)){
//		short s=parseInteger(useStatus).shortValue();
		cq.add(Restrictions.eq("useStatus", parseInteger(useStatus)));
//		cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?", "%"+mobilephone.trim()+"%", StringType.INSTANCE));
	}
	if(StringUtils.isNotBlank(originalGameId)&&StringUtils.isNotEmpty(originalGameId)){
		short s= parseInteger(chargetip).shortValue();
		cq.add(Restrictions.eq("originalGameId",s));
	}
	if(StringUtils.isNotBlank(chargetip)&&StringUtils.isNotEmpty(chargetip)){
		cq.add(Restrictions.eq("chargetip", parseInteger(chargetip)));
	}
	if(StringUtils.isNotBlank(chargesuceesstip)&&StringUtils.isNotEmpty(chargesuceesstip)){
		cq.add(Restrictions.eq("chargesuceesstip", parseInteger(chargesuceesstip)));
	}
	String additional = request.getParameter("additional");
	if(StringUtils.isNotBlank(additional)&&StringUtils.isNotEmpty(additional)){
		cq.add(Restrictions.eq("additional", parseInteger(additional)));
	}
	String gameId = request.getParameter("gameId");
	if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotEmpty(gameId)){
		cq.add(Restrictions.eq("gameId", parseInteger(gameId)));
	}
	
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params = "addorupdate")
  public ModelAndView addorupdate(String id) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("mmdoSetting/addorupdate");
    List<SdkGame> games = this.systemService.getList(SdkGame.class);
    mv.addObject("games", games);
    
    List<SdkOperatorPayChannel> payChannels = this.systemService.getList(SdkOperatorPayChannel.class);
    mv.addObject("payChannels", payChannels);
    
    if (StringUtils.isBlank(id)) {
      mv.addObject("opt", "add");
      return mv;
    }

    SdkMmdoSetting mmdoSetting = this.systemService.getEntity(SdkMmdoSetting.class, Integer.valueOf(id));
    if (mmdoSetting.getNumber().split(ResourceUtil.getConfigByName("mmdo_content_pre_split", ",")).length > 1) {
      splitGroupString = ResourceUtil.getConfigByName("mmdo_content_pre_split", ",");
    } else {
      splitGroupString = ResourceUtil.getConfigByName("mmdo_content_split", "#");
    }

    String[] numberArray = mmdoSetting.getNumber().split(splitGroupString);
    String[] contentArray = mmdoSetting.getSendContent().split(splitGroupString);
    String[] shieldNumberGroupArray = mmdoSetting.getShieldNumber().split(splitGroupString);
    String[] shieldKeywordGroupArray = mmdoSetting.getShieldKeyword().split(splitGroupString);
    Map<String, String> sendMap = new HashMap<String, String>();
    List<Map<String, String>> shieldGroupList = new ArrayList<Map<String, String>>();
    for (int i = 0; i < numberArray.length; i++) {
      sendMap.put(numberArray[i], contentArray[i]);
      String[] shieldNumberSubArray = shieldNumberGroupArray[i].split(splitString);
      String[] shieldKeywordSubArray = shieldKeywordGroupArray[i].split(splitString);
      Map<String, String> shieldSubMap = new HashMap<String, String>();
      for (int j = 0; j < shieldNumberSubArray.length; j++) {
        shieldSubMap.put(shieldNumberSubArray[j], shieldKeywordSubArray[j]);

      }
      shieldGroupList.add(shieldSubMap);
    }

    mv.addObject("opt", "edit");
    mv.addObject("mmdoSetting", mmdoSetting);
    mv.addObject("sendMap", sendMap);
    mv.addObject("shieldGroupList", shieldGroupList);

    return mv;
  }
  
  @RequestMapping(params = "periods")
  public @ResponseBody
  List<BillingPeriodVo> getBillingPeriods(Integer id) throws Exception {
    List<BillingPeriodVo> periods = new ArrayList<BillingPeriodVo>(0);;
    if (id != null) {
      SdkMmdoSetting entity= systemService.getEntity(SdkMmdoSetting.class, id);
      if (entity != null && StringUtils.isNotBlank(entity.getChargetipPeriods())) {
        TypeReference<List<BillingPeriodVo>> typeRef = new TypeReference<List<BillingPeriodVo>>(){};
        periods = mapper.readValue(entity.getChargetipPeriods(), typeRef);
      }
    }
    
    return periods;
  }
  
  @RequestMapping(params = "cstperiods")
  public @ResponseBody
  List<BillingPeriodVo> getcPeriods(Integer id) throws Exception {
    List<BillingPeriodVo> periods = new ArrayList<BillingPeriodVo>(0);;
    if (id != null) {
      SdkMmdoSetting entity= systemService.getEntity(SdkMmdoSetting.class, id);
      if (entity != null && StringUtils.isNotBlank(entity.getChargesuceesstipPeriods())) {
        TypeReference<List<BillingPeriodVo>> typeRef = new TypeReference<List<BillingPeriodVo>>(){};
        periods = mapper.readValue(entity.getChargesuceesstipPeriods(), typeRef);
      }
    }
    
    return periods;
  }
  
  @RequestMapping(params = "getPropsid", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getPropsid(String gameId) {
		CriteriaQuery cq = new CriteriaQuery(SdkProps.class);
		cq.addOrder("propsid", SortDirection.asc);
		Integer g=parseInteger(gameId);
		logger.info("gameid is :"+g);
		cq.add(Restrictions.eq("gameId", g));
		List<SdkProps> sdkProps = this.systemService
				.getListByCriteriaQuery(cq, false);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (sdkProps != null && sdkProps.size() > 0) {
			for (SdkProps sdkProp : sdkProps) {
				logger.debug("Propsid is :"+sdkProp.getPropsid());
				logger.info("Propsid is :"+sdkProp.getPropsid());
				map.put(sdkProp.getId(), sdkProp.getPropsid());
			}
		}
		JSONObject j = JSONObject.fromObject(map);
		return j;
	}
  
  @RequestMapping(params = "save")
  @ResponseBody
  public AjaxJson save(SdkMmdoSetting mmdoSetting, HttpServletRequest request) {
    AjaxJson json = new AjaxJson();
    try {
      this.systemService.saveOrUpdate(mmdoSetting);
      json.setMsg("保存成功");
    } catch (Exception e) {
      json.setMsg("保存异常");
    }

    return json;
  }

  @RequestMapping(params = "setLimit")
  public ModelAndView setLimit() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("mmdoSetting/setLimit");
    List<SdkOperatorPayLimit> limits = this.systemService.getList(SdkOperatorPayLimit.class);
    mv.addObject("limits", limits);
    return mv;
  }
  
  @RequestMapping(params = "saveSetting")
  @ResponseBody
  public AjaxJson saveSetting(SdkOperatorPayLimitList limitList) {
    AjaxJson json = new AjaxJson();
    if (limitList != null) {
      List<SdkOperatorPayLimit> limits = limitList.getLimits();
      for (SdkOperatorPayLimit limit : limits) {
        this.systemService.saveOrUpdate(limit);
      }
    }
    return json;
  }

  @RequestMapping(params = "del", method = RequestMethod.POST)
  @ResponseBody
  public AjaxJson del(HttpServletRequest request, SdkMmdoSetting mmdoSetting) {
    AjaxJson json = new AjaxJson();
    try {
      SdkMmdoSetting sdkMmdoSetting = this.systemService.getEntity(SdkMmdoSetting.class, mmdoSetting.getId());
      this.systemService.delete(sdkMmdoSetting);
      json.setMsg("删除成功");
    } catch (Exception e) {
      json.setMsg("无法删除");
    }
    return json;
  }

  public static class SdkOperatorPayLimitList {
    private List<SdkOperatorPayLimit> limits = new ArrayList<SdkOperatorPayLimit>();

    public List<SdkOperatorPayLimit> getLimits() {
      return limits;
    }

    public void setLimits(List<SdkOperatorPayLimit> limits) {
      this.limits = limits;
    }
    
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
