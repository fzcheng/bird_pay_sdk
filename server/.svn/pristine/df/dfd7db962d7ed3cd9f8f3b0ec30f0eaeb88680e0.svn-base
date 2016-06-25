package jeecg.ext.sdk.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannel;
import jeecg.ext.sdk.entity.SdkChannelPartner;
import jeecg.system.pojo.base.TSDepart;
import jeecg.system.pojo.base.TSRole;
import jeecg.system.pojo.base.TSRoleUser;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;
import jeecg.system.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/channelPartner")
public class ChannelPartnerController extends BaseController {
  private static final Logger logger = Logger.getLogger(ChannelPartnerController.class);

  @Autowired
  private SystemService systemService;

  @Autowired
  private UserService userService;

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @RequestMapping(params = "list")
  public ModelAndView list() {
    return new ModelAndView("channelPartner/list");
  }

  @RequestMapping(params = "data")
  public void getList(SdkChannelPartner sdkChannelPartner, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    CriteriaQuery cq = new CriteriaQuery(SdkChannelPartner.class, dataGrid);
    cq.addOrder("createTime", SortDirection.desc);
    cq.add();
    HqlGenerateUtil.installHql(cq, sdkChannelPartner);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params = "add")
  public ModelAndView addChannelPartner(SdkChannelPartner sdkChannelPartner, HttpServletRequest request) {
    // 添加，列出所有未被使用的渠道 pid=0
    CriteriaQuery cq = new CriteriaQuery(SdkChannel.class);
    cq.eq("pid", 0);
    cq.add();
    List<SdkChannel> sdkChannels = this.systemService.getListByCriteriaQuery(cq, false);
    request.setAttribute("sdkChannels", sdkChannels);
    return new ModelAndView("channelPartner/add");
  }

  @RequestMapping(params = "update")
  public ModelAndView updateChannelPartner(SdkChannelPartner sdkChannelPartner, HttpServletRequest request) {
    // 更新，列出所有未被使用的渠道 pid=0和当前
    if (StringUtil.isNotEmpty(sdkChannelPartner.getId())) {
      SdkChannelPartner tmp = this.systemService.getEntity(SdkChannelPartner.class, sdkChannelPartner.getId());
      if (tmp != null) {
        CriteriaQuery cq = new CriteriaQuery(SdkChannel.class);
        cq.add(Restrictions.or(Restrictions.eq("pid", 0), Restrictions.eq("pid", tmp.getId())));
        cq.add();
        List<SdkChannel> sdkChannels = this.systemService.getListByCriteriaQuery(cq, false);
        request.setAttribute("tmp", tmp);
        request.setAttribute("sdkChannels", sdkChannels);
        request.setAttribute("id", sdkChannelPartner.getId());
        request.setAttribute("action", "update");
      }

    }
    return new ModelAndView("channelPartner/add");
  }
  
  @RequestMapping(params = "valid")
  public @ResponseBody ValidForm valid(Integer id, String name, String param) {
    logger.debug("id = " + id + ", name = " + name + ", param = " + param);
    boolean isExisted = false;
    SdkChannelPartner partner = systemService.findUniqueByProperty(SdkChannelPartner.class, "loginName", param);
    if (id == null || partner == null) {
      TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName", param);
      if (user != null) {
        isExisted = true;
      }
    } else if (!id.equals(partner.getId())) {
      isExisted = true;
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
  public AjaxJson saveChannelPartner(SdkChannelPartner partner, String[] channelIds) {
    logger.debug("partner = " + partner + ", channelIds = " + channelIds);
    AjaxJson json = new AjaxJson();
    ValidForm valid = valid(partner.getId(), "loginName", partner.getLoginName());
    if ("n".equals(valid.getStatus())) {
      json.setMsg("操作失败，登录账号已存在！");
      json.setSuccess(false);
      return json;
    }
    
    Date time = new Date();
    SdkChannelPartner entity;
    if (partner.getId() == null) {
      String passwd = PasswordUtil.encrypt(partner.getLoginName(), partner.getLoginPasswd(), PasswordUtil.getStaticSalt());
      partner.setCreateTime(time);
      partner.setUpdateTime(time);
      partner.setLoginPasswd(passwd);
      entity = partner;
      
      createUser(partner.getLoginName(), passwd, partner.getPartnerName());
      
      json.setMsg("添加成功");
    } else {
      entity = systemService.get(SdkChannelPartner.class, partner.getId());
      if (entity != null) {
        entity.setPartnerName(partner.getPartnerName());
        entity.setUpdateTime(time);
        
        boolean isUpdateUser = false;
        TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName", entity.getLoginName());
        if (!partner.getLoginName().equals(entity.getLoginName())) {
          isUpdateUser = true;
          entity.setLoginName(partner.getLoginName());
        }
        if (StringUtils.isNotBlank(partner.getLoginPasswd())) {
          String passwd = PasswordUtil.encrypt(entity.getLoginName(), partner.getLoginPasswd(), PasswordUtil.getStaticSalt());
          if (!passwd.equals(entity.getLoginPasswd())) {
            entity.setLoginPasswd(passwd);
            isUpdateUser = true;
          }
        }
        if (user == null) {
          createUser(entity.getLoginName(), entity.getLoginPasswd(), entity.getPartnerName());
        } else if (isUpdateUser) {
          user.setUserName(entity.getLoginName());
          user.setPassword(entity.getLoginPasswd());
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
    if (channelIds != null) {
      for (String channelId : channelIds) {
        SdkChannel channel = this.systemService.getEntity(SdkChannel.class, oConvertUtils.getInt(channelId));
        if (channel != null) {
          channel.setPid(entity.getId());
          systemService.saveOrUpdate(channel);
        }
      }
    }

    return json;
  }

  @RequestMapping(params = "detail")
  public ModelAndView detailChannelPartner(SdkChannelPartner sdkChannelPartner, HttpServletRequest request) {
    // 更新，列出所有未被使用的渠道 pid=0和当前
    if (StringUtil.isNotEmpty(sdkChannelPartner.getId())) {
      SdkChannelPartner tmp = this.systemService.getEntity(SdkChannelPartner.class, sdkChannelPartner.getId());
      if (tmp != null) {
        CriteriaQuery cq = new CriteriaQuery(SdkChannel.class);
        cq.eq("pid", tmp.getId());
        cq.add();
        List<SdkChannel> sdkChannels = this.systemService.getListByCriteriaQuery(cq, false);
        request.setAttribute("tmp", tmp);
        request.setAttribute("sdkChannels", sdkChannels);
        request.setAttribute("id", sdkChannelPartner.getId());
      }

    }
    return new ModelAndView("channelPartner/add");
  }

  @RequestMapping(params = "del", method = RequestMethod.POST)
  @ResponseBody
  public AjaxJson del(Integer id) {
    AjaxJson j = new AjaxJson();
    if (id == null) {
      j.setMsg("请选择渠道商删除！");
      j.setSuccess(false);
      return j;
    }
    
    SdkChannelPartner partner = systemService.getEntity(SdkChannelPartner.class, id);
    List<SdkChannel> sdkChannelList = systemService.findByProperty(SdkChannel.class, "pid", partner.getId());
    if (sdkChannelList != null && sdkChannelList.size() > 0) {
      for (SdkChannel sdkChannel : sdkChannelList) {
        sdkChannel.setPid(0);
        systemService.saveOrUpdate(sdkChannel);
      }
    }
    
    systemService.delete(partner);
    
    //String channel_roleCode = ResourceUtil.getConfigByName("channelPartner", "channelP");
    //TSUser user = this.systemService.findUniqueByProperty(TSUser.class, "userName", channel_roleCode + oConvertUtils.getString(partner.getId()));
    TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName", partner.getLoginName());
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
    j.setMsg("删除成功！");
    
    return j;
  }

  @RequestMapping(params = "dataList")
  public ModelAndView dataList() {
    return new ModelAndView("channelPartner/dataList");
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
    
    String channel_roleCode=ResourceUtil.getConfigByName("channelPartner", "channelP");
    TSRole role=this.systemService.findUniqueByProperty(TSRole.class, "roleCode", channel_roleCode);
    if (StringUtil.isNotEmpty(role.getId())) {
      saveRoleUser(user, role.getId());
    }
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
  
  public void delRoleUser(TSUser user) {
    // 同步删除用户角色关联表
    List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
    if (roleUserList.size() >= 1) {
      for (TSRoleUser tRoleUser : roleUserList) {
        systemService.delete(tRoleUser);
      }
    }
  }
}
