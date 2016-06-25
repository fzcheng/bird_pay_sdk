/**
 * 
 */
package jeecg.ext.sdk.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkOperatorPayment;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
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
@RequestMapping("/operatorpayment")
public class OperatorPaymentController extends BaseController {
  private static final Logger logger = Logger.getLogger(OperatorPaymentController.class);
  
  @Autowired
  private SystemService systemService;
  
  @RequestMapping(params = "list")
  public ModelAndView list() {
    logger.debug("get the operator payment list page");
    return new ModelAndView("operatorpayment/list");
  }
  
  @RequestMapping(params = "datagrid")
  public void getList(SdkOperatorPayment payment, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    logger.debug("get the operator payment list datagrid");
    CriteriaQuery cq = new CriteriaQuery(SdkOperatorPayment.class, dataGrid);
    HqlGenerateUtil.installHql(cq, payment);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params = "edit")
  public ModelAndView edit(Integer id) {
    logger.debug("get the operator payment edit page");
    ModelAndView mv = new ModelAndView("operatorpayment/edit");
    SdkOperatorPayment entity;
    if (id != null) {
      entity = systemService.getEntity(SdkOperatorPayment.class, id);
    } else {
      entity = null;
    }
    mv.addObject("payment", entity);
    return mv;
  }
  
  @RequestMapping(params = "valid")
  public @ResponseBody ValidForm valid(Integer id, String name, String param) {
    logger.debug("the operator payment valid, id = " + id + ", name = " + name + ", param = " + param);
    ValidForm valid = new ValidForm();
    valid.setInfo("");
    if ("type".equals(name) && StringUtils.isNotBlank(param)) {
      SdkOperatorPayment payment = systemService.findUniqueByProperty(SdkOperatorPayment.class, name, Integer.valueOf(param));
      if (id == null) {
        if (payment != null) {
          valid.setInfo("支付类型已存在！");
          valid.setStatus("n");
        }
      } else {
        if (payment != null && !id.equals(payment.getId())) {
          valid.setInfo("支付类型已存在！");
          valid.setStatus("n");
        }
      }
    }
    return valid;
  }
  
  @RequestMapping(params = "save")
  public @ResponseBody AjaxJson save(SdkOperatorPayment payment) {
    AjaxJson json = new AjaxJson();
    
    ValidForm valid = valid(payment.getId(), "type", String.valueOf(payment.getType()));
    if ("n".equals(valid.getStatus())) {
      json.setMsg(valid.getInfo());
      json.setSuccess(false);
      return json;
    }
    
    Date time = new Date();
    SdkOperatorPayment entity;
    if (payment.getId() != null) {
      entity = systemService.getEntity(SdkOperatorPayment.class, payment.getId());
      entity.setDayLimit(payment.getDayLimit());
      entity.setMonthLimit(payment.getMonthLimit());
      entity.setName(payment.getName());
      entity.setType(payment.getType());
    } else {
      entity = payment;
      entity.setCreatedTime(time);
    }
    
    entity.setUpdatedTime(time);
    systemService.saveOrUpdate(entity);
    
    return json;
  }
  
  @RequestMapping(params = "del")
  public @ResponseBody AjaxJson del(Integer id) {
    AjaxJson json = new AjaxJson();
    try {
      SdkOperatorPayment payment = this.systemService.getEntity(SdkOperatorPayment.class, id);
      this.systemService.delete(payment);
      json.setMsg("删除成功");
    } catch (Exception e) {
      json.setMsg("无法删除");
    }
    return json;
  }
}
