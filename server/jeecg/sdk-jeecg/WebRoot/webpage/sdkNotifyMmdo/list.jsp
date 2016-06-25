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
	/* if($("input[name='createTime_begin']").val()==""||undefined || null){
	}else {
	url=url+"&createTime_begin="+$("input[name='createTime_begin']").val();
	}
	if($("input[name='createTime_end']").val().val()==""||undefined || null){
	}else {
	url=url+"&createTime_end="+$("input[name='createTime_end']").val();
	} */
	url=url+"&createTime_begin="+$("input[name='createTime_begin']").val()+"&createTime_end="+$("input[name='createTime_end']").val();
	if($("input[name='orderNo']").val()==""||undefined || null){
	}else {
	url=url+"&orderNo="+$("input[name='orderNo']").val();
	}
	if($("select[name=operationType]").val()==""||undefined || null){
	}else {
	url=url+"&operationType="+$("select[name=operationType]").val();
	}
	if($("select[name=payChannelCode]").val()==""||undefined || null){
	}else {
	url=url+"&payChannelCode="+$("select[name=payChannelCode]").val();
	}
	if($("select[name=gameId]").val()==""||undefined || null){
	}else {
	url=url+"&gameId="+$("select[name=gameId]").val();
	}
	if($("input[name='amount']").val()==""||undefined || null){
	}else {
	url=url+"&amount="+$("input[name='amount']").val();
	}
	if($("select[name=additionalStatus]").val()==""||undefined || null){
	}else {
	url=url+"&additionalStatus="+$("select[name=additionalStatus]").val();
	}
	if($("input[name='mobile']").val()==""||undefined || null){
	}else {
	url=url+"&mobile="+$("input[name='mobile']").val();
	}
	if($("input[name='linkid']").val()==""||undefined || null){
	}else {
	url=url+"&linkid="+$("input[name='linkid']").val();
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

$("select[name=operationType]").val();

$("select[name=payChannelCode]").val();

$("select[name=gameId]").val();

$("input[name='amount']").val();

$("select[name=additionalStatus]").val();

$("input[name='mobile']").val();

$("input[name='linkid']").val();

</script>
 
  <t:datagrid name="sdkNotifyMmdo" title="运营商下行回调表" actionUrl="sdkNotifyMmdo.do?datagrid"  idField="id"  fit="true" queryMode="group">
    <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss" queryMode="group" width="18" query="true"></t:dgCol>
    <t:dgCol title="订单号" field="orderNo"  width="15" query="true"></t:dgCol>
    <t:dgCol title="状态" field="notifyStatus" replace="失败_0,成功_1" width="8" query="true"></t:dgCol>
    <t:dgCol title="运营商类型" field="operationType"  width="5"  replace="移动_1,联通_2,电信_3" query="true"></t:dgCol>
     <t:dgCol title="支付渠道名称" field="payChannelName" width="15" ></t:dgCol>
    <t:dgCol title="游戏名称" field="gameId" replace="${gameReplace}" width="10" query="true"></t:dgCol>
    <t:dgCol title="单价金额"  field="amount"  width="5" query="true"></t:dgCol>
    <t:dgCol title="是否补点" field="additionalStatus" replace="否_0,是_1" width="8" query="true"></t:dgCol>
    <t:dgCol title="手机号码" field="mobile" width="15" query="true"></t:dgCol>
    <t:dgCol title="外部交易编号" field="linkid" width="19" query="true"></t:dgCol>
    <t:dgCol title="状态描述" field="statusDetail" width="8"></t:dgCol>
    <t:dgCol title="状态码" field="originalcode" width="8"></t:dgCol>
    <t:dgCol title="业务代码" field="spid" width="10"></t:dgCol>
    <t:dgCol title="上行指令" field="cmd" width="10"></t:dgCol>
    <t:dgCol title="端口" field="spnum" width="10"></t:dgCol>
    <t:dgCol title="省份" field="key" width="6"></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgToolBar title="导出Excel" icon="icon-add" funname="downfile('sdkNotifyMmdo.do?outputExcel')" url=""></t:dgToolBar>
  </t:datagrid>
 </div>
</div>