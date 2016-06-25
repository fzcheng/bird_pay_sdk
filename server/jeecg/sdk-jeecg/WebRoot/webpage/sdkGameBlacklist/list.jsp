<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameBlacklist" title="黑名单列表" actionUrl="sdkGameBlacklist.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="国际移动用户识别码（IMSI）" field="imsi" width="10"></t:dgCol>
   <t:dgCol title="手机号码" field="mobile" width="10" query="true"></t:dgCol>  
   <t:dgCol title="手机运营商类型" field="operatorType" replace="移动_1,联通_2,电信_3" width="10" query="true"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkGameBlacklist.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkGameBlacklist.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkGameBlacklist.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>