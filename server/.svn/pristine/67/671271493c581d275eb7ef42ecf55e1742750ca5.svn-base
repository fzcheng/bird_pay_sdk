package jeecg.ext.sdk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkProps;
import jeecg.ext.sdk.vo.BillingPeriodVo;
import jeecg.system.service.SystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;

@Controller
@RequestMapping("/sdkProps")
public class SdkPropsController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SdkPropsController.class);
	
	private static final ObjectMapper mapper = new ObjectMapper();
	  static {
	    //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  }
	
	@Autowired
	private SystemService systemService;

	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkProps/list");
		
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

		return mv;
	}
	
	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkProps.class,
				dataGrid);
		String propsid = request.getParameter("propsid");
		String gameId = request.getParameter("gameId");
		String propsName = request.getParameter("propsName");
		String amount = request.getParameter("amount");
		String useStatus = request.getParameter("useStatus");
		String propsAlias = request.getParameter("propsAlias");
		
		if (StringUtils.isNotBlank(propsid)&&StringUtils.isNotEmpty(propsid)) {
			cq.add(Restrictions.like("propsid", "%" + propsid.trim() + "%"));
		}
		if(StringUtils.isNotBlank(gameId)&&StringUtils.isNotEmpty(gameId)){
			cq.add(Restrictions.eq("gameId", parseInteger(gameId)));
		}
		if (propsName != null) {
			cq.add(Restrictions.like("propsName", "%" + propsName.trim() + "%"));
		}
		if (amount != null) {
			if(StringUtils.isNotBlank(amount)){
				float f=Float.parseFloat(amount);
				cq.add(Restrictions.eq("amount", f));
			}
		}
		if (useStatus != null) {
			if(StringUtils.isNotBlank(useStatus)){
				cq.add(Restrictions.eq("useStatus", parseInteger(useStatus)));
			}
//			cq.add(Restrictions.sqlRestriction("CAST({alias}.mobilephone AS CHAR) like ?", "%"+mobilephone.trim()+"%", StringType.INSTANCE));
		}
		
		if (StringUtils.isNotBlank(propsAlias)&&StringUtils.isNotEmpty(propsAlias)) {
			cq.add(Restrictions.like("propsAlias", "%" + propsAlias.trim() + "%"));
		}
		
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sdkProps/addorupdate");
		Integer idInt = parseInteger(id);
		if (idInt != null) {
			SdkProps sdkProps=systemService
					.getEntity(SdkProps.class, idInt);
			mv.addObject("sdkProps", sdkProps);
		}

		List<SdkGame> games = this.systemService.getList(SdkGame.class);
		mv.addObject("games", games);

		return mv;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(SdkProps sdkProps) {
		AjaxJson json = new AjaxJson();
		try {
			logger.info("sdkProps periods1"+sdkProps.getPeriods());
			SdkProps entity=null;
			if (sdkProps.getId() != null) {
				entity = systemService.getEntity(SdkProps.class,
						sdkProps.getId());
			}
			
			Date now = new Date();
			if (entity != null) {
				entity.setGameId(sdkProps.getGameId());
				entity.setPropsName(sdkProps.getPropsName());
				entity.setPropsAlias(sdkProps.getPropsAlias());
				entity.setAmount(sdkProps.getAmount());
				entity.setPropsDesc(sdkProps.getPropsDesc());
				entity.setUseStatus(sdkProps.getUseStatus());
				logger.info("sdkProps periods"+sdkProps.getPeriods());
				entity.setPeriods(sdkProps.getPeriods());
				entity.setUpdatedTime(now);
			} else {
				entity = sdkProps;
				entity.setPropsid(getPropsid(entity.getGameId()));
				entity.setCreatedTime(now);
			}

			this.systemService.saveOrUpdate(entity);
			json.setMsg("保存成功");
		} catch (Exception e) {
			json.setMsg("保存异常");
		}

		return json;
	}
	
	@RequestMapping(params = "period")
	  public @ResponseBody
	  List<BillingPeriodVo> getBillingPeriods(Integer id) throws Exception {
	    List<BillingPeriodVo> periods = new ArrayList<BillingPeriodVo>(0);;
	    if (id != null) {
	      SdkProps entity = systemService.getEntity(SdkProps.class, id);
	      if (entity != null && StringUtils.isNotBlank(entity.getPeriods())) {
	        TypeReference<List<BillingPeriodVo>> typeRef = new TypeReference<List<BillingPeriodVo>>(){};
	        periods = mapper.readValue(entity.getPeriods(), typeRef);
	      }
	    }
	    
	    return periods;
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

		SdkProps entity=systemService.getEntity(
				SdkProps.class, idInt);
		if (entity == null) {
			json.setMsg("id=" + id + "，记录不存在");
			return json;
		}

		systemService.delete(entity);
		json.setMsg("删除成功");

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
	
	private String getPropsid(Integer gameid){
		String propsid="";
		String D="D";
		String gid=String.valueOf(gameid);
		if(gid.length()==2){
			gid="0"+gid;
		}
		CriteriaQuery cq = new CriteriaQuery(SdkProps.class);
		cq.addOrder("propsid", SortDirection.desc);
		cq.add(Restrictions.ilike("propsid", D+gid, MatchMode.START));
		List<SdkProps> sdkProps = this.systemService
				.getListByCriteriaQuery(cq, false);
		SdkProps entity=null;
		if (sdkProps != null && sdkProps.size() > 0) {
			entity=sdkProps.get(0);
			String order=String.valueOf((parseInteger(entity.getPropsid().substring(entity.getPropsid().length()-2))+1));
			if(order.length()==1){
				order="0"+order;
			}
			propsid=D+gid+order;
		}else{
			propsid=D+gid+"01";
		}
		return propsid;
	}
	
	
}
