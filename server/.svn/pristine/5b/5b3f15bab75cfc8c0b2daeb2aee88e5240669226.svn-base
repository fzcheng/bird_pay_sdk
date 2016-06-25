<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>发布</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="push.do?save&type=0">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
    <tr>
        <td align="right"><label class="Validform_label"> 标题: </label></td>
        <td class="value"><input class="inputxt" name="title" id="title" value="" datatype="s">
          <span class="Validform_checktip"></span></td>
      </tr>
      <tr>
			<td align="right">
				<label class="Validform_label">
				内容:
				</label>
			</td>
			<td class="value" style="width: 400px">
                <textarea rows="10" cols="80" id="content" name="content" datatype="*"  value=""></textarea>
			</td>
		</tr>
    </table>
  </t:formvalid>
</body>