<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
$("a[icon='icon-search']").css("display","none");

}
);
</script>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="sdkGameUpgrateList" title="游戏升级" actionUrl="sdkUpgrade.do?datagrid2&gameId=${gameId}"  idField="upgradeId"  fit="true">
   <t:dgCol title="编号" field="id"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="upgradeId"  width="5" hidden="false"></t:dgCol>
   <t:dgCol title="渠道名称" field="channel"  width="10"  sortable="false" query="true"></t:dgCol>
   <t:dgCol title="升级类型" field="forceTag"  width="10"   replace="不升级_0,建议升级_1,强制升级_2" sortable="false"></t:dgCol>
   <t:dgCol title="安装文件" field="downUrl" width="10"  sortable="false"></t:dgCol>
   <t:dgCol title="versioncode"  field="versionCode"  width="10"  sortable="false"></t:dgCol>
   <t:dgToolBar title="设置" icon="icon-edit" funname="update" url="sdkUpgrade.do?addorupdate&gameId=${gameId}"  ></t:dgToolBar>
  </t:datagrid>
 </div>
</div>