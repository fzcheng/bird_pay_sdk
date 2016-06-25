package jeecg.system.controller.core;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
//import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jeecg.system.pojo.base.TSConfig;
import jeecg.system.pojo.base.TSFunction;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleFunction;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.pojo.base.TSVersion;
import jeecg.system.service.SysConfUtils;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.TransientObjectException;
import org.jeecgframework.core.common.model.common.SessionInfo;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 登陆初始化控制器
 * 
 * 
 */
@Controller
@RequestMapping({"/loginController"})
public class LoginController{
  static Log logger = LogFactory.getLog(LoginController.class);
  private SystemService systemService;
  private UserService userService;
  private String message = null;

  @Autowired
  public void setSystemService(SystemService systemService) {
    this.systemService = systemService;
  }

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }

  @RequestMapping(params={"goPwdInit"})
  public String goPwdInit() {
    return "login/pwd_init";
  }

	/**
	 * admin账户密码初始化
	 * 
	 * @param request
	 * @return
	 */
  @RequestMapping(params={"pwdInit"})
  public ModelAndView pwdInit(HttpServletRequest request){
    ModelAndView modelAndView = null;
	//TSUser user = new TSUser();
	//user.setUserName("admin");
	//String newPwd = "123456";
	//userService.pwdInit(user, newPwd);
    modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
    return modelAndView;
  }

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
  @RequestMapping(params={"checkuser"})
  @ResponseBody
  public AjaxJson checkuser(TSUser user, HttpServletRequest req){
    HttpSession session = ContextHolderUtils.getSession();
    DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
    AjaxJson j = new AjaxJson();
    TSUser u = this.userService.checkUserExits(user);
    if (u != null){
		// date:20130318-------for:注释掉U盾的校验
		// if (user.getUserKey().equals(u.getUserKey())) {
//		if (true) {
			// date:20130318-------for:注释掉U盾的校验
      this.message = ("用户: " + user.getUserName() + "登录成功");
      SessionInfo sessionInfo = new SessionInfo();
      sessionInfo.setUser(u);
      session.setMaxInactiveInterval(1800);
      session.setAttribute(Globals.USER_SESSION, sessionInfo);
      // 添加登陆日志
      this.systemService.addLog(this.message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);

//		} else {
//			j.setMsg("请检查U盾是否正确");
//			j.setSuccess(false);
//		}
    }else{
      j.setMsg("用户名或密码错误!");
      j.setSuccess(false);
    }
    return j;
  }

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params={"login"})
	public String login(HttpServletRequest request, HttpServletResponse response){
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
		TSUser user = ResourceUtil.getSessionUserName();
	    String roles = "";
	    if (user != null) {
	      List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
	      for (TSRoleUser ru : rUsers) {
	        TSRole role = ru.getTSRole();
	        roles += role.getRoleName() + ",";
	      }
	      if (roles.length() > 0) {
	        roles = roles.substring(0, roles.length() - 1);
	      }
	      request.setAttribute("roleName", roles);
	      request.setAttribute("userName", user.getRealName());
	
	      String userCookie = getUserCookieString(user);
	
	      setupUserCookie(response, userCookie);
	
	      SysConfUtils sysConfUtils = new SysConfUtils(this.systemService);
	      request.setAttribute("version", sysConfUtils.getVersion(((TSRoleUser)rUsers.get(0)).getTSRole().getId(), user.getId()));
	      TSFunction indexTab = null;
	      try {
	        indexTab = sysConfUtils.getIndexTab(((TSRoleUser)rUsers.get(0)).getTSRole().getId(), user.getId());
	      }catch (Exception e) {
	      }
	      if (indexTab == null){
	        request.setAttribute("tab", "webpage/main/home.jsp");
	        request.setAttribute("tabTitle", "首页");
	      }else {
	        String tabUrl = indexTab.getFunctionUrl();
	        String tabTitle = indexTab.getFunctionName();
	        request.setAttribute("tab", tabUrl);
	        request.setAttribute("tabTitle", tabTitle);
	      }
	
			
			
			// update-start--Author:周勇 Date:2013-07-29：登录后判断首页风格
			// 默认风格
	      String indexStyle = "default";
	
	      Cookie[] cookies = request.getCookies();
	      for (Cookie cookie : cookies) {
	        if ((cookie != null) && (!StringUtils.isEmpty(cookie.getName()))){
	          if (cookie.getName().equalsIgnoreCase("JEECGINDEXSTYLE")) {
	            indexStyle = cookie.getValue();
	          }
	        }
	      }
			// 要添加自己的风格，复制下面三行即可
	      if ((StringUtils.isNotEmpty(indexStyle)) && (indexStyle.equalsIgnoreCase("bootstrap"))) {
	        return "main/bootstrap_main";
	      }
			// update-start--Author:周勇 Date:2013-07-29：登录后判断首页风格
	      
	      return "main/main";
	    } else {
	    return "login/login";
	  }
    
}
  
  private String getUserCookieString(TSUser user){
    try{
      String r = "username=" + URLEncoder.encode(user.getUserName(), "utf-8");
      r += "&userid=" + user.getId();
      r+="&key="+DigestUtils.md5Hex(new StringBuilder(String.valueOf(user.getUserName())).append("&").append(user.getId()).append("&jeecgpwd20130901").toString());
	  return r;
    }catch (Exception e) {
      logger.error(e, e);
    }return null;
  }

  private void setupUserCookie(HttpServletResponse response, String cookieValue){
		//存储用户Cookie信息
    String cn = ResourceUtil.getConfigByName("jeecg.login.cookie.name", "JEECG-USER");
    String domain = ResourceUtil.getConfigByName("jeecg.login.cookie.domain", "127.0.0.1");
    Cookie cookie = new Cookie(cn, cookieValue);
    cookie.setMaxAge(-1);
    cookie.setDomain(domain);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

	/**
	 * 退出系统
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
  @RequestMapping(params={"logout"})
  public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
    ModelAndView modelAndView = null;

    HttpSession session = ContextHolderUtils.getSession();
    String versionCode = oConvertUtils.getString(request.getParameter("versionCode"));
    TSUser user = ResourceUtil.getSessionUserName();

	// 根据版本编码获取当前软件版本信息
    TSVersion version = (TSVersion)this.systemService.findUniqueByProperty(TSVersion.class, "versionCode", versionCode);

    List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
    for (TSRoleUser ru : rUsers) {
      TSRole role = ru.getTSRole();
      session.removeAttribute(role.getId());
    }
    
    try{
      this.systemService.addLog("用户: " + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
    } catch (TransientObjectException toe) {
      logger.error(toe.getMessage());
    }finally {
		// 判断用户是否为空不为空则清空session中的用户object
      session.removeAttribute(Globals.USER_SESSION);// 注销该操作用户
    }

    session.removeAttribute("cpId");

    setupUserCookie(response, null);

    modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));

    return modelAndView;
  }

	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
  @RequestMapping(params={"left"})
  public ModelAndView left(HttpServletRequest request){
    TSUser user = ResourceUtil.getSessionUserName();
    String roles = "";
    HttpSession session = ContextHolderUtils.getSession();
	// 登陆者的权限
    if (user.getId() == null) {
      session.removeAttribute(Globals.USER_SESSION);
      return new ModelAndView(new RedirectView("loginController.do?login"));
    }
    Set<TSFunction> loginActionlist = new HashSet<TSFunction>();// 已有权限菜单
    List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
    for (TSRoleUser ru : rUsers) {
    	TSRole role =  ru.getTSRole();
    	roles += role.getRoleName() + ",";
    	List<TSRoleFunction> roleFunctionList = ResourceUtil.getSessionTSRoleFunction(role.getId());
//      String functionReload = (String)request.getServletContext().getAttribute("functionReload");
/*    	if( functionReload == null) */String functionReload = "0";
      if ((functionReload.equals("1")) ||(roleFunctionList == null)) {
    	  
        session.setMaxInactiveInterval(1800);
        roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
        session.setAttribute(role.getId(), roleFunctionList);
      }else {
    	  if (((TSRoleFunction)roleFunctionList.get(0)).getId() == null) {
    		  roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
    	  }
      }
//    }
//    TSRole role;
//    Iterator localIterator2;
//    for (Iterator localIterator1 = rUsers.iterator(); localIterator1.hasNext(); 
//      localIterator2.hasNext())
//    {
//      TSRoleUser ru = (TSRoleUser)localIterator1.next();
//      role = ru.getTSRole();
//      roles = roles + role.getRoleName() + ",";
//      List roleFunctionList = ResourceUtil.getSessionTSRoleFunction(role.getId());
////      String functionReload = (String)request.getServletContext().getAttribute("functionReload");
//      String functionReload = "0";
////      if (functionReload == null) functionReload = "0";
//      if ((functionReload.equals("1")) || (roleFunctionList == null))
//      {
//        session.setMaxInactiveInterval(1800);
//        roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
//        session.setAttribute(role.getId(), roleFunctionList);
//      }
//      else if (((TSRoleFunction)roleFunctionList.get(0)).getId() == null) {
//        roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
//      }

    for (TSRoleFunction roleFunction : roleFunctionList) {
//      localIterator2 = roleFunctionList.iterator();
//      while(localIterator2.hasNext()){
//    	  TSRoleFunction roleFunction = (TSRoleFunction)localIterator2.next();
    	  TSFunction function = roleFunction.getTSFunction();
          if (function != null) {
            loginActionlist.add(function);
          }
      }
    }

//    request.getServletContext().setAttribute("functionReload", "0");

    Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
    if (loginActionlist.size() > 0) {
      for (TSFunction function : loginActionlist) {
        if (!functionMap.containsKey(Integer.valueOf(function.getFunctionLevel().shortValue() + 0))) {
          functionMap.put(Integer.valueOf(function.getFunctionLevel().shortValue() + 0), new ArrayList());
        }
        ((List)functionMap.get(Integer.valueOf(function.getFunctionLevel().shortValue() + 0))).add(function);
      }
    }
    // 菜单栏排序
    Collection<List<TSFunction>> c = functionMap.values();
    Iterator<List<TSFunction>> it = c.iterator();
    while (it.hasNext()) {
      Collections.sort((List)it.next(), new NumberComparator());
    }

    String logString = ListtoMenu.getEasyuiMultistageMenu(functionMap);

    request.setAttribute("loginMenu", logString);
	// request.setAttribute("parentFun", bigActionlist);
    request.setAttribute("roleName", roles);
    request.setAttribute("menuMap", functionMap);
    request.setAttribute("userName", user.getRealName());
	// request.setAttribute("childFun", smailActionlist);
    request.setAttribute("userName", user.getRealName());
    List<TSConfig> configs = this.userService.loadAll(TSConfig.class);
    for (TSConfig tsConfig : configs) {
      request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
    }
    return new ModelAndView("main/left");
  }

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params={"home"})
	public ModelAndView home(HttpServletRequest request){
		return new ModelAndView("main/home");
	}

	/**
	 * 无权限页面提示跳转
	 * 
	 * @return
	 */
	@RequestMapping(params={"noAuth"})
	public ModelAndView noAuth(HttpServletRequest request){
		return new ModelAndView("common/noAuth");
	}

	// update-start--Author:周勇 Date:2013-07-20：新增bootstrap头部菜单导航
	/**
	 * @Title: top
	 * @Description: bootstrap头部菜单请求
	 * @param request
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(params={"top"})
	public ModelAndView top(HttpServletRequest request){
    TSUser user = ResourceUtil.getSessionUserName();
    String roles = "";
    HttpSession session = ContextHolderUtils.getSession();
	// 登陆者的权限
    if (user.getId() == null) {
      session.removeAttribute(Globals.USER_SESSION);
      return new ModelAndView(new RedirectView("loginController.do?login"));
    }
    Set<TSFunction> loginActionlist = new HashSet<TSFunction>();// 已有权限菜单
    List<TSRoleUser> rUsers = this.systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
    for (TSRoleUser ru : rUsers) {
    	TSRole role = ru.getTSRole();
    	roles += role.getRoleName() + ",";
		List<TSRoleFunction> roleFunctionList = ResourceUtil.getSessionTSRoleFunction(role.getId());
		if (roleFunctionList == null) {
			session.setMaxInactiveInterval(60 * 30);
			roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
			session.setAttribute(role.getId(), roleFunctionList);
		} else {
			if (roleFunctionList.get(0).getId() == null) {
				roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
			}
		}
		for (TSRoleFunction roleFunction : roleFunctionList) {
			TSFunction function = roleFunction.getTSFunction();
			loginActionlist.add(function);
		}
    }
//    TSRole role;
//    Iterator localIterator2;
//    TSFunction function;
//    for (Iterator localIterator1 = rUsers.iterator(); localIterator1.hasNext(); 
//      localIterator2.hasNext())
//    {
//      TSRoleUser ru = (TSRoleUser)localIterator1.next();
//      role = ru.getTSRole();
//      roles = roles + role.getRoleName() + ",";
//      List roleFunctionList = ResourceUtil.getSessionTSRoleFunction(role.getId());
//      if (roleFunctionList == null) {
//        session.setMaxInactiveInterval(1800);
//        roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
//        session.setAttribute(role.getId(), roleFunctionList);
//      }
//      else if (((TSRoleFunction)roleFunctionList.get(0)).getId() == null) {
//        roleFunctionList = this.systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
//      }
//
//      localIterator2 = roleFunctionList.iterator();
//      while(localIterator2.hasNext()){
//    	  TSRoleFunction roleFunction = (TSRoleFunction)localIterator2.next();
//    	  function = roleFunction.getTSFunction();
//          if (function != null) {
//            loginActionlist.add(function);
//          }
//      }
//    }

    Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
    if (loginActionlist.size() > 0) {
      for (TSFunction tSFunction : loginActionlist) {
        if (!functionMap.containsKey(Integer.valueOf(tSFunction.getFunctionLevel().shortValue() + 0))) {
          functionMap.put(Integer.valueOf(tSFunction.getFunctionLevel().shortValue() + 0), new ArrayList());
        }
        ((List)functionMap.get(Integer.valueOf(tSFunction.getFunctionLevel().shortValue() + 0))).add(tSFunction);
      }
    }
	// 菜单栏排序
    Collection<List<TSFunction>> c = functionMap.values();
    Iterator<List<TSFunction>> it = c.iterator();
    while (it.hasNext()) {
      Collections.sort((List)it.next(), new NumberComparator());
    }

    String logString = ListtoMenu.getBootstrapMenu(functionMap);

    request.setAttribute("loginMenu", logString);
	// request.setAttribute("parentFun", bigActionlist);
    request.setAttribute("roleName", roles);
    request.setAttribute("menuMap", functionMap);
    request.setAttribute("userName", user.getRealName());
	// request.setAttribute("childFun", smailActionlist);
    request.setAttribute("userName", user.getRealName());
    List<TSConfig> configs = this.userService.loadAll(TSConfig.class);
    for (TSConfig tsConfig : configs) {
      request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
    }
    return new ModelAndView("main/bootstrap_top");
  }
}