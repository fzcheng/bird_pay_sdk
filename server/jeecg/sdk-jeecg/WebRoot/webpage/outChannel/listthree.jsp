<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">

 <div region="center" style="padding:1px;">
  <script>
$(document).ready(function(){
$("input[name='inputTime_begin']").attr("class","easyui-datebox");
$("input[name='inputTime_end']").attr("class","easyui-datebox");

}
);

$("input[name='inputTime_begin']").datebox({
	onSelect: function(date){
		//alert(date);
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
		
	}
});

$("input[name='inputTime_end']").datebox({
	onSelect: function(date){
		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
	}
});
</script>
  <t:datagrid name="outChannelDetailList" title="外部渠道详情列表" queryMode="group"  actionUrl="outChannel.do?getThreeList" fit="true" >
   <t:dgCol title="渠道商名称" field="sdkOutChannel_name"  width="10"></t:dgCol>
   <t:dgCol title="日期" field="inputTime" formatter="yyyy-MM-dd"  width="10" queryMode="group" query="true"></t:dgCol>
   <t:dgCol title="注册数" field="regNumber"  width="10"></t:dgCol>   
   <t:dgToolBar title="查看" icon="icon-search" funname="detail" url="outChannel.do?addorupdateOutChannelDetail"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>