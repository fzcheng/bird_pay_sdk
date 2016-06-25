<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);

function openGifList(id){
	addOneTab('礼包列表','sdkGameGif.do?list&gameId='+id);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="gameGifList" title="游戏礼包管理列表" actionUrl="sdkGif.do?datagrid"  idField="gameId"  fit="true" >
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="gameId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="游戏名称" field="name"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="游戏包名" field="packageName"  width="10" ></t:dgCol>
    <t:dgCol title="所属CP" field="sdkGameCp_name"  width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>    
   <t:dgFunOpt title="管理礼包"  funname="openGifList(id)" ></t:dgFunOpt>  
   <t:dgToolBar title="查看" icon="icon-search" funname="" url=""></t:dgToolBar>
  </t:datagrid>
 </div>
</div>