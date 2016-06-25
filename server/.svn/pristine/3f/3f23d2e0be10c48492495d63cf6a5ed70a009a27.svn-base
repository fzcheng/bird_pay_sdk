<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
 <div region="center" style="padding:1px;">
  <t:datagrid name="mmdoSettingList" title="方案设置列表" actionUrl="mmdoSetting.do?datagrid"  idField="id"  fit="true" queryMode="group">
   <t:dgCol title="编号" field="id"  width="5" ></t:dgCol>
   <t:dgCol title="游戏名" field="gameId" replace="${gameReplace}" width="10" query="true"></t:dgCol>
   <t:dgCol title="支付渠道" field="operatorPayChannelId" replace="${channelReplace}"  width="10" query="true"></t:dgCol>
   <t:dgCol title="发送端口" field="number"  width="10" ></t:dgCol>
   <t:dgCol title="发送内容前缀" field="sendContent"  width="10" ></t:dgCol>
    <t:dgCol title="金额" field="amount"  width="10" query="true"></t:dgCol>
    <t:dgCol title="实际发送金额" field="realAmount"  width="10" ></t:dgCol>
    <t:dgCol title="屏蔽关键字" field="shieldKeyword"  width="10"></t:dgCol>
    <t:dgCol title="屏蔽端口" field="shieldNumber"  width="10"></t:dgCol>
    <t:dgCol title="发送间隔(s)" field="interval"  width="10"></t:dgCol>     
    <t:dgCol title="优先级" field="idx"  width="5"></t:dgCol>
    <t:dgCol title="道具ID" field="propsid"  width="10"></t:dgCol>
    <t:dgCol title="状态" field="useStatus" replace="使用_1,停用_0" width="5" query="true"></t:dgCol>
    <t:dgCol title="计费代码配置的原本游戏" field="originalGameId" replace="${gameReplace}" width="10"></t:dgCol>
    <t:dgCol title="代计费原游戏名" field="originalGameName"  width="10"></t:dgCol>
    <t:dgCol title="支付中提示语" field="sendingtip"  width="10"></t:dgCol>
    <t:dgCol title="统计次道具使用次数的标志符" field="tjpropsname"  width="7"></t:dgCol>
    <t:dgCol title="计费提示框弹出状态" field="chargetip" replace="使用_1,停用_0" width="5" query="true"></t:dgCol>
    <t:dgCol title="计费成功提示框弹出状态" field="chargesuceesstip" replace="使用_1,停用_0" width="5" query="true"></t:dgCol>
    <t:dgCol title="loading提示框弹出秒数" field="loadingtipmin" width="5" ></t:dgCol>
    <t:dgCol title="失败提示框弹出状态" field="chargefailtip" replace="使用_1,停用_0" width="5" ></t:dgCol>
    <t:dgCol title="是否补点计费" field="additional" replace="使用_1,停用_0" width="5" query="true"></t:dgCol>
    <t:dgCol title="补点计费列表" field="addList" width="5" ></t:dgCol>
    <t:dgCol title="操作" field="opt"></t:dgCol>
    <t:dgConfOpt title="删除" url="mmdoSetting.do?del&id={id}" message="此操作将会删除与之相关的所有数据，确认删除？" ></t:dgConfOpt>
    <t:dgToolBar title="添加" icon="icon-add" funname="add" url="mmdoSetting.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" funname="update" url="mmdoSetting.do?addorupdate"></t:dgToolBar>
    <t:dgToolBar title="设置限额" icon="icon-add" funname="add" url="mmdoSetting.do?setLimit"></t:dgToolBar>
  </t:datagrid>
 </div>
</div>