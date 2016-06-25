<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);

function openAdMissionByType2List(adId){
	addOneTab('广告任务列表','adMission.do?list&showType=2&adId='+adId);
}
function openAdMissionByType4List(adId){
	addOneTab('广告任务列表','adMission.do?list&showType=4&adId='+adId);
}
function openAdMissionByType5List(adId){
	addOneTab('广告任务列表','adMission.do?list&showType=5&adId='+adId);
}
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="adList" title="广告信息管理列表" actionUrl="ad.do?datagrid&adverId=${adverId}"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="NO." field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="商户id" field="apid"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="广告计划id" field="adId"  width="10"></t:dgCol>
   <t:dgCol title="计划别名" field="adname"  width="10"></t:dgCol>
   <t:dgCol title="开始时间" field="beginTime"  formatter="yyyy-MM-dd hh:mm:ss" width="15"></t:dgCol>
   <t:dgCol title="结束时间" field="endTime"  formatter="yyyy-MM-dd hh:mm:ss" width="15"></t:dgCol>
    <t:dgCol title="广告key" field="adKey"  width="10"></t:dgCol>
   <t:dgCol title="计划投放资金" field="fund"  width="10"></t:dgCol>
   <t:dgCol title="现有消耗产出资金" field="fundOutput"  width="10"></t:dgCol>
   <t:dgCol title="计划描述" field="detail"  width="10"></t:dgCol>
   <t:dgFunOpt title="管理插屏(图片)广告列表"  funname="openAdMissionByType2List(adId)" ></t:dgFunOpt>
   <t:dgFunOpt title="管理积分墙广告列表"  funname="openAdMissionByType4List(adId)" ></t:dgFunOpt>
   <t:dgFunOpt title="管理插屏(视频)广告列表"  funname="openAdMissionByType5List(adId)" ></t:dgFunOpt>
   <t:dgCol title="操作" field="opt"></t:dgCol>    
   <t:dgToolBar title="增加" icon="icon-add" funname="add" url="ad.do?addorupdate&adverId=${adverId}"></t:dgToolBar>
   <t:dgToolBar title="修改" icon="icon-edit" funname="update" url="ad.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>