<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameAdvertisementList" title="广告列表" actionUrl="sdkGameAdvertisementList.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="广告地址" field="advertisementUrl" query="true" width="10"></t:dgCol>
   <t:dgCol title="图片地址" field="iconUrl" width="10" ></t:dgCol>  
   <t:dgCol title="是否显示" field="ifshow" replace="显示_1,隐藏_0" width="10" query="true"></t:dgCol>
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>    
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkGameAdvertisementList.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkGameAdvertisementList.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkGameAdvertisementList.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>