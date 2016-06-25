package jeecg.ext.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jeecg.system.pojo.base.TSFunction;
import jeecg.system.service.SystemService;

public class SysConfUtils {
	
	public SystemService systemService;
	
	public SysConfUtils(SystemService systemService){
		this.systemService = systemService;
	}
	/**
	 * 根据role_id 获取该角色的首页tab，如果没有，则获取sys_conf默认的首页内容
	 * @param role_id
	 * @return
	 * 
	 */
	public TSFunction getIndexTab(String role_id, String user_id){
		String functionid = getSysConfKey("home", "tab", role_id, user_id);
		List<TSFunction> list = (List<TSFunction>)systemService.findByProperty(TSFunction.class, "id", functionid);
		
		return list.get(0);
	}
	public Map getDefaultSysConf(String head){
		String sql = "select * from t_s_sys_conf";
		List<Map<String, Object>>list = systemService.findForJdbc( sql, null);
		Map map = getKeysMap(head , list);
		return map;
	}
	

	
	public String getSysConfKey(String head, String field, String role_id,  String user_id){
		String key = head + "." + field;
		String sql = "select * from t_s_sys_conf_user where keyss ='" + key + "' and user_id = '" + user_id +"'";  
		List<Map<String, Object>> list = systemService.findForJdbc( sql , null);
		if(list != null && list.size() > 0){
			Map map = getKeysMap(head, list);
			String tab = (String)map.get(field);
			if(tab == null || tab.equals("")){
				Map tmp =  getDefaultSysConf(head);
				return (String)tmp.get(field);
			}else{
				return (String)map.get(field);
			}
		}else{
			String sqlRole = "select * from t_s_sys_conf_role where keyss ='" + key + "' and role_id = '" + role_id +"'";  
			List<Map<String, Object>> listRole = systemService.findForJdbc( sqlRole , null);
			if(listRole !=null && listRole.size() > 0){
				Map map = getKeysMap(head, listRole);
				String tab = (String)map.get(field);
				if(tab == null || tab.equals("")){
					Map tmp =  getDefaultSysConf(head);
					return (String)tmp.get(field);
				}else{
					return (String)map.get(field);
				}
			}else{
				sql = "select * from t_s_sys_conf";
				list = systemService.findForJdbc( sql, null);
				Map map = getKeysMap(head, list);
				return (String)map.get(field);
			}
		}
	}
	
	public String getVersion(String role_id, String user_id){
		return getSysConfKey("home", "version", role_id, user_id);
	}
	public Map getKeysMap(String keysValue){
		Map map = new HashMap(0);
		String[] list = keysValue.split(",");
		int length = list.length;
		for(int i = 0; i < length; i++){
			map.put(list[i].substring(0, list[i].indexOf("=")), list[i].substring(list[i].indexOf("=") + 1, list[i].length() ));
		}
		return map;
	}

	
	public Map getSysConfKeyMap(String head, String role_id, String user_id){
		String key = head;
		String sql = "select * from t_s_sys_conf_user where user_id = '" + user_id +"'";  
		List<Map<String, Object>> list = systemService.findForJdbc( sql , null);
		if(list != null && list.size() > 0){
			Map map = getKeysMap(head, list);
				return map;
		}else{
			String sqlRole = "select * from t_s_sys_conf_role where  role_id = '" + role_id +"'";  
			List<Map<String, Object>> listRole = systemService.findForJdbc( sqlRole , null);
			if(listRole !=null && listRole.size() > 0){
				Map map = getKeysMap(head, listRole);
				return map;
			}else{
				Map map = getDefaultSysConf(head);
				return map;
			}
		}
	}

	public void saveSysConfKey(String head, String field, String value ,String user_id, boolean isDefault){
		String updateSql = "replace t_s_sys_conf_user set value='" + value + "' ,user_id ='" + user_id + "' , keyss = '" + head + "." + field + "'";
		if(isDefault){
			updateSql = "replace t_s_sys_conf set value='" + value + "' ,keyss = '" + head + "." + field + "'";
			
		}
		systemService.executeSql(updateSql);
	}

	public Map getKeysMap(String head, List<Map<String, Object>> list){
		Map map = new HashMap(0);
		int length = list.size();
		for(int i = 0; i < length; i++){
			Map tmp = list.get(i);
			String key =(String) tmp.get("keyss");
			String[] array = key.split("\\.");
            if(array[0].equals(head)){
            	map.put(array[1], tmp.get("value"));
            }
		}
		return map;
	}
	

	

}
