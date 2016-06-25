package jeecg.ext.sdk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import jeecg.ext.sdk.entity.SdkGameCp;
import jeecg.ext.sdk.entity.SdkOrder;
import jeecg.ext.sdk.entity.SdkOrderExcel;
import jeecg.system.pojo.base.TSType;
import jeecg.system.pojo.base.TSTypegroup;
import jeecg.system.service.SystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.excel.ExcelExportUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/sdkOrder"})
public class SdkOrderController extends BaseController{
  static Log logger = LogFactory.getLog(SdkOrderController.class);

  @Autowired
  private SystemService systemService;

  @RequestMapping(params={"list"})
  public ModelAndView list(String gameId, HttpServletRequest request,
			HttpServletResponse response) {
		// return new ModelAndView("gameInfo/gameinfo_list");
    request.setAttribute("gameId", gameId);
    return new ModelAndView("sdkOrder/list");
  }

	@RequestMapping(params={"datagrid"})
	public void datagrid(String gameId, SdkOrder sdkOrder,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(SdkOrder.class, dataGrid);
    cq.eq("sdkGame.gameId", Integer.valueOf(gameId));
    cq.add();
    String orderNo = request.getParameter("orderNo");
		if ((StringUtils.isNotEmpty(orderNo)) && (StringUtils.isNotBlank(orderNo))) {
      logger.info("the sdk_order orderNo is :" + orderNo);
      cq.add(Restrictions.ilike("orderNo", "%" + orderNo.trim() + "%"));
    }
    String amount = request.getParameter("amount");
		if ((StringUtils.isNotEmpty(amount)) && (StringUtils.isNotBlank(amount))) {
			cq.add(Restrictions.eq("amount", Float.valueOf(Float.parseFloat(amount))));
    }
		// cq.add(Restrictions.or(Restrictions.eq("status", 1),
		// Restrictions.eq("status", 3)));
    String status = request.getParameter("status");
		if ((StringUtils.isNotEmpty(status)) && (StringUtils.isNotBlank(status))) {
      cq.add(Restrictions.eq("status", parseInteger(status)));
    }
    String ctBegin = request.getParameter("createTime_begin");
    String ctEnd = request.getParameter("createTime_end");
		if ((StringUtil.isNotEmpty(ctBegin)) && (StringUtil.isNotEmpty(ctEnd))) {
      try {
				cq.ge("createTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(ctBegin));
				cq.le("createTime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(ctEnd));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      cq.add();
    }
		// 查询条件组装器
    cq.addOrder("createTime", SortDirection.desc);
		HqlGenerateUtil.installHql(cq, sdkOrder);
    this.systemService.getDataGridReturn(cq, true);
    List<SdkOrder> list = dataGrid.getReaults();
		List<TSType> tsTypeList = getStatusDetailList();
    for (SdkOrder order : list) {
      String detail = getStateDetail(order.getStatusDetail(), tsTypeList);
      if (StringUtil.isNotEmpty(detail)) {
				order.setStatusDetail(getStateDetail(order.getStatusDetail(),
						 tsTypeList));
      }

    }
    dataGrid.setReaults(list);
    TagUtil.datagrid(response, dataGrid);
  }

  private Integer parseInteger(String str) {
    Integer num = null;
    try {
			if (StringUtils.isNotBlank(str)){
        num = Integer.valueOf(str);
			}
		}catch (NumberFormatException e) {
      logger.warn("parse number string error! str = " + str);
    }
    return num;
  }

  private List<TSType> getStatusDetailList() {
		List<TSType> list = new ArrayList<TSType>();
		String stateTable = ResourceUtil.getConfigByName("orderStat_Table",
				"orderStat");
		TSTypegroup typegroup = (TSTypegroup)this.systemService.findUniqueByProperty(
				TSTypegroup.class, "typegroupcode", stateTable);
    if (typegroup != null) {
      list = typegroup.getTSTypes();
    }
    return list;
  }
  private String getStateDetail(String key, List<TSType> list) {
    String value = "";
    for (TSType type : list) {
      if (type.getTypename().equals(key)) {
        value = type.getTypecode();
        break;
      }
    }
    return value;
  }

	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 */

	@RequestMapping(params={"outputExcel"})
	public void outputExcel(HttpServletRequest request,
			HttpServletResponse response){

		// 生成提示信息，
    response.setContentType("application/vnd.ms-excel");
    String codedFileName = null;
    OutputStream fOut = null;
		try{
      codedFileName = "付费数据";
			// 根据浏览器进行转码，使其支持中文文件名
      String browse = BrowserUtils.checkBrowse(request);
      if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
        response.setHeader(
          "content-disposition", 
          "attachment;filename="
								+ URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
      } else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
        response.setHeader("content-disposition", 
          "attachment;filename=" + newtitle + ".xls");
      }

      String gameId = request.getParameter("gameId");
      CriteriaQuery cq = new CriteriaQuery(SdkOrder.class);
      cq.eq("sdkGame.gameId", Integer.valueOf(gameId));
      cq.add();
			cq.add(Restrictions.or(Restrictions.eq("status", Integer.valueOf(1)),
					Restrictions.eq("status", Integer.valueOf(3))));
      String ctBegin = request.getParameter("createTime_begin");
      String ctEnd = request.getParameter("createTime_end");
			if ((StringUtil.isNotEmpty(ctBegin)) && (StringUtil.isNotEmpty(ctEnd))) {
        try {
				cq.ge("createTime",
						new SimpleDateFormat("yyyy-MM-dd").parse(ctBegin));
				cq.le("createTime",
						new SimpleDateFormat("yyyy-MM-dd").parse(ctEnd));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        cq.add();
      }
			// 查询条件组装器
      cq.addOrder("createTime", SortDirection.desc);

			// CriteriaQuery cq = new CriteriaQuery(SdkOrder.class);

			// cq.eq("sdkGame.gameId", Integer.valueOf(gameId));
			// cq.add(Restrictions.or(Restrictions.eq("status", 1),
			// Restrictions.eq("status", 3)));
			// String ctBegin = request.getParameter("createTime_begin");
			// String ctEnd = request.getParameter("createTime_end");

			List<SdkOrder> list = this.systemService.getListByCriteriaQuery(cq,
					Boolean.valueOf(false));
			List<TSType> tsTypeList = getStatusDetailList();
      for (SdkOrder order : list) {
				String detail = getStateDetail(order.getStatusDetail(),
						tsTypeList);
        if (StringUtil.isNotEmpty(detail)) {
					order.setStatusDetail(getStateDetail(
						order.getStatusDetail(), tsTypeList));
        }
      }
			List<SdkOrderExcel> resultList = new ArrayList<SdkOrderExcel>();
      for (SdkOrder order : list) {
        SdkOrderExcel excel = new SdkOrderExcel();
        excel.setOrderNo(order.getOrderNo());
        excel.setAmount(order.getAmount());
//        excel.setCpName(order.getSdkGameCp().getName());
        excel.setCreateTime(order.getCreateTime());
//				excel.setNotifyTime(order.getNotifyTime()+"");
//        excel.setResult(order.getStatusDetail());
        excel.setStatus(String.valueOf(order.getStatus()));
//        excel.setCompleteTime(order.getCompleteTime());
				switch (order.getNotifyStatus().intValue()) {
        case 0:
          excel.setNotifyStatus("尚未通知");
          break;
        case 1:
          excel.setNotifyStatus("通知成功");
          break;
        case 2:
          excel.setNotifyStatus("通知失败");
          break;
        case 3:
          excel.setNotifyStatus("无需通知");
          break;

        default:
          excel.setNotifyStatus("通知无响应");
					break;        }
        resultList.add(excel);
      }

			// 进行转码，使其支持中文文件名
			// 产生工作簿对象
      HSSFWorkbook workbook = null;

			workbook = ExcelExportUtil.exportExcel("导出信息", SdkOrderExcel.class,
					resultList);
      fOut = response.getOutputStream();
      workbook.write(fOut);
		} catch (UnsupportedEncodingException e1){

		} catch (Exception e) {

		} finally {
			try {
        fOut.flush();
        fOut.close();
      } catch (IOException e) {

      }
    }
//    catch (Exception localException)
//    {
//      try
//      {
//        fOut.flush();
//        fOut.close();
//      }
//      catch (IOException localIOException1)
//      {
//      }
//    }
//    finally
//    {
//      try
//      {
//        fOut.flush();
//        fOut.close();
//      }
//      catch (IOException localIOException2)
//      {
//      }
//    }
  }
}