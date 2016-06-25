<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkOperatorPayType" title="运营商支付方式表" actionUrl="sdkOperatorPayType.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>   
   <t:dgCol title="支付方式" field="type" replace="${sdkPaymentReplace}" width="10" query="true"></t:dgCol>
   <t:dgCol title="运营商类型" field="operator" replace="移动_1,联通_2,电信_3" width="10" query="true"></t:dgCol>  
   <t:dgCol title="最小支付金额" field="minPrice" width="10" query="true"></t:dgCol>
   <t:dgCol title="最大支付金额" field="maxPrice" width="10" query="true"></t:dgCol>
   <t:dgCol title="优先级" field="idx" width="7" query="true"></t:dgCol>
   <t:dgCol title="最低版本号" field="ver" width="20" query="true"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkOperatorPayType.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkOperatorPayType.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkOperatorPayType.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>