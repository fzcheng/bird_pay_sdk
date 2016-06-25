<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
function openGameList(name,id){
	console.log(name);
	console.log(id);
	addOneTab('游戏商游戏列表','gameInfo.do?list&cp_id='+id);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="gameCmpData" title="游戏商列表" actionUrl="gameCmp.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="游戏商ID" field="id"  width="5" hidden="false"></t:dgCol>
      <t:dgCol title="游戏商ID" field="cpId"  width="5"></t:dgCol>
   <t:dgCol title="游戏商名称" field="name"  width="10" query="true"></t:dgCol>
   <t:dgCol title="登录名" field="loginName"  width="10" query="true"></t:dgCol>
    <t:dgCol title="联系方式" field="email"  width="10" query="true"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime"  formatter="yyyy-MM-dd hh:mm:ss" width="15"></t:dgCol>
   <t:dgFunOpt title="游戏管理"  funname="openGameList(name,id)" ></t:dgFunOpt> 
   <t:dgCol title="操作" field="opt"></t:dgCol>     
   <t:dgDelOpt url="gameCmp.do?del&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="gameCmp.do?addorupdate"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="gameCmp.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="gameCmp.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>