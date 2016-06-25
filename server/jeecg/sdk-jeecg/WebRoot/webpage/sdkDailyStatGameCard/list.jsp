<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkDailyStatGameCard" title="卡商日报数据设置表" actionUrl="sdkDailyStatGameCard.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="日期" field="daily" formatter="yyyy-MM-dd" width="10"></t:dgCol>
   <t:dgCol title="游戏ID" field="gameId" replace="${gameReplace}" width="10" query="true"></t:dgCol>
   <t:dgCol title="运营商" field="operatorType" replace="移动_1,联通_2,电信_3" width="10" query="true"></t:dgCol>
   <t:dgCol title="支付渠道" field="operatorPayChannelId" replace="${channelReplace}" width="10" query="true"></t:dgCol>
   <t:dgCol title="渠道商" field="channelNum" width="10" query="true"></t:dgCol>
   <t:dgCol title="总金额" field="totalAmount" width="10" ></t:dgCol>      
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkDailyStatGameCard.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkDailyStatGameCard.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkDailyStatGameCard.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>