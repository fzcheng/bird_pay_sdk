<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkCenterNumber" title="短信中心号段列表" actionUrl="sdkCenterNumber.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="省份名称" field="provinceNo" replace="${provinceReplace}" width="10" query="true"></t:dgCol>
   <t:dgCol title="城市名称" field="areaCode" replace="${cityReplace}" width="10" query="true"></t:dgCol>  
   <t:dgCol title="短信中心号段" field="centernumber" query="true" width="10"></t:dgCol>
   <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
   <t:dgCol title="更改时间" field="updatedTime" formatter="yyyy-MM-dd hh:mm:ss" width="10" ></t:dgCol>
  <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="sdkCenterNumber.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkCenterNumber.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkCenterNumber.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>