/**
 * 
 */
package jeecg.ext.sdk.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkOperatorPayment;
import jeecg.ext.sdk.entity.SdkPayPassage;
import jeecg.ext.sdk.entity.SdkPayPassageParam;
import jeecg.ext.sdk.entity.SdkProvinceAddr;
import jeecg.system.service.SystemService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
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
@RequestMapping("/optpaypassage")
public class OperatorPayPassageController extends BaseController {
  private static final Logger logger = Logger.getLogger(OperatorPayPassageController.class);
  private static final ObjectMapper mapper = new ObjectMapper();
  static {
    //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
  
  @Autowired
  private SystemService systemService;

  @RequestMapping(params = "list")
  public ModelAndView list() {
    logger.debug("get the pay passage list page");
    ModelAndView mv = new ModelAndView("optpaypassage/list");
    
    List<SdkProvinceAddr> provinces = systemService.getList(SdkProvinceAddr.class);
    StringBuffer buff = new StringBuffer("全网_,");
    if (provinces != null) {
      for (SdkProvinceAddr province : provinces) {
        buff.append(province.getProvincenm()).append("_").append(province.getId()).append(",");
      }
    }
    mv.addObject("provinceReplaces", buff.toString());
    
    return mv;
  }

  @RequestMapping(params = "datagrid")
  public void getList(SdkPayPassage passage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    logger.debug("get the pay passage list datagrid");
    CriteriaQuery cq = new CriteriaQuery(SdkPayPassage.class, dataGrid);
    HqlGenerateUtil.installHql(cq, passage);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  @RequestMapping(params = "edit")
  public ModelAndView edit(Integer id) {
    logger.debug("get the pay passage edit page");
    ModelAndView mv = new ModelAndView("optpaypassage/edit");

    List<SdkOperatorPayment> payments = systemService.getList(SdkOperatorPayment.class);
    mv.addObject("payments", payments);

    List<SdkProvinceAddr> provinces = systemService.getList(SdkProvinceAddr.class);
    mv.addObject("provinces", provinces);

    SdkPayPassage entity;
    if (id != null) {
      entity = systemService.getEntity(SdkPayPassage.class, id);
    } else {
      entity = null;
    }
    mv.addObject("passage", entity);
    return mv;
  }

  @RequestMapping(params = "params")
  public @ResponseBody
  List<SdkPayPassageParam> getPayPassageParams(Integer payPassageId) {
    logger.debug("get the pay passage params list datagrid");
    List<SdkPayPassageParam> params;
    if (payPassageId != null) {
      params = systemService.findHql("select new SdkPayPassageParam(p.id, p.name, p.param, p.val) from SdkPayPassageParam p where p.sdkPayPassage.id=?", payPassageId);
    } else {
      params = new ArrayList<SdkPayPassageParam>(0);
    }
    return params;
  }

  @RequestMapping(params = "valid")
  public @ResponseBody
  ValidForm valid(Integer id, String name, String param) {
    logger.debug("the pay passage edit valid, id = " + id + ", name = " + name + ", param = " + param);
    ValidForm valid = new ValidForm();
    valid.setInfo("");
    if ("code".equals(name) && StringUtils.isNotBlank(param)) {
      SdkPayPassage passage = systemService.findUniqueByProperty(SdkPayPassage.class, name, param);
      if (id == null) {
        if (passage != null) {
          valid.setInfo("计费通道编号已存在！");
          valid.setStatus("n");
        }
      } else {
        if (passage != null && !id.equals(passage.getId())) {
          valid.setInfo("计费通道编号已存在！");
          valid.setStatus("n");
        }
      }
    }
    return valid;
  }

  @RequestMapping(params = "save")
  public @ResponseBody
  AjaxJson save(SdkPayPassage passage, String insertParams, String updateParams, String deleteParams) throws Exception {
    AjaxJson json = new AjaxJson();

    ValidForm valid = valid(passage.getId(), "code", passage.getCode());
    if ("n".equals(valid.getStatus())) {
      json.setMsg(valid.getInfo());
      json.setSuccess(false);
      return json;
    }

    Date time = new Date();
    SdkPayPassage entity;
    if (passage.getId() != null) {
      entity = systemService.getEntity(SdkPayPassage.class, passage.getId());
      entity.setBillingProvinceId(passage.getBillingProvinceId());
      entity.setCode(passage.getCode());
      entity.setName(passage.getName());
      entity.setSdkMinVer(passage.getSdkMinVer());
      entity.setSdkOperatorPayment(passage.getSdkOperatorPayment());
      // entity.setSdkPayment(sdkPayment);
      // entity.setSdkPayPassageParams(sdkPayPassageParams);
      entity.setSmsFetchUrl(passage.getSmsFetchUrl());
      entity.setSmsType(passage.getSmsType());
    } else {
      entity = passage;
      entity.setCreatedTime(time);
    }
    entity.setUpdatedTime(time);
    systemService.saveOrUpdate(entity);
    
    TypeReference<List<SdkPayPassageParam>> typeRef = new TypeReference<List<SdkPayPassageParam>>(){};
    List<SdkPayPassageParam> insertParamList = null;
    if (StringUtils.isNotBlank(insertParams)) {
      insertParamList = mapper.readValue(insertParams, typeRef);
      for (SdkPayPassageParam param : insertParamList) {
        param.setCreatedTime(time);
        param.setSdkPayPassage(entity);
        param.setUpdatedTime(time);
        systemService.saveOrUpdate(param);
      }
    }
    
    List<SdkPayPassageParam> updateParamList = null;
    if (StringUtils.isNotBlank(updateParams)) {
      updateParamList = mapper.readValue(updateParams, typeRef);
      for (SdkPayPassageParam param : updateParamList) {
        SdkPayPassageParam entityParam = systemService.get(SdkPayPassageParam.class, param.getId());
        entityParam.setName(param.getName());
        entityParam.setParam(param.getParam());
        entityParam.setUpdatedTime(time);
        entityParam.setVal(param.getVal());
        systemService.saveOrUpdate(entityParam);
      }
    }
    
    List<SdkPayPassageParam> deleteParamList = null;
    if (StringUtils.isNotBlank(deleteParams)) {
      deleteParamList = mapper.readValue(deleteParams, typeRef);
      for (SdkPayPassageParam param : deleteParamList) {
        systemService.delete(param);
      }
    }

    return json;
  }

  @RequestMapping(params = "del")
  public @ResponseBody
  AjaxJson del(Integer id) {
    AjaxJson json = new AjaxJson();
    try {
      SdkPayPassage payment = this.systemService.getEntity(SdkPayPassage.class, id);
      this.systemService.delete(payment);
      json.setMsg("删除成功");
    } catch (Exception e) {
      json.setMsg("无法删除");
    }
    return json;
  }

  @RequestMapping(params = "shielding")
  public ModelAndView editShielding(Integer id) {
    logger.debug("get the pay passage shielding edit page");
    ModelAndView mv = new ModelAndView("optpaypassage/editShielding");
    return mv;
  }
  
}
