<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="channelGameConfirm" title="确认框设置列表" actionUrl="channelGameConfirm.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>   
   <t:dgCol title="支付渠道名" field="channelName" width="10" query="true"></t:dgCol>
   <t:dgCol title="游戏名称" field="gameName" width="10" query="true"></t:dgCol>  
   <t:dgCol title="计费代码配置的原本游戏" field="originalGameName" width="10" query="true"></t:dgCol>
   <t:dgCol title="计费提示框状态" field="chargetip" replace="使用_1,停用_0" width="15" query="true"></t:dgCol>
   <t:dgCol title="计费成功提示框状态" field="chargesuceesstip" replace="使用_1,停用_0" width="15" query="true"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="channelGameConfirm.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="channelGameConfirm.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="channelGameConfirm.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>