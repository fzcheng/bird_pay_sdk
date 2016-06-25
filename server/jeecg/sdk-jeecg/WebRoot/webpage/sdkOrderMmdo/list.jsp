<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
 
 <script>
$(document).ready(function(){
$("input[name='reqTime_begin']").attr("class","easyui-datetimebox");
$("input[name='reqTime_end']").attr("class","easyui-datetimebox");

}
);
function downfile(url){
	window.document.location.href=url+"&createTime_begin="+$("input[name='createTime_begin']").val()+"&createTime_end="+$("input[name='createTime_end']").val();
}
$("input[name='reqTime_begin']").datetimebox({
	onSelect: function(date){
		//alert(date);
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		
	}
});

$("input[name='reqTime_end']").datetimebox({
	onSelect: function(date){
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
	}
});
</script>
 
  <t:datagrid name="sdkOrderMmdo" title="运营商订单表" actionUrl="sdkOrderMmdo.do?datagrid"  idField="id"  fit="true" queryMode="group">
    <t:dgCol title="payId" field="payId"  width="10" query="true"></t:dgCol>
    <t:dgCol title="创建时间" field="reqTime" formatter="yyyy-MM-dd hh:mm:ss" queryMode="group" width="23" query="true"></t:dgCol>
    <t:dgCol title="运营商类型" field="operationType"  width="5"  replace="移动_1,联通_2,电信_3" query="true"></t:dgCol>
    <t:dgCol title="支付渠道名称" field="payChannelCode" replace="${sdkOperatorPayChannelReplace}" width="18" query="true"></t:dgCol>
    <t:dgCol title="游戏名称" field="gameId" replace="${gameReplace}" width="10" query="true"></t:dgCol>
    <t:dgCol title="用户ID"  field="uid"  width="12" query="true"></t:dgCol>
    <t:dgCol title="金额"  field="reqOrderAmount"  width="5" query="true"></t:dgCol>
    <t:dgCol title="发送状态" field="respStatus"  width="5"  replace="失败_0,成功_1" query="true"></t:dgCol>
    <t:dgCol title="手机IMSI" field="reqImsi" width="10" query="true"></t:dgCol>
    <t:dgCol title="发送端口" field="reqSendNumber" width="10"></t:dgCol>
    <t:dgCol title="发送指令" field="reqSendContent" width="10"></t:dgCol>
    <t:dgCol title="实际发送使用的Imsi" field="respImsi" width="15"></t:dgCol>
    <t:dgCol title="实际发送端口" field="respSendNumber" width="10"></t:dgCol>
    <t:dgCol title="实际发送指令" field="respSendContent" width="10"></t:dgCol>
    <t:dgCol title="手机IMEI号" field="imei" width="10" query="true"></t:dgCol>
    <t:dgCol title="手机ip地址" field="ipAddr" width="10"></t:dgCol>
    <t:dgCol title="手机mac地址" field="macAddr" width="10"></t:dgCol>
    <t:dgCol title="是否补点" field="additionalStatus" replace="否_0,是_1" width="8" query="true"></t:dgCol>
    <t:dgCol title="响应时间" field="respTime" width="10"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="sdkOperatorPayType.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="sdkOperatorPayType.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>