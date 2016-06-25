<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
function add(title,addurl,gname) {
	gridname=gname;
	createwindow(title, addurl,700,500);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameGifList" title="游戏礼包列表" actionUrl="sdkGameGif.do?datagrid&gameId=${gameId}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="礼包名称" field="title"  width="10" query="true"></t:dgCol>
    <t:dgCol title="所属游戏" field="sdkGame_name"  width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>    
   <t:dgConfOpt title="删除" url="sdkGameGif.do?del&gameId=${gameId}&id={id}" message="此操作将会删除与之相关的所有数据，确认删除？" operationCode="gif_del"></t:dgConfOpt>  
  <%--  <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="sdkGameGif.do?addorupdate&gameId=${gameId}"></t:dgToolBar> --%>
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="sdkGameGif.do?addorupdate&gameId=${gameId}"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="sdkGameGif.do?addorupdate&gameId=${gameId}"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>