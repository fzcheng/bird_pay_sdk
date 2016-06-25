<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
 <script>
$(document).ready(function(){
$("input[name='statDay_begin']").attr("class","easyui-datebox");
$("input[name='statDay_end']").attr("class","easyui-datebox");

}
);
function downfile(url){
	window.document.location.href=url+"&statDay_begin="+$("input[name='statDay_begin']").val()+"&statDay_end="+$("input[name='statDay_end']").val();
}
$("input[name='statDay_begin']").datebox({
	onSelect: function(date){
		//alert(date);
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		
	}
});

$("input[name='statDay_end']").datebox({
	onSelect: function(date){
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
	}
});
</script>
  <t:datagrid name="channelPartnerList" title="渠道商列表"  actionUrl="channelReport.do?getList&partnerId=${partnerId}" fit="true" idField="statDay"  queryMode="group">
   <t:dgCol title="日期" field="statDay"  width="5"  query="true"  queryMode="group" ></t:dgCol>
   <t:dgCol title="渠道商ID" field="channelPartnetId"  width="10"></t:dgCol>
   <t:dgCol title="渠道商名称"  field="channelPartnerName"  width="10"></t:dgCol>   
   <t:dgCol title="游戏名称"  field="gameId"  width="10"></t:dgCol>  
   <t:dgCol title="渠道编号"  field="channel"  width="10"></t:dgCol> 
   <t:dgCol title="收入"  field="income"  width="10"></t:dgCol> 
   <t:dgCol title="注册用户数"  field="regNum"  width="10"></t:dgCol> 
      <t:dgCol title="付费用户数"  field="rechargeNum"  width="10"></t:dgCol>
    <t:dgToolBar title="导出Excel" icon="icon-add" funname="downfile('channelReport.do?outputExcel&partnerId=${partnerId}')" url=""></t:dgToolBar>
  </t:datagrid>
 </div>
</div>