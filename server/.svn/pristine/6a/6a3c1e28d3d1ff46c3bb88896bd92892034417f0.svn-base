/**
 * 
 */
package jeecg.ext.sdk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkChannel;
import jeecg.ext.sdk.entity.SdkChannelPartner;
import jeecg.ext.sdk.entity.SdkGameChannel;
import jeecg.system.service.SystemService;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
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
@RequestMapping("/cnl")
public class ChannelController extends BaseController {
  @Autowired
  private SystemService systemService;
  /**
   * 
   */
  public ChannelController() {
    // TODO Auto-generated constructor stub
  }

  @RequestMapping(params = "list")
  public ModelAndView list() {
    List<SdkChannelPartner> partners = systemService.getList(SdkChannelPartner.class);
    
    ModelAndView mv = new ModelAndView("channelPartner/channellist");
    if (partners != null && partners.size() > 0) {
      StringBuilder replace = new StringBuilder();
      for (SdkChannelPartner partner : partners) {
        replace.append(partner.getPartnerName()).append("_").append(partner.getId()).append(",");
      }
      mv.addObject("replace", replace.toString());
    } else {
      mv.addObject("replace", "0_0");
    }
    
    return mv;
  }
  
  @RequestMapping(params = "data")
  public void getList(SdkChannel channel, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    CriteriaQuery cq = new CriteriaQuery(SdkChannel.class, dataGrid);
    cq.addOrder("id", SortDirection.desc);
    cq.add();
    HqlGenerateUtil.installHql(cq, channel);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params = "edit")
  public ModelAndView editChannel(Integer id) {
    ModelAndView mv = new ModelAndView("channelPartner/channeledit");
    if (id != null) {
      SdkChannel channel = systemService.getEntity(SdkChannel.class, id);
      mv.addObject("channel", channel);
    }
    List<SdkChannelPartner> partners = systemService.getList(SdkChannelPartner.class);
    mv.addObject("partners", partners);
    return mv;
  }
  
  @RequestMapping(params = "validChannelNum")
  public @ResponseBody ValidForm validChannelNum(Integer id, String name, String param) {
    SdkChannel cnlNumEntity = systemService.findUniqueByProperty(SdkChannel.class, "channelNum", param);
    ValidForm valid = new ValidForm();
    if (cnlNumEntity != null) {
      if (id == null || !id.equals(cnlNumEntity.getId())) {
        valid.setInfo("渠道编号已存在！");
        valid.setStatus("n");
      } else {
        valid.setInfo("");
        valid.setStatus("y");
      }
    } else {
      valid.setInfo("");
      valid.setStatus("y");
    }
    return valid;
  }
  
  @RequestMapping(params = "save")
  public @ResponseBody AjaxJson saveChannel(SdkChannel channel) {
    AjaxJson json = new AjaxJson();
    SdkChannel entity = systemService.findUniqueByProperty(SdkChannel.class, "channelNum", channel.getChannelNum());
    if (entity != null) {
      if (channel.getId() == null || !channel.getId().equals(entity.getId())) {
        json.setMsg("操作失败，渠道编号已存在！");
        json.setSuccess(false);
        return json;
      }
    }
    
    if (channel.getId() != null) {
      entity = systemService.getEntity(SdkChannel.class, channel.getId());
      entity.setChannelName(channel.getChannelName());
      entity.setChannelNum(channel.getChannelNum());
      entity.setMemo(channel.getMemo());
      entity.setPid(channel.getPid());
    } else {
      entity = channel;
    }
    
    systemService.saveOrUpdate(entity);
    json.setMsg("操作成功");
    return json;
  }
  
  @RequestMapping(params = "del")
  public @ResponseBody AjaxJson delChannel(Integer id) {
    AjaxJson json = new AjaxJson();
    if (id == null) {
      json.setMsg("渠道不存在！");
      json.setSuccess(false);
      return json;
    }
    
    SdkChannel channel = systemService.getEntity(SdkChannel.class, id);
    if (channel == null) {
      json.setMsg("渠道不存在！");
      json.setSuccess(false);
      return json;
    }
    
    CriteriaQuery cq = new CriteriaQuery(SdkGameChannel.class);
    cq.eq("channelId", channel.getId());
    cq.add();
    List<SdkGameChannel> list = systemService.getListByCriteriaQuery(cq, true);
    if (list != null && list.size() > 0) {
      json.setMsg("此渠道已投放游戏，请确认再删除！");
      json.setSuccess(false);
      return json;
    }
    
    systemService.delete(channel);
    
    json.setMsg("删除渠道成功！");
    return json;
  }
}
