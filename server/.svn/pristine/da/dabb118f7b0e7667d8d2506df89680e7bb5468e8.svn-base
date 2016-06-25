package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkGameCp;
import jeecg.ext.tools.TagUtilExt;
import jeecg.system.pojo.base.TSDepart;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gameCmp")
public class GameCompanyController extends BaseController {
  private static final Logger logger = Logger.getLogger(GameCompanyController.class);
  @Autowired
  private UserService userService;

  @Autowired
  private SystemService systemService;

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @RequestMapping(params = "list")
  public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
    // return new ModelAndView("gameInfo/gameinfo_list");
    return new ModelAndView("gamecmp/list");
  }

  @RequestMapping(params = "datagrid")
  public void datagrid(SdkGameCp gameCmp, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    CriteriaQuery cq = new CriteriaQuery(SdkGameCp.class, dataGrid);
    // 查询条件组装器
//    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, gameCmp);
    String name = request.getParameter("name");
	String loginName = request.getParameter("loginName");
	String email = request.getParameter("email");
    if (name != null) {
		cq.add(Restrictions.like("name", "%" + name.trim() + "%"));
	}
	if (loginName != null) {
		if(StringUtils.isNotBlank(loginName)){
			cq.add(Restrictions.like("loginName", "%" + loginName.trim() + "%"));	
    	}
	}
	if (email != null) {
		if(StringUtils.isNotBlank(email)){
			cq.add(Restrictions.like("email", "%" + email.trim() + "%"));
		}
	}
    this.systemService.getDataGridReturn(cq, true);
    TagUtilExt.datagrid(response, dataGrid, "cpId");
  }

  /**
   * 列表页面跳转
   * 
   * @return
   */
  @RequestMapping(params = "addorupdate")
  public ModelAndView addorupdate(String id, HttpServletRequest req) {
    String apiKey = getAppkey();
    if (StringUtil.isNotEmpty(id)) {
      SdkGameCp gameCmp = systemService.getEntity(SdkGameCp.class, Integer.valueOf(id));
      req.setAttribute("gameCmp", gameCmp);
      apiKey = gameCmp.getApiKey();
    }
    req.setAttribute("apiKey", apiKey);
    return new ModelAndView("gamecmp/add");
  }

  @RequestMapping(params = "valid")
  public @ResponseBody
  ValidForm valid(Integer id, String name, String param) {
    logger.debug("id = " + id + ", name = " + name + ", param = " + param);
    boolean isExisted = false;
    if ("name".equals(name)) {
      SdkGameCp gameCp = systemService.findUniqueByProperty(SdkGameCp.class, "name", param);
      if (gameCp != null) {
        if (id == null) {
          isExisted = true;
        } else {
          if (!id.equals(gameCp.getCpId())) {
            isExisted = true;
          }
        }
      }
    } else if ("loginName".equals(name)) {
      SdkGameCp gameCp = systemService.findUniqueByProperty(SdkGameCp.class, "loginName", param);
      if (id == null || gameCp == null) {
        TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName", param);
        if (user != null) {
          isExisted = true;
        }
      } else if (!id.equals(gameCp.getCpId())) {
        isExisted = true;
      }
    }

    ValidForm valid = new ValidForm();
    if (isExisted) {
      valid.setInfo("登录账号已存在！");
      valid.setStatus("n");
    } else {
      valid.setInfo("");
      valid.setStatus("y");
    }
    return valid;
  }

  @RequestMapping(params = "save")
  @ResponseBody
  public AjaxJson save(SdkGameCp gameCmp) {
    AjaxJson json = new AjaxJson();

    ValidForm valid = valid(gameCmp.getCpId(), "name", gameCmp.getName());
    if ("n".equals(valid.getStatus())) {
      json.setMsg("操作失败，游戏商已存在！");
      json.setSuccess(false);
      return json;
    }
    
    valid = valid(gameCmp.getCpId(), "loginName", gameCmp.getLoginName());
    if ("n".equals(valid.getStatus())) {
      json.setMsg("操作失败，登录账号已存在！");
      json.setSuccess(false);
      return json;
    }

    SdkGameCp entity;
    if (gameCmp.getCpId() == null) {
      entity = gameCmp;
      String passwd = PasswordUtil.encrypt(entity.getLoginName(), entity.getPwd(), PasswordUtil.getStaticSalt());
      entity.setPwd(passwd);
      entity.setCreateTime(new Date());
      systemService.save(gameCmp);
      createUser(entity.getLoginName(), passwd, entity.getName());
    } else {
      entity = systemService.getEntity(SdkGameCp.class, gameCmp.getCpId());
      boolean isUpdateUser = false;
      if (entity != null) {
        entity.setEmail(gameCmp.getEmail());
        entity.setName(gameCmp.getName());
        if (!gameCmp.getLoginName().equals(entity.getLoginName())) {
          isUpdateUser = true;
          entity.setLoginName(gameCmp.getLoginName());
        }
        if (StringUtils.isNotBlank(gameCmp.getPwd())) {
          if (!gameCmp.getPwd().equals(entity.getPwd())) {
            String passwd = PasswordUtil.encrypt(entity.getLoginName(), gameCmp.getPwd(), PasswordUtil.getStaticSalt());
            entity.setPwd(passwd);
            isUpdateUser = true;
          }
        }
        
        TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName", entity.getLoginName());
        if (user == null) {
          createUser(entity.getLoginName(), entity.getPwd(), entity.getName());
        } else if (isUpdateUser) {
          user.setUserName(entity.getLoginName());
          user.setPassword(entity.getPwd());
          systemService.saveOrUpdate(user);
        }
        
        json.setMsg("修改成功");
      }
    }
    
    if (entity == null) {
      json.setMsg("操作失败");
      json.setSuccess(false);
      return json;
    }
    
    systemService.saveOrUpdate(entity);
    return json;
  }
  
  private void createUser(String userName, String passwd, String realName) {
    TSUser user=new TSUser();
    TSDepart tsDepart=this.systemService.findUniqueByProperty(TSDepart.class, "departname", "信息部");
    user.setTSDepart(tsDepart);
    user.setRealName(realName);
    user.setUserName(userName);
    user.setPassword(passwd);
    user.setStatus(Globals.User_Normal);
    systemService.save(user);
    
    String cp_roleCode = ResourceUtil.getConfigByName("cp_roleCode", "cp");
    TSRole role = this.systemService.findUniqueByProperty(TSRole.class, "roleCode", cp_roleCode);
    if (StringUtil.isNotEmpty(role.getId())) {
      saveRoleUser(user, role.getId());
    }
    
    message = "游戏商: " + realName + "添加成功";
    systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
  }

  protected void saveRoleUser(TSUser user, String roleidstr) {
    String[] roleids = roleidstr.split(",");
    for (int i = 0; i < roleids.length; i++) {
      TSRoleUser rUser = new TSRoleUser();
      TSRole role = systemService.getEntity(TSRole.class, roleids[i]);
      rUser.setTSRole(role);
      rUser.setTSUser(user);
      systemService.save(rUser);

    }
  }

  @RequestMapping(params = "del", method = RequestMethod.POST)
  @ResponseBody
  public AjaxJson del(HttpServletRequest request, String id) {
    AjaxJson j = new AjaxJson();
    SdkGameCp gameCmp = this.systemService.getEntity(SdkGameCp.class, Integer.valueOf(id));
    if (gameCmp != null) {
      systemService.delete(gameCmp);
      // 删除cp账号
      TSUser user = this.systemService.findUniqueByProperty(TSUser.class, "userName", gameCmp.getLoginName());
      if (user != null) {
        List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        if (!user.getStatus().equals(Globals.User_ADMIN)) {
          if (roleUser.size() > 0) {
            // 删除用户时先删除用户和角色关系表
            delRoleUser(user);
            userService.delete(user);
            message = "用户：" + user.getUserName() + "删除成功";
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
          } else {
            userService.delete(user);
            message = "用户：" + user.getUserName() + "删除成功";
          }

        }
      }
      systemService.addLog("删除游戏商: " + gameCmp.getName(), Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

      j.setMsg("成功删除!");
    } else {

      systemService.addLog("删除不存在的游戏商: ", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

      j.setMsg("删除不存在的游戏商");
    }

    return j;
  }

  public void delRoleUser(TSUser user) {
    // 同步删除用户角色关联表
    List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
    if (roleUserList.size() >= 1) {
      for (TSRoleUser tRoleUser : roleUserList) {
        systemService.delete(tRoleUser);
      }
    }
  }

  private String getAppkey() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString().replace("-", "");
  }

}
