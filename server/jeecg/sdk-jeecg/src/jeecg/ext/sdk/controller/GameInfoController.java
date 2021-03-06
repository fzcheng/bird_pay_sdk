package jeecg.ext.sdk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jeecg.cgreport.exception.CgReportNotFoundException;
import jeecg.ext.sdk.entity.SdkGame;
import jeecg.ext.sdk.entity.SdkGameCp;
import jeecg.ext.sdk.entity.SdkGamePayment;
import jeecg.ext.sdk.entity.SdkGamePush;
import jeecg.ext.sdk.entity.SdkOrder;
import jeecg.ext.sdk.entity.SdkPayment;
import jeecg.ext.sdk.entity.SdkPushcode;
import jeecg.ext.sdk.entity.SdkUpgradeJar;
import jeecg.ext.sdk.entity.SdkWiipayPaycode;
import jeecg.ext.sdk.entity.SelectItemt;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({ "/gameInfo" })
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GameInfoController extends BaseController {
	static Log logger = LogFactory.getLog(GameInfoController.class);

	// @Autowired
	// private GameInfoServiceI gameInfoService;
	private UserService userService;
	private SystemService systemService;
	private String message;

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public UserService getUserService() {
		return this.userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(params = { "list" })
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getParameter("cp_id") != null) {
			request.setAttribute("cp_id", request.getParameter("cp_id"));
		}
		return new ModelAndView("gameInfo/list");
	}

	@RequestMapping(params = { "list2" })
	public ModelAndView list2(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getParameter("cp_id") != null) {
			request.setAttribute("cp_id", request.getParameter("cp_id"));
		}
		return new ModelAndView("gameInfo/list2");
	}

	@RequestMapping(params = { "editPayment" })
	public ModelAndView editPayment(String gameId, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取paymentList
		List<SdkGamePayment> sdkGamePayments = this.systemService
				.findByProperty(SdkGamePayment.class, "sdkGame.gameId",
						Integer.valueOf(oConvertUtils.getInt(gameId)));
		request.setAttribute("sdkGamePayments", sdkGamePayments);
		request.setAttribute("gameId", gameId);
		return new ModelAndView("gameInfo/editpayment");
	}

	@RequestMapping(params = { "savePayment" })
	@ResponseBody
	public AjaxJson savePayment(HttpServletRequest reques, String gameId,
			String id, String idx) {
		AjaxJson json = new AjaxJson();
		String[] ids = id.split(",");
		String[] idxs = idx.split(",");
		if (ids.length != idxs.length) {
			json.setMsg("数据异常!");
			return json;
		}
		for (int i = 0; i < ids.length; i++) {
			SdkGamePayment sdkGamePayment = (SdkGamePayment) this.systemService
					.getEntity(SdkGamePayment.class,
							Integer.valueOf(oConvertUtils.getInt(ids[i])));
			if (sdkGamePayment != null) {
				sdkGamePayment.setIdx(Integer.valueOf(oConvertUtils
						.getInt(idxs[i])));
				this.systemService.saveOrUpdate(sdkGamePayment);
			}
		}
		json.setMsg("更新成功！");
		return json;
	}

	@RequestMapping(params = { "datagrid2" })
	public void datagrid2(SdkGame gameData, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkGame.class, dataGrid);

		// 获取用户

		String cpId = "";
		if (StringUtil.isNotEmpty(request.getParameter("cp_id"))) {
			cpId = request.getParameter("cp_id");
		} else {
			// TSUser user=ResourceUtil.getSessionUserName();
			// cpId=user.getUserName();
			cpId = getCpId().toString();
		}
		cq.eq("sdkGameCp.cpId", Integer.valueOf(cpId));

		// 查询条件组装器
		HqlGenerateUtil.installHql(cq, gameData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid, "gameId");
	}

	@RequestMapping(params = { "datagrid" })
	public void datagrid(SdkGame gameData, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SdkGame.class, dataGrid);

		// 获取用户

		String cpId = "";
		if (StringUtil.isNotEmpty(request.getParameter("cp_id"))) {
			logger.info("the request cp_id is :"
					+ request.getParameter("cp_id"));
			cpId = request.getParameter("cp_id");
		} else {
			logger.info("the getCpId is :" + getCpId().toString());
			cpId = getCpId().toString();
		}
		cq.eq("sdkGameCp.cpId", Integer.valueOf(cpId));
		String name = request.getParameter("name");
		if ((name != null) && (StringUtils.isNotBlank(name))) {
			cq.add(Restrictions.like("name", "%" + name.trim() + "%"));
		}
		// 查询条件组装器
		HqlGenerateUtil.installHql(cq, gameData);
		this.systemService.getDataGridReturn(cq, true);
		TagUtilExt.datagrid(response, dataGrid, "gameId");
	}

	/**
	 * 列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = { "addorupdate2" })
	public ModelAndView addorupdate2(String id, String cp_id,
			HttpServletRequest req) {
		String app_key = getAppkey();
		List<SdkPayment> sdkPayments = this.systemService
				.getList(SdkPayment.class);
		List<SdkGamePayment> sdkGamePayments = new ArrayList<SdkGamePayment>();
		if (StringUtil.isNotEmpty(id)) {
			SdkGame gameData = (SdkGame) this.systemService.getEntity(
					SdkGame.class, Integer.valueOf(id));
			SdkPushcode sdkPushcode = (SdkPushcode) this.systemService
					.findUniqueByProperty(SdkPushcode.class, "sdkGame.gameId",
							gameData.getGameId());
			app_key = gameData.getAppKey();
			// paymentList
			sdkGamePayments = new ArrayList<SdkGamePayment>(
					gameData.getSdkGamePayments());
			// this.systemService.getListByCriteriaQuery(criteriaQuery, false);
			req.setAttribute("pushMasterSecret",
					sdkPushcode.getPushMasterSecret());
			req.setAttribute("pushAppkey", sdkPushcode.getPushAppKey());
			req.setAttribute("gameDataPage", gameData);
		}

		List<SelectItemt> selectList = new ArrayList<SelectItemt>();
		for (SdkPayment payment : sdkPayments) {
			SelectItemt item = new SelectItemt();
			boolean checked = false;
			for (SdkGamePayment sdkpayment : sdkGamePayments) {
				if (payment.getId() == sdkpayment.getSdkPayment().getId()) {
					checked = true;
					break;
				}
			}
			item.setId(payment.getId());
			item.setName(payment.getPayName());
			item.setChecked(false);
			if (checked) {
				item.setChecked(true);
			}
			selectList.add(item);

		}

		req.setAttribute("cp_id", cp_id);
		req.setAttribute("selectList", selectList);
		req.setAttribute("appKey", app_key);
		return new ModelAndView("gameInfo/add2");
	}

	/**
	 * 列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = { "addorupdate" })
	public ModelAndView addorupdate(String id, String cp_id,
			HttpServletRequest req) {
		String app_key = getAppkey();
		List<SdkPayment> sdkPayments = this.systemService
				.getList(SdkPayment.class);
		List<SdkGamePayment> sdkGamePayments = new ArrayList<SdkGamePayment>();
		if (StringUtil.isNotEmpty(id)) {
			SdkGame gameData = (SdkGame) this.systemService.getEntity(
					SdkGame.class, Integer.valueOf(id));
			SdkPushcode sdkPushcode = (SdkPushcode) this.systemService
					.findUniqueByProperty(SdkPushcode.class, "sdkGame.gameId",
							gameData.getGameId());
			app_key = gameData.getAppKey();
			// paymentList
			sdkGamePayments = new ArrayList<SdkGamePayment>(
					gameData.getSdkGamePayments());
			// this.systemService.getListByCriteriaQuery(criteriaQuery, false);
			req.setAttribute("pushMasterSecret",
					sdkPushcode.getPushMasterSecret());
			req.setAttribute("pushAppkey", sdkPushcode.getPushAppKey());
			req.setAttribute("gameDataPage", gameData);
		}

		List<SelectItemt> selectList = new ArrayList<SelectItemt>();
		for (SdkPayment payment : sdkPayments) {
			SelectItemt item = new SelectItemt();
			boolean checked = false;
			for (SdkGamePayment sdkpayment : sdkGamePayments) {
				if (payment.getId() == sdkpayment.getSdkPayment().getId()) {
					checked = true;
					break;
				}
			}
			item.setId(payment.getId());
			item.setName(payment.getPayName());
			item.setChecked(false);
			if (checked) {
				item.setChecked(true);
			}
			selectList.add(item);

		}
		SdkGameCp sdkGameCp = (SdkGameCp) this.systemService.getEntity(
				SdkGameCp.class, Integer.valueOf(cp_id));
		req.setAttribute("ServerKEY", sdkGameCp.getApiKey());
		req.setAttribute("cp_id", cp_id);
		req.setAttribute("selectList", selectList);
		req.setAttribute("appKey", app_key);

		List<SdkUpgradeJar> sdkUpgradeJars = this.systemService
				.getList(SdkUpgradeJar.class);
		req.setAttribute("sdkUpgradeJars", sdkUpgradeJars);
		// mv.addObject("sdkUpgradeJars",sdkUpgradeJars);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gameInfo/add");
		return mv;

		// return new ModelAndView("gameInfo/add");

	}

	@RequestMapping(params = { "save" })
	@ResponseBody
	public AjaxJson save(HttpServletRequest req, SdkGame gameData) {
		AjaxJson j = new AjaxJson();
		String cp_id = req.getParameter("cp_id");
		String pushMasterSecret = req.getParameter("pushMasterSecret");
		String pushAppkey = req.getParameter("pushAppkey");
		String[] payments = new String[0];
		if (StringUtil.isNotEmpty(req.getParameterValues("payType"))) {
			payments = req.getParameterValues("payType");
		}
		List<String> paymentList = Arrays.asList(payments);
		// 获取对象
		Integer[] paymentInt;
		if (StringUtil.isNotEmpty(gameData.getGameId())) {
			SdkGame game = (SdkGame) this.systemService.getEntity(
					SdkGame.class, gameData.getGameId());
			SdkGameCp cmp = (SdkGameCp) this.systemService
					.findUniqueByProperty(SdkGameCp.class, "cpId",
							Integer.valueOf(cp_id));

			SdkPushcode sdkPushcode = (SdkPushcode) this.systemService
					.findUniqueByProperty(SdkPushcode.class, "sdkGame.id",
							game.getGameId());
			sdkPushcode.setPushAppKey(pushAppkey);
			sdkPushcode.setPushMasterSecret(pushMasterSecret);
			this.systemService.saveOrUpdate(sdkPushcode);

			game.setAppKey(gameData.getAppKey());
			game.setCallbackRecharge(gameData.getCallbackRecharge());
			game.setCreateTime(new Timestamp(System.currentTimeMillis()));
			game.setName(gameData.getName());
			game.setPackageName(gameData.getPackageName());

			game.setStatus(Integer.valueOf(1));
			game.setVersion(gameData.getVersion());
			game.setWeipaykey(gameData.getWeipaykey());

			game.setIfPush(gameData.getIfPush());
			// gameData.setSdkGameCp(cmp);
			// game.getSdkGamePayments().clear();
			this.systemService.saveOrUpdate(game);
			// 支付方式修改
			CriteriaQuery cQuery = new CriteriaQuery(SdkGamePayment.class);
			paymentInt = new Integer[payments.length];
			for (int i = 0; i < payments.length; i++) {
				paymentInt[i] = Integer.valueOf(payments[i]);
			}
			cQuery.in("sdkPayment.id", paymentInt);
			cQuery.add();
			cQuery.eq("sdkGame.gameId", game.getGameId());
			cQuery.add();

			List<SdkGamePayment> sdkGamePayments1 = new ArrayList<SdkGamePayment>(
					game.getSdkGamePayments());
			List<SdkGamePayment> sdkGamePayments2 = this.systemService
					.getListByCriteriaQuery(cQuery, Boolean.valueOf(false));
			Collection<SdkGamePayment> sdkGamePayments = CollectionUtils
					.disjunction(sdkGamePayments1, sdkGamePayments2);

			game.getSdkGamePayments().clear();
			this.systemService.deleteAllEntitie(sdkGamePayments);
			// sdkGamePayments.clear();
			this.systemService.saveOrUpdate(game);
			for (String selectPay : paymentList) {
				SdkGamePayment sdkGamePayment = getFilterGamePayment(
						sdkGamePayments1, selectPay);
				if (sdkGamePayment == null) {
					sdkGamePayment = new SdkGamePayment();
					sdkGamePayment.setSdkGame(game);
					SdkPayment payment = (SdkPayment) this.systemService
							.getEntity(SdkPayment.class,
									Integer.valueOf(selectPay));
					sdkGamePayment.setSdkPayment(payment);
					sdkGamePayment.setPayShow(Integer.valueOf(1));
					// gameData.getSdkGamePayments().add(sdkGamePayment);
					this.systemService.save(sdkGamePayment);
				}

			}

			this.message = ("游戏: " + gameData.getName() + "更新成功");
			this.systemService.addLog(this.message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			SdkGame tmp = (SdkGame) this.systemService.findUniqueByProperty(
					SdkGame.class, "name", gameData.getName());
			if (tmp != null) {
				this.message = ("游戏: " + gameData.getName() + "已经存在");
			} else {
				SdkGameCp cmp = (SdkGameCp) this.systemService
						.findUniqueByProperty(SdkGameCp.class, "cpId",
								Integer.valueOf(cp_id));
				gameData.setStatus(Integer.valueOf(1));
				gameData.setSdkGameCp(cmp);
				gameData.setCreateTime(new Timestamp(System.currentTimeMillis()));
				// gameData.setIfPush(Integer.parseInt(req.getParameter("ifPush")));
				this.systemService.save(gameData);
				SdkPushcode sdkPushcode = new SdkPushcode();
				sdkPushcode.setSdkGame(gameData);
				sdkPushcode.setPushAppKey(pushAppkey);
				sdkPushcode.setPushMasterSecret(pushMasterSecret);
				this.systemService.save(sdkPushcode);
				// 支付方式修改
				for (String selectPay : paymentList) {

					SdkGamePayment sdkGamePayment = new SdkGamePayment();
					sdkGamePayment.setSdkGame(gameData);
					SdkPayment payment = (SdkPayment) this.systemService
							.getEntity(SdkPayment.class,
									Integer.valueOf(selectPay));
					sdkGamePayment.setSdkPayment(payment);
					sdkGamePayment.setPayShow(Integer.valueOf(1));
					sdkGamePayment.setIdx(payment.getPayType());
					// gameData.getSdkGamePayments().add(sdkGamePayment);
					this.systemService.save(sdkGamePayment);
				}
				this.message = ("游戏: " + gameData.getName() + "添加成功");
				this.systemService.addLog(this.message,
						Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(this.message);
		return j;
	}

	private SdkGamePayment getFilterGamePayment(List<SdkGamePayment> list,
			String id) {
		for (SdkGamePayment sdkGamePayment : list) {
			if (String.valueOf(sdkGamePayment.getSdkPayment().getId()).equals(
					id)) {
				return sdkGamePayment;
			}
		}
		return null;
	}

	@RequestMapping(params = { "del" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson del(HttpServletRequest request, SdkGame gameData) {
		AjaxJson j = new AjaxJson();
		try {
			SdkGame info = (SdkGame) this.systemService.getEntity(
					SdkGame.class, gameData.getGameId());
			List<SdkGamePush> sdkGamePushs = new ArrayList<SdkGamePush>(
					info.getSdkGamePushs());
			List<SdkOrder> sdkOrders = new ArrayList<SdkOrder>(
					info.getSdkOrders());
			List<SdkWiipayPaycode> sdkWiipayPaycodes = new ArrayList<SdkWiipayPaycode>(
					info.getSdkWiipayPaycodes());
			List<SdkPushcode> sdkPushcodes = new ArrayList<SdkPushcode>(
					info.getSdkPushcodes());
			info.getSdkGamePushs().clear();
			info.getSdkOrders().clear();
			info.getSdkWiipayPaycodes().clear();
			info.getSdkPushcodes().clear();

			this.systemService.deleteAllEntitie(sdkGamePushs);
			this.systemService.deleteAllEntitie(sdkOrders);
			this.systemService.deleteAllEntitie(sdkWiipayPaycodes);
			this.systemService.deleteAllEntitie(sdkPushcodes);
			this.systemService.saveOrUpdate(info);
			this.systemService.delete(info);
			this.systemService.addLog("删除游戏: " + info.getName(),
					Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

			j.setMsg("成功删除!");
		} catch (Exception e) {
			j.setMsg("删除失败");
		}

		return j;
	}

	@RequestMapping(params = { "saveFiles" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();

		MultipartHttpServletRequest fq = (MultipartHttpServletRequest) request;
		Iterator<String> it = fq.getFileNames();

		String eid = String.valueOf(System.currentTimeMillis());
		String path = request.getRealPath("/upload");
		String base_path = ResourceUtil.getConfigByName("apk.basepath", path);
		String filePathString = base_path + "/public/apk/" + eid;
		File evalRoot = new File(filePathString);
		if (!evalRoot.exists()) {
			evalRoot.mkdirs();
		}

		while (it.hasNext()) {
			String name = (String) it.next();
			MultipartFile f = fq.getFile(name);
			File fto = new File(evalRoot, f.getOriginalFilename());
			try {
				FileOutputStream fos = new FileOutputStream(fto);
				fos.write(f.getBytes());
				fos.close();

				logger.info("Copied file to: " + fto.getAbsolutePath());
				filePathString = "/public/apk/" + eid + "/"
						+ f.getOriginalFilename();
			} catch (Exception e) {
				logger.error("" + e, e);
			}
		}

		j.setMsg("文件添加成功");
		attributes.put("apkfile", filePathString);
		j.setAttributes(attributes);

		return j;
	}

	private String getAppkey() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	private Integer getCpId() {
		HttpSession session = ContextHolderUtils.getSession();
		Integer cpId = (Integer) session.getAttribute("cpId");
		if (cpId == null) {
			TSUser user = ResourceUtil.getSessionUserName();
			if (StringUtils.isNumeric(user.getUserName())) {
				cpId = Integer.valueOf(user.getUserName());
				session.setAttribute("cpId", cpId);
			} else {
				SdkGameCp gameCp = (SdkGameCp) this.systemService
						.findUniqueByProperty(SdkGameCp.class, "loginName",
								user.getUserName());
				if (gameCp != null) {
					cpId = gameCp.getCpId();
					session.setAttribute("cpId", cpId);
				}
			}
		}

		if (cpId == null) {
			throw new CgReportNotFoundException("游戏商不存在: " + cpId);
		}

		return cpId;
	}
}
