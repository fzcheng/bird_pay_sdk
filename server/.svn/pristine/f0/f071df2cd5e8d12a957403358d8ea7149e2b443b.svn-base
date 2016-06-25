<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="resPwdList" title="用户信息列表"  actionUrl="resetPwd.do?datagrid" queryMode="single"   fit="true"  idField="userId" >
   <t:dgCol title="编号" field="id"  width="5"  hidden="false"></t:dgCol>
   <t:dgCol title="用户ID" field="userId"  width="5"  query="true"   queryMode="single"    ></t:dgCol>
   <t:dgCol title="用户昵称" field="nickname"  width="5" ></t:dgCol>
   <t:dgCol title="手机IMEI" field="phoneImei"  width="10"  query="true"   queryMode="single"   ></t:dgCol>
   <t:dgCol title="手机号码"  field="phoneNumber"  width="10"   query="true"  queryMode="single"   ></t:dgCol>   
    <t:dgCol title="绑定状态"  field="bandStatus"  width="5" replace="未绑定_0,已绑定_1"></t:dgCol> 
    <t:dgCol title="注册时间"  field="regTime"  width="5"></t:dgCol>
    <t:dgCol title="注册IP"  field="regIP"  width="5"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="重置密码" url="resetPwd.do?reset&userId={userId}" message="此操作将会此用户密码重置为123456，确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="查看" icon="icon-search" funname="detail" url=""></t:dgToolBar>
  </t:datagrid>
 </div>
</div>