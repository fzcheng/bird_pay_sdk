<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkSms" title="企信通列表" actionUrl="sdkSms.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="运营商" field="operationType" replace="移动_1,联通_2,电信_3" width="10"></t:dgCol>
   <t:dgCol title="上行端口" field="upPort" query="true" width="10"></t:dgCol>
   <t:dgCol title="是否使用" field="useState" replace="使用_1,停用_0" width="10" query="true"></t:dgCol>   
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
  <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkSms.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkSms.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkSms.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>