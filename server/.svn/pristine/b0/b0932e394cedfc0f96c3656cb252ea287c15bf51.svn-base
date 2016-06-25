<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameAlipay" title="官方支付宝信息表" actionUrl="sdkGameAlipay.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5"></t:dgCol>  
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="8" ></t:dgCol>
   <t:dgCol title="游戏名称" field="sdkGame_gameId" replace="${gameReplace}" width="8" query="true"></t:dgCol>
   <t:dgCol title="支付宝合作商户ID" field="partner" width="8"></t:dgCol>
   <t:dgCol title="支付宝账户ID" field="seller" width="13"></t:dgCol>
   <t:dgCol title="通知回调地址" field="notifyUrl" width="15"></t:dgCol>  
   <t:dgCol title="支付宝商户（RSA）私钥" field="rsaPrivate" width="15"></t:dgCol>
   <t:dgCol title="支付宝（RSA）公钥" field="rsaAlipayPublic" width="15"></t:dgCol> 
   
  <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkGameAlipay.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkGameAlipay.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkGameAlipay.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>