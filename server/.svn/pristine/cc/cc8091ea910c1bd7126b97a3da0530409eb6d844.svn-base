<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameInformationList" title="游戏资讯" actionUrl="sdkInformation.do?datagrid2&gameId=${gameId}"  idField="id"  fit="true">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="infoId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="资讯标题" field="title"  width="10"  query="true"></t:dgCol>
   <t:dgCol title="资讯类型" field="type"  width="10"></t:dgCol>
   <t:dgCol title="简介" field="detail"  width="10"></t:dgCol>
  <t:dgCol title="资讯链接" field="weburl"  width="10"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>  
<t:dgConfOpt title="删除" url="sdkInformation.do?del&infoId={infoId}" message="此操作将会删除与之相关的所有数据，确认删除？" ></t:dgConfOpt>  
   	<t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkInformation.do?addorupdate&gameId=${gameId}" ></t:dgToolBar>
   	<t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkInformation.do?addorupdate&gameId=${gameId}" ></t:dgToolBar>
    <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="sdkInformation.do?addorupdate&gameId=${gameId}"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>