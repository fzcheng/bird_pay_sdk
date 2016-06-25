
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>车友汇后台管理系统</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="shortcut icon" href="images/favicon.ico">
<style type="text/css">
a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
</style>
<SCRIPT type="text/javascript">
	$(function() {
		
		// update-start--Author:duanqilu Date:20130601 for：添加在线用户显示
		$('#layout_jeecg_onlineDatagrid').datagrid({
			url : 'systemController.do?datagridOnline&field=id,ip,logindatetime,loginname,',
			title : '',
			iconCls : '',
			fit : true,
			fitColumns : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10 ],
			nowarp : false,
			border : false,
			idField : 'id',
			sortName : 'logindatetime',
			sortOrder : 'desc',
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				title : '登录名',
				field : 'loginname',
				width : 100,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return formatString('<span title="{0}">{1}</span>', value, value);
				}
			}, {
				title : 'IP',
				field : 'ip',
				width : 150,
				align : 'center',
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return formatString('<span title="{0}">{1}</span>', value, value);
				}
			}, {
				title : '登录时间',
				field : 'logindatetime',
				width : 150,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return formatString('<span title="{0}">{1}</span>', value, value);
				},
				hidden : true
			} ] ],
			onClickRow : function(rowIndex, rowData) {
			},
			onLoadSuccess : function(data) {
				$('#layout_jeecg_onlinePanel').panel('setTitle', '( ' + data.total + ' )人在线');
			}
		}).datagrid('getPager').pagination({
			showPageList : false,
			showRefresh : false,
			beforePageText : '',
			afterPageText : '/{pages}',
			displayMsg : ''
		});		
		
		$('#layout_jeecg_onlinePanel').panel({
			tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					$('#layout_jeecg_onlineDatagrid').datagrid('load', {});
				}
			} ]
		});

		window.setInterval(function() {
			$('#layout_jeecg_onlineDatagrid').datagrid('load', {});
		}, 1000 * 60 * 10);
		
				
		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});

	});
</SCRIPT>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<!-- 顶部-->
	<div region="north" border="false" title="版本: ${version }" style="BACKGROUND:#FFFFFF;height: 85px; padding: 1px; overflow: hidden;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left" style="vertical-align:text-bottom"><img src="indexContentController.do?getLogo" style="width:819px;height:59px" /></td>
				<td align="right" nowrap>
				
					<table>
						<tr>
							<td valign="top" height="50"><span style="color: #CC33FF">当前用户:</span><span style="color: #666633">(${userName }) <span style="color: #CC33FF">职务</span>:<span style="color: #666633">${roleName }</span>
							</td>
						</tr>
						<tr><td>
							<div style="position: absolute; right: 0px; bottom: 0px;">
								<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a>
							</div>
							<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
								<div onclick="openwindow('用户信息','userController.do?userinfo')">个人信息</div>
								<div class="menu-sep"></div>
								<div onclick="add('修改密码','userController.do?changepassword')">修改密码</div>
								
								<!-- update-start--Author:周勇  Date:2013-07-29：新增修改首页风格功能  -->
						        <div class="menu-sep"></div>
						        <div onclick="add('修改首页风格','userController.do?changestyle')">首页风格 </div>
						        <!-- update-start--Author:周勇  Date:2013-07-29：新增修改首页风格功能  -->
						        
						        <div class="menu-sep"></div>
								<div onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);">退出系统</div>
								
							</div>
						</td></tr>
					</table></td>
				<td align="right">&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>
	</div>
	<!-- 左侧-->
	<div region="west" split="true" href="loginController.do?left" title="导航菜单" style="width: 150px; padding: 1px;"></div>
	<!-- 中间-->
	<div id="mainPanle" region="center" style="overflow: hidden;">
		<div id="maintabs" class="easyui-tabs" fit="true" border="false">
			 <div class="easyui-tab" title="${tabTitle }" href=" ${tab}" style="padding:2px; overflow: hidden;"></div>
			<c:if test="${map=='1'}">
				<div class="easyui-tab" title="地图" style="padding: 1px; overflow: hidden;">
					<iframe name="myMap" id="myMap" scrolling="no" frameborder="0" src="mapController.do?map" style="width: 100%; height: 99.5%;"></iframe>
				</div>
			</c:if>
		</div>
	</div>
	<!-- 右侧 -->
	<div collapsed="true" region="east" iconCls="icon-reload" title="辅助工具" split="true" style="width: 190px;">
		<div id="tabs" class="easyui-tabs" border="false" style="height: 240px">
			<div title="日历" style="padding: 0px; overflow: hidden; color: red;">
				<div id="layout_east_calendar"></div>
			</div>
		</div>
		<!-- update-start--Author:duanqilu Date:20130601 for：添加在线用户显示 -->
		<div id="layout_jeecg_onlinePanel" data-options="fit:true,border:false" title="用户在线列表">
			<table id="layout_jeecg_onlineDatagrid"></table>
		</div>
		<!-- update---Author:duanqilu Date:20130601 for：添加在线用户显示 -->
	</div>
	<!-- 底部 -->
	<div region="south" border="false" style="height: 25px; overflow: hidden;">
		<div align="center" style="color: #CC99FF; padding-top: 2px">
			推荐谷歌浏览器，获得更快响应速度
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>

	</div>

</body>
</html>