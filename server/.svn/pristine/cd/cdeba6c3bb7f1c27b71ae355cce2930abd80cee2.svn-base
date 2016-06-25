<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
    <t:datagrid name="optpaypassageDatagrid" title="运营商计费通道列表" actionUrl="optpaypassage.do?datagrid" idField="id" fit="true">
      <t:dgCol title="ID" field="id" width="5"></t:dgCol>
      <t:dgCol title="运营商" field="sdkOperatorPayment_name" width="10"></t:dgCol>
      <t:dgCol title="计费通道名" field="name" width="10"></t:dgCol>
      <t:dgCol title="计费通道编号" field="code" width="10"></t:dgCol>
      <t:dgCol title="支持sdk最低版本" field="sdkMinVer" width="10"></t:dgCol>
      <t:dgCol title="计费省份" field="billingProvinceId" width="10" replace="${provinceReplaces}"></t:dgCol>
      <t:dgCol title="指令方式" field="smsType" replace="短信指令_1,网络获取_2,SDK接入_3" width="10"></t:dgCol>
      <t:dgCol title="创建时间" field="createdTime" formatter="yyyy-MM-dd hh:mm:ss" width="10"></t:dgCol>
      <t:dgCol title="更新时间" field="updatedTime"  formatter="yyyy-MM-dd hh:mm:ss" width="10"></t:dgCol>
      <t:dgCol title="操作" field="opt"></t:dgCol>
      <t:dgFunOpt title="屏蔽设置"  funname="setOptPayPassageShielding(id)" ></t:dgFunOpt>
      <t:dgDelOpt url="optpaypassage.do?del&id={id}" title="删除"></t:dgDelOpt>
      <t:dgToolBar title="增加" icon="icon-add" funname="add" url="optpaypassage.do?edit"></t:dgToolBar>
      <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="optpaypassage.do?edit"></t:dgToolBar>
    </t:datagrid>
  </div>
</div>
<script type="text/javascript">
function setOptPayPassageShielding(id){
  var url="optpaypassage.do?shielding&id="+id;
  var title="屏蔽设置";
  createwindow(title,url);
}
</script>