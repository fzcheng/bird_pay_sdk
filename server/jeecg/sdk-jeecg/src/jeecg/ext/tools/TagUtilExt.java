package jeecg.ext.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;

public class TagUtilExt {

	/**
	 * 控件类型：easyui
	 * 返回datagrid JSON数据
	 * @param response
	 * @param dataGrid
	 */
	public static void datagrid(HttpServletResponse response,DataGrid dg,String key) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		JSONObject object = TagUtilExt.getJson(dg,key);
		try {
			PrintWriter pw=response.getWriter();
			pw.write(object.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回列表JSONObject对象
	 * @param field
	 * @param dataGrid
	 * @return
	 */
	private static JSONObject getJson(DataGrid dg,String key) {
		JSONObject jObject = null;
		try {
			if(!StringUtil.isEmpty(dg.getFooter())){
				jObject = JSONObject.fromObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getReaults(),dg.getFooter().split(","),key));
			}else{
				jObject = JSONObject.fromObject(listtojson(dg.getField().split(","), dg.getTotal(), dg.getReaults(),null,key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObject;

	}
	/**
	 * 循环LIST对象拼接EASYUI格式的JSON数据
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String listtojson(String[] fields, int total, List list,String[] footers,String key) throws Exception {
		Object[] values = new Object[fields.length];
		String jsonTemp = "{\'total\':" + total + ",\'rows\':[";
		for (int j = 0; j < list.size(); j++) {
			jsonTemp = jsonTemp + "{\'state\':\'closed\',";
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].toString();
				String tmp="";
				if(fieldName.equals("id")){
					tmp=key;
				}else{
					tmp=fieldName;
				}
				if(list.get(j) instanceof Map){
					values[i] = ((Map)list.get(j)).get(tmp);
				}else{
					values[i] = TagUtil.fieldNametoValues(tmp, list.get(j));
					// json格式问题
					if(values[i] instanceof String){
						values[i]=String.valueOf(values[i]).replace("\\", "\\\\").replace("\"", "\\\"")
							    .replace("\r", "\\r")
							    .replace("\n", "\\n");
					}
					
				}
				jsonTemp = jsonTemp + "\'" + fieldName + "\'" + ":\'" + values[i]+ "\'";
				if (i != fields.length - 1) {
					jsonTemp = jsonTemp + ",";
				}
			}
			if (j != list.size() - 1) {
				jsonTemp = jsonTemp + "},";
			} else {
				jsonTemp = jsonTemp + "}";
			}
		}
		jsonTemp = jsonTemp + "]";
		if(footers!=null){
			jsonTemp = jsonTemp + ",";
			jsonTemp = jsonTemp + "\'footer\':[";
			jsonTemp = jsonTemp + "{";
			jsonTemp = jsonTemp + "\'name\':\'合计\',";
			for(String footer:footers){
				String footerFiled = footer.split(":")[0];
				Object value = null;
				if(footer.split(":").length==2){
					value = footer.split(":")[1];
				}else{
					value = getTotalValue(footerFiled,list);
				}
				jsonTemp = jsonTemp +"\'"+footerFiled+"\':\'"+value+"\',";
			}
			if(jsonTemp.endsWith(",")){
				jsonTemp = jsonTemp.substring(0,jsonTemp.length()-1);
			}
			jsonTemp = jsonTemp + "}";
			jsonTemp = jsonTemp + "]";
		}
		jsonTemp = jsonTemp + "}";
		return jsonTemp;
	}
	
	/**
	 * 计算指定列的合计
	 * @param filed 字段名
	 * @param list 列表数据
	 * @return
	 */
	private static Object getTotalValue(String fieldName,List list){
		Double sum = 0D;
		try{
			for (int j = 0; j < list.size(); j++) {
				Double v = 0d;
				String vstr =String.valueOf(TagUtil.fieldNametoValues(fieldName, list.get(j)));
				if(!StringUtil.isEmpty(vstr)){
					v = Double.valueOf(vstr);
				}else{
					
				}
				sum+=v;
			}
		}catch (Exception e) {
			return "";
		}
		return sum;
	}
}
