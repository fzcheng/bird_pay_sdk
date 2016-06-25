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
	url=url+"&createTime_begin="+$("input[name='createTime_begin']").val()+"&createTime_end="+$("input[name='createTime_end']").val();
	if($("input[name='orderNo']").val()==""||undefined || null){
	}else {
	url=url+"&orderNo="+$("input[name='orderNo']").val();
	}
	if($("select[name=sdkPayment_payType]").val()==""||undefined || null){
	}else {
	url=url+"&sdkPayment_payType="+$("select[name=sdkPayment_payType]").val();
	}
	if($("input[name='amount']").val()==""||undefined || null){
	}else {
	url=url+"&amount="+$("input[name='amount']").val();
	}
	if($("select[name=status]").val()==""||undefined || null){
	}else {
	url=url+"&status="+$("select[name=status]").val();
	}
	if($("input[name='sdkUser_uid']").val()==""||undefined || null){
	}else {
	url=url+"&sdkUser_uid="+$("input[name='sdkUser_uid']").val();
	}
	if($("select[name=sdkGame_gameId]").val()==""||undefined || null){
	}else {
	url=url+"&sdkGame_gameId="+$("select[name=sdkGame_gameId]").val();
	}
	if($("select[name=notifyStatus]").val()==""||undefined || null){
	}else {
	url=url+"&notifyStatus="+$("select[name=notifyStatus]").val();
	}
	if($("input[name='payId']").val()==""||undefined || null){
	}else {
	url=url+"&payId="+$("input[name='payId']").val();
	}
	//alert("the url is :"+url);
	window.document.location.href=url;
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

$("input[name='orderNo']").val();

$("select[name=sdkPayment_payType]").val();

$("input[name='amount']").val();

$("select[name=status]").val();

$("input[name='sdkUser_uid']").val();

$("select[name=sdkGame_gameId]").val();

$("select[name=notifyStatus]").val();

$("input[name='payId']").val();
</script>
 
  <t:datagrid name="sdkOrderNew" title="订单总表" actionUrl="sdkOrderNew.do?datagrid"  idField="id"  fit="true" queryMode="group">
    <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss" queryMode="group" width="18" query="true"></t:dgCol>
    <t:dgCol title="订单号" field="orderNo"  width="17" query="true"></t:dgCol>
    <t:dgCol title="运营商" field="operationType" replace="移动_1,联通_2,电信_3" width="5"></t:dgCol>
    <t:dgCol title="支付渠道名" field="channelCode" width="8"></t:dgCol>
    <t:dgCol title="省份" field="provinceno" replace="${sdkProvinceAddrsReplace}" width="8"></t:dgCol>
    <t:dgCol title="手机号码" field="mobilephoneNumber" width="8"></t:dgCol>
    <t:dgCol title="订单名称" field="orderName" width="10"></t:dgCol>
    <t:dgCol title="支付方式" field="sdkPayment_payType" replace="${sdkPaymentReplace}" width="8" query="true"></t:dgCol>
    <t:dgCol title="金额"  field="amount"  width="5" query="true"></t:dgCol>
   	<t:dgCol title="订单状态" field="status"  width="10"  replace="订单创建_0,支付成功_1,等待结果_2,支付失败_3,订单异常_4,省份屏蔽_5,余额不足_6,网络异常_7,计费点无效_8,签名鉴权无效_9,请求频繁_10,黑名单_11,日上限_12,月上限_13,未知原因错误_14,飞行模式_15,长短信发送解析失败_16,不在服务区_17,超时_18,订单取消_19,获取短信内容端口异常_20,获取验证码超时_21" query="true"></t:dgCol>
   	<t:dgCol title="状态描述"  field="statusDetail"  width="10"></t:dgCol>
   	<t:dgCol title="原始状态码"  field="originalcode"  width="7"></t:dgCol>
   	<t:dgCol title="渠道号"  field="channel"  width="7"></t:dgCol>
   	<t:dgCol title="sdk版本号"  field="sdkver"  width="7"></t:dgCol>
   	<t:dgCol title="用户ID"  field="sdkUser_uid"  width="8" query="true"></t:dgCol>
   	<t:dgCol title="游戏名称" field="sdkGame_gameId" replace="${gameReplace}" width="10" query="true"></t:dgCol>
   	<t:dgCol title="游戏商名称" field="sdkGameCp_cpId" replace="${sdkGameCpsReplace}" width="10"></t:dgCol>
    <t:dgCol title="游戏服务商扩展信息"  field="cpExt"  width="5"></t:dgCol>
    <t:dgCol title="通知状态" field="notifyStatus" width="5" replace="尚未通知_0,通知成功_1,通知失败_2,无需通知_3" query="true"/>
    <t:dgCol title="上一次通知的时间" field="notifyTime" formatter="yyyy-MM-dd hh:mm:ss" width="17"></t:dgCol>
    <t:dgCol title="payId" field="payId"  width="8" query="true"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgToolBar title="导出Excel" icon="icon-add" funname="downfile('sdkOrderNew.do?outputExcel')" url=""></t:dgToolBar>
  </t:datagrid>
 </div>
</div>