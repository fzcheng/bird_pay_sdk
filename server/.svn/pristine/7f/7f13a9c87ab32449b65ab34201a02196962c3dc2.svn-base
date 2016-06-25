<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkSwbInfo" title="三网信息列表" actionUrl="sdkSwbInfo.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="游戏名称" field="gameId" replace="${gameReplace}" query="true" width="10"></t:dgCol>
   <t:dgCol title="运营商" field="operatorType" replace="中国移动_1,中国联通_2,中国电信_3" query="true" width="10"></t:dgCol>
   <t:dgCol title="游戏申请的appid" field="appId" query="true" width="10"></t:dgCol>
   <t:dgCol title="游戏申请的appkey" field="appKey" query="true" width="10"></t:dgCol>
   <t:dgCol title="沃商店为开发者分配的VAC资质编号" field="cpId" width="10"></t:dgCol>
   <t:dgCol title="开发者在沃商店开发者社区的唯一编码" field="cpCode" width="10"></t:dgCol>
   <t:dgCol title="公司名称" field="company" width="10"></t:dgCol>
   <t:dgCol title="电话号码" field="phone" width="10"></t:dgCol>
   <t:dgCol title="渠道" field="channel" width="10"></t:dgCol>
   <t:dgCol title="使用状态" field="useStatus" replace="停用_0,使用_1" query="true" width="10"></t:dgCol>
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
   <t:dgCol title="更改时间" field="updatedTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
  <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkSwbInfo.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkSwbInfo.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkSwbInfo.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>