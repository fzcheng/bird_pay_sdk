package jeecg.ext.sdk.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jeecg.ext.sdk.entity.ChannelReportEntity;
import jeecg.ext.sdk.entity.SdkChannel;
import jeecg.ext.sdk.entity.SdkChannelPartner;
import jeecg.ext.sdk.entity.SdkChannelReportEntity;
import jeecg.ext.sdk.entity.SdkOrder;
import jeecg.ext.sdk.entity.SdkOrderExcel;
import jeecg.system.pojo.base.TSType;
import jeecg.system.pojo.base.TSUser;
import jeecg.system.service.SystemService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.core.util.excel.ExcelExportUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/channelReport")
public class ChannelReportController extends BaseController{

	@Autowired
	private SystemService systemService;
	
	private String getOrderString="SELECT * FROM `sdk_order` where channel REGEXP  '.+_.+_{channel}' and `status`=1";
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("partnerId", request.getParameter("partnerId"));
		return new ModelAndView("channelReport/list");
	}
	@RequestMapping(params = "getList")
	public void getList(String partnerId, ChannelReportEntity channelReportEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		// 获取该渠道商的渠道
		if(!StringUtil.isNotEmpty(partnerId)){
			TSUser user=ResourceUtil.getSessionUserName();
			String tmpChannel=ResourceUtil.getConfigByName("channelPartner","channelP");
			partnerId=user.getUserName().replace(tmpChannel, "");
		}
		 SdkChannelPartner sdkChannelPartner=this.systemService.getEntity(SdkChannelPartner.class, oConvertUtils.getInt(partnerId));
		 if(sdkChannelPartner!=null){
			 	String sqlString=ResourceUtil.getConfigByName("channelPartnerSql");
				sqlString=sqlString.replace("{channelPartnetId}",oConvertUtils.getString(sdkChannelPartner.getId())).replace("{channelPartnerName}", sdkChannelPartner.getPartnerName());
				String statDayBegin = request.getParameter("statDay_begin");
				String statDayEnd = request.getParameter("statDay_end");
				if(StringUtil.isNotEmpty(statDayBegin) && StringUtil.isNotEmpty(statDayEnd)){
					String tmpsqlString=ResourceUtil.getConfigByName("channelPartnerSqlSelectTime");
					sqlString=tmpsqlString.replace("{table}", sqlString).replace("{statDay_begin}", statDayBegin).replace("{statDay_end}", statDayEnd);
				}
				dataGrid.setField(ResourceUtil.getConfigByName("channelPartnerFeild", "statDay,channelPartnetId,channelPartnerName,gameId,channel,income,regNum,rechargeNum"));
				
				HqlQuery hqlQuery=new HqlQuery(ChannelReportEntity.class, sqlString,dataGrid);
				hqlQuery.setCurPage(dataGrid.getPage());
				PageList list=this.systemService.getPageListBySql(hqlQuery, true);
				List<ChannelReportEntity> channelReportEntitiyList=ChannelReportEntity.getChannelReportEntityList(this.systemService,list.getResultList());
				dataGrid.setReaults(channelReportEntitiyList);
				dataGrid.setPage(list.getCurPageNO());
				dataGrid.setTotal(list.getCount());
				TagUtil.datagrid(response, dataGrid);
		 }	
	}
	@RequestMapping(params = "outputExcel")
	public void outputExcel(HttpServletRequest request, HttpServletResponse response){
		
		
		// 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
        	 	        	codedFileName =  "渠道商数据" ;
        	// 根据浏览器进行转码，使其支持中文文件名
			String browse = BrowserUtils.checkBrowse(request);
			if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName, "UTF-8")
								+ ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"), "ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			String partnerId="";
			if(!StringUtil.isNotEmpty(partnerId)){
				TSUser user=ResourceUtil.getSessionUserName();
				String tmpChannel=ResourceUtil.getConfigByName("channelPartner","channelP");
				partnerId=user.getUserName().replace(tmpChannel, "");
			}
			 SdkChannelPartner sdkChannelPartner=this.systemService.getEntity(SdkChannelPartner.class, oConvertUtils.getInt(partnerId));
			 if(sdkChannelPartner!=null){
				 	String sqlString=ResourceUtil.getConfigByName("channelPartnerSql");
					sqlString=sqlString.replace("{channelPartnetId}",oConvertUtils.getString(sdkChannelPartner.getId())).replace("{channelPartnerName}", sdkChannelPartner.getPartnerName());
					String statDayBegin = request.getParameter("statDay_begin");
					String statDayEnd = request.getParameter("statDay_end");
					if(StringUtil.isNotEmpty(statDayBegin) && StringUtil.isNotEmpty(statDayEnd)){
						String tmpsqlString=ResourceUtil.getConfigByName("channelPartnerSqlSelectTime");
						sqlString=tmpsqlString.replace("{table}", sqlString).replace("{statDay_begin}", statDayBegin).replace("{statDay_end}", statDayEnd);
					}
					List<Map<String, Object>> mapList =this.systemService.findForJdbc(sqlString, null);
					List<ChannelReportEntity> list=getEntityList(mapList,ChannelReportEntity.class);
					List<ChannelReportEntity> channelReportEntitiyList=ChannelReportEntity.getChannelReportEntityList(this.systemService,list);
					HSSFWorkbook workbook = null ;
		            
		            workbook =  ExcelExportUtil.exportExcel("导出信息", ChannelReportEntity.class, channelReportEntitiyList);
		            fOut = response.getOutputStream();
		            workbook.write(fOut);
			 }	 
        }
        catch (UnsupportedEncodingException e1)
        {
            
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            try
            {
                fOut.flush();
                fOut.close();
            }
            catch (IOException e)
            {
                
            }
        }
	}
	
	private <T> List<T> getEntityList(List<Map<String, Object>> mapList,Class<T> clazz){
		List<T> rsList = new ArrayList<T>();
		T po = null;
		for(Map<String,Object> m:mapList){
			try {
				po = clazz.newInstance();
				MyBeanUtils.copyMap2Bean_Nobig(po, m);
				rsList.add(po);
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rsList;
	
	}

	
}
