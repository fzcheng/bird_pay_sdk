/**
 * 
 */
package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkPayment;
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
@RequestMapping("/payment")
public class PaymentController extends BaseController {
  private static final Logger logger = Logger.getLogger(PaymentController.class);
  @Autowired
  private SystemService systemService;

  @RequestMapping(params = "list")
  public ModelAndView list() {
    logger.debug("get the payment list page");
    ModelAndView mv = new ModelAndView("payment/list");
    return mv;
  }
  
  @RequestMapping(params = "datagrid")
  public void getList(SdkPayment payment, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    logger.debug("get the payment list datagrid");
    CriteriaQuery cq = new CriteriaQuery(SdkPayment.class, dataGrid);
    HqlGenerateUtil.installHql(cq, payment);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
  
  @RequestMapping(params = "edit")
  public ModelAndView edit(Integer id) {
    logger.debug("get the payment edit page");
    ModelAndView mv = new ModelAndView("payment/edit");
    SdkPayment entity;
    if (id != null) {
      entity = systemService.getEntity(SdkPayment.class, id);
    } else {
      entity = null;
    }
    mv.addObject("payment", entity);
    return mv;
  }
  
  @RequestMapping(params = "valid")
  public @ResponseBody ValidForm valid(Integer id, String name, String param) {
    logger.debug("the payment valid, id = " + id + ", name = " + name + ", param = " + param);
    ValidForm valid = new ValidForm();
    valid.setInfo("");
    if ("payName".equals(name) && StringUtils.isNotBlank(param)) {
      SdkPayment payment = systemService.findUniqueByProperty(SdkPayment.class, name, param);
      if (id == null) {
        if (payment != null) {
          valid.setInfo("支付方式名已存在！");
          valid.setStatus("n");
        }
      } else {
        if (payment != null && !id.equals(payment.getId())) {
          valid.setInfo("支付方式名已存在！");
          valid.setStatus("n");
        }
      }
    } else if ("payType".equals(name)) {
      SdkPayment payment = systemService.findUniqueByProperty(SdkPayment.class, name, Integer.valueOf(param));
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
  public @ResponseBody AjaxJson save(SdkPayment payment) {
    AjaxJson json = new AjaxJson();
    
    ValidForm valid = valid(payment.getId(), "payName", payment.getPayName());
    if ("n".equals(valid.getStatus())) {
      json.setMsg("操作失败，计费方式名已存在！");
      json.setSuccess(false);
      return json;
    }
    
    valid = valid(payment.getId(), "payType", String.valueOf(payment.getPayType()));
    if ("n".equals(valid.getStatus())) {
      json.setMsg("操作失败，计费类型已存在！");
      json.setSuccess(false);
      return json;
    }
    SdkPayment entity;
    if (payment.getId() != null) {
      entity = systemService.getEntity(SdkPayment.class, payment.getId());
      entity.setPayCnName(payment.getPayCnName());
      entity.setPayName(payment.getPayName());
      entity.setPayType(payment.getPayType());
    } else {
      entity = payment;
    }
    
    systemService.saveOrUpdate(entity);
    
    return json;
  }
  
  @RequestMapping(params = "del")
  public @ResponseBody AjaxJson del(Integer id) {
    AjaxJson json = new AjaxJson();
    try {
      SdkPayment payment = this.systemService.getEntity(SdkPayment.class, id);
      this.systemService.delete(payment);
      json.setMsg("删除成功");
    } catch (Exception e) {
      json.setMsg("无法删除");
    }
    return json;
  }
}
