<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>卡商日报数据添加</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<script type="text/javascript">
 function myformatter(date){
 var y = date.getFullYear();
 var m = date.getMonth()+1;
 var d = date.getDate();
 return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
 }
 function myparser(s){
 if (!s) return new Date();
 var ss = (s.split('-'));
 var y = parseInt(ss[0],10);
 var m = parseInt(ss[1],10);
 var d = parseInt(ss[2],10);
 if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
 return new Date(y,m-1,d);
 }else{
 return new Date();
 }
 }
 </script>

<body>
	<t:formvalid formid="formobj" layout="table" dialog="true" action="sdkDailyStatGameCard.do?save">
		<input id="id" name="id" type="hidden" value="${sdkDailyStatGameCard.id}">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">           
			
			<tr>
				<td align="center"><label class="Validform_label"> 日期: </label></td>
				<td class="value" style="width: 400px"><input name="daily" id="daily" class="easyui-datebox" required="true" data-options="formatter:myformatter,parser:myparser"></input></td>
			</tr>
			
			<tr>
             <td align="center"><label class="Validform_label"> 游戏: </label>
             </td>
             <td class="value">
              <select id="gameId"  name="gameId">
               <c:forEach items="${games}" var="game">
                <option value="${game.gameId}" <c:if test="${game.gameId==sdkDailyStatGameCard.gameId}">selected="selected"</c:if> >
                 ${game.name}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
			
			<tr>
             <td align="center"><label class="Validform_label"> 渠道号: </label>
             </td>
             <td class="value">
              <select id="operatorPayChannelId"  name="operatorPayChannelId" required="true">
              <option value="">请选择</option>
               <c:forEach items="${payChannels}" var="payChannel">
                <option value="${payChannel.id}" <c:if test="${payChannel.id==sdkDailyStatGameCard.operatorPayChannelId}">selected="selected"</c:if> >
                 ${payChannel.channelName}
                </option>
               </c:forEach>
              </select>
              <span class="Validform_checktip"></span>
             </td>
            </tr>
			
			<tr>
					<td align="center">
						<label class="Validform_label">
						手机运营商类型
						</label>
					</td>
					<td class="value">
					<input type="radio"  value="1" name="operatorType" <c:if test="${sdkDailyStatGameCard.operatorType==1}" >checked</c:if> >中国移动 &nbsp;
					<input type="radio"  value="2" name="operatorType" <c:if test="${sdkDailyStatGameCard.operatorType==2}" >checked</c:if>>中国联通 &nbsp;
					<input type="radio"  value="3" name="operatorType" <c:if test="${sdkDailyStatGameCard.operatorType==3}" >checked</c:if> >中国电信 &nbsp;
					<span class="Validform_checktip"></span>
						 
					</td>
				</tr>
				
			<tr>
				<td align="center"><label class="Validform_label"> 渠道编号: </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="channelNum" id="channelNum" value="${sdkDailyStatGameCard.channelNum}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
			
			<tr>
				<td align="center"><label class="Validform_label"> 计费成功金额(元): </label></td>
				<td class="value" style="width: 400px"><input class="longInput" name="totalAmount" id="totalAmount" value="${sdkDailyStatGameCard.totalAmount}" datatype="*"> <span class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>