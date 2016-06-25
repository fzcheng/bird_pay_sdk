<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="optChannelList" title="运营商支付渠道" actionUrl="optpaychannel.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" width="5" ></t:dgCol>
   <t:dgCol title="签约公司名称" field="signCorporation" width="10" query="true"></t:dgCol>
   <t:dgCol title="支付通道公司名称" field="corporation" width="10" query="true"></t:dgCol>
   <t:dgCol title="运营商" field="operatorType" replace="移动_1,联通_2,电信_3"  width="10" query="true"></t:dgCol>
   <t:dgCol title="短信方式" field="smsType" replace="短信指令_1,网络获取_2,SDK接入_3" width="10" query="true"></t:dgCol>
   <t:dgCol title="支付渠道名" field="channelName" width="10" query="true"></t:dgCol>
   <t:dgCol title="支付渠道号" field="channelCode" width="10" ></t:dgCol>
   <t:dgCol title="SDK最低版本" field="sdkMinVer" width="10" ></t:dgCol>
   <t:dgCol title="短信内容发送方式" field="smsContentType" replace="字符串_1,二进制_2,多条短信_3"  width="10"></t:dgCol>
   <t:dgCol title="每日限额" field="dayLimit" width="10" ></t:dgCol>
   <t:dgCol title="每月限额" field="monthLimit" width="10" ></t:dgCol>
   <t:dgCol title="单通道补点时间间隔" field="timeinterval" width="10" ></t:dgCol>
   <t:dgCol title="请求计费时间间隔" field="reqTimeinterval" width="10" ></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgFunOpt title="计费设置"  funname="setOptPayChannelPaying(id)" ></t:dgFunOpt>
    <t:dgFunOpt title="屏蔽设置"  funname="setOptPayChannelShielding(id)" ></t:dgFunOpt>
    <t:dgConfOpt title="删除" url="optpaychannel.do?del&id={id}" message="确认删除？" ></t:dgConfOpt>  
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="optpaychannel.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="optpaychannel.do?addorupdate"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>
<script type="text/javascript">
function setOptPayChannelPaying(id){
  var url="optpaychannel.do?editPaying&id="+id;
  var title="计费设置";
  createwindow(title,url);
}
function setOptPayChannelShielding(id){
  var url="optpaychannel.do?editShielding&id="+id;
  var title="屏蔽设置";
  createwindow(title,url);
}
</script>