/**
 * 
 */
package jeecg.ext.sdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.SdkPayPassage;
import jeecg.system.service.SystemService;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Merlin
 *
 */
@Controller
@RequestMapping("/paypassage")
public class PayPassageController extends BaseController {
  private static final Logger logger = Logger.getLogger(PayPassageController.class);
  @Autowired
  private SystemService systemService;
  
  @RequestMapping(params = "list")
  public ModelAndView list() {
    logger.debug("get the pay passage list page");
    ModelAndView mv = new ModelAndView("paypassage/list");
    return mv;
  }
  
  @RequestMapping(params = "datagrid")
  public void getList(SdkPayPassage passage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
    logger.debug("get the payment list datagrid");
    CriteriaQuery cq = new CriteriaQuery(SdkPayPassage.class, dataGrid);
    HqlGenerateUtil.installHql(cq, passage);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }
}
