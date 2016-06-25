<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
function openFei(id){
	addOneTab('付费列表','sdkOrder.do?list&gameId='+id);
}
function setWeipai(id){
	addOneTab('微派计费管理','wiicode.do?list&gameId='+id);
}
function detail(title,url, id) {
	var rowData = $('#'+id).datagrid('getSelected');
//	if (rowData.id == '') {
//		tip('请选择查看项目');
//		return;
//	}
	
	if (!rowData) {
		tip('请选择要查看的游戏');
		return;
	}
    url += '&load=detail&id='+rowData.id;	 
	createdetailwindow(title,url);
}

function setFufeiWeizhi(id){
  	var url="gameInfo.do?editPayment&gameId="+id;
  	var title="付费位置";
	createwindow(title,url);
}

</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="gameDataList" title="游戏信息列表"  actionUrl="gameInfo.do?datagrid&cp_id=${cp_id}" fit="true" idField="gameId" >
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="Game ID" field="gameId"  width="5"></t:dgCol>
   <t:dgCol title="游戏名称" field="name"  width="10"></t:dgCol>
   <t:dgCol title="游戏商"  field="sdkGameCp_name"  width="10"></t:dgCol>   
   <t:dgCol title="CPID"  field="sdkGameCp_cpId"  width="10"></t:dgCol> 
   <t:dgCol title="创建时间" field="createTime"  formatter="yyyy-MM-dd hh:mm:ss" width="15"></t:dgCol>
  </t:datagrid>
 </div>
</div>