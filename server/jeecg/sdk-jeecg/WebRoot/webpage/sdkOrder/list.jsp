<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
<script>
$(document).ready(function(){
$("input[name='createTime_begin']").attr("class","easyui-datetimebox");
$("input[name='createTime_end']").attr("class","easyui-datetimebox");

}
);
function downfile(url){
	window.document.location.href=url+"&createTime_begin="+$("input[name='createTime_begin']").val()+"&createTime_end="+$("input[name='createTime_end']").val();
}
$("input[name='createTime_begin']").datetimebox({
	onSelect: function(date){
		//alert(date);
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		
	}
});

$("input[name='createTime_end']").datetimebox({
	onSelect: function(date){
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
	}
});
</script>
  <t:datagrid name="sdkorderList" title="费用信息列表"  actionUrl="sdkOrder.do?datagrid&gameId=${gameId}"  idField="orderNo" fit="true" queryMode="group">
   <t:dgCol title="订单号" field="orderNo"  width="10" query="true"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime"   width="17" query="true"  queryMode="group"  formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="金额"  field="amount"  width="10" query="true"></t:dgCol>   
   <t:dgCol title="商户名称" field="sdkGameCp_name"  width="10"></t:dgCol>
   <t:dgCol title="订单状态" field="status"  width="5"  replace="订单创建_0,支付成功_1,等待结果_2,支付失败_3,订单异常_4" query="true"></t:dgCol>
   <t:dgCol title="最后一次通知时间" field="notifyTime" width="15"></t:dgCol>
   <t:dgCol title="完成时间" field="completeTime" width="15"></t:dgCol>
    <t:dgCol title="失败原因"  field="statusDetail"  width="20"></t:dgCol>
    <t:dgCol title="通知状态" field="notifyStatus" width="10" replace="尚未通知_0,通知成功_1,通知失败_2,无需通知_3"/>
    <t:dgToolBar title="导出Excel" icon="icon-add" funname="downfile('sdkOrder.do?outputExcel&gameId=${gameId}')" url=""></t:dgToolBar>
 </t:datagrid>
 
 </div>
</div>