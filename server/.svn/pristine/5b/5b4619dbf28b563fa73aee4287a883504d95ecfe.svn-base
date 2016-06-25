<script type="text/javascript">
$(function(){$('#${config_id}List').datagrid(
	{
	idField: 'id',
	title: '${config_name}',
	url:'exCgReportController.do?datagrid&configId=${config_id}',
	fit:true,
	fitColumns:true,
	pageSize: 10,
	pagination:true,
	singleSelect:true,
	sortOrder:'asc',
	rownumbers:true,
	showFooter:true,
	frozenColumns:[[]],
	columns:[
		[	
		<#list config_fieldList  as x>  
			{field:'${x['field_name']}',title:'${x['field_txt']}',width:50,sortable:'true'},
		</#list>
		]
	],
	onLoadSuccess:function(data){$("#${config_id}List").datagrid("clearSelections");},
	onClickRow:function(rowIndex,rowData)
		{rowid=rowData.id;gridname='${config_id}List';}
	});
	$('#${config_id}List').datagrid('getPager').pagination({beforePageText:'',afterPageText:'/{pages}',displayMsg:'{from}-{to}共{total}条',showPageList:true,pageList:[10,20,30],showRefresh:true});
	$('#${config_id}List').datagrid('getPager').pagination({onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination('loading');$(this).pagination('loaded'); }});});
	function reloadTable(){	
		try{
		$('#'+gridname).datagrid('reload');
		$('#'+gridname).treegrid('reload');
		}catch(ex){
			//donothing
		}
	}
	function reload${config_id}List(){$('#${config_id}List').datagrid('reload');}
	function get${config_id}ListSelected(field){return getSelected(field);}
	function getSelected(field){var row = $('#'+gridname).datagrid('getSelected');if(row!=null){value= row[field];}else{value='';}return value;}
	function get${config_id}ListSelections(field){var ids = [];var rows = $('#${config_id}List').datagrid('getSelections');for(var i=0;i<rows.length;i++){ids.push(rows[i][field]);}ids.join(',');return ids};
	function ${config_id}Listsearch(){var queryParams=$('#${config_id}List').datagrid('options').queryParams;$('#${config_id}Listtb').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});$('#${config_id}List').datagrid({url:'exCgReportController.do?datagrid&configId=${config_id}'});}
	function dosearch(params){var jsonparams=$.parseJSON(params);$('#${config_id}List').datagrid({url:'exCgReportController.do?datagrid&configId=${config_id},',queryParams:jsonparams});}
	function ${config_id}Listsearchbox(value,name){var queryParams=$('#${config_id}List').datagrid('options').queryParams;queryParams[name]=value;queryParams.searchfield=name;$('#${config_id}List').datagrid('reload');}$('#${config_id}Listsearchbox').searchbox({searcher:function(value,name){${config_id}Listsearchbox(value,name);},menu:'#${config_id}Listmm',prompt:'请输入查询关键字'});
	function searchReset_${config_id}(name){ $("#"+name+"tb").find(":input").val("");${config_id}Listsearch();}
	//导出
	function exportXls() {
		var submitUrl = "cgExportExcelController.do?exportXls&configId=${config_id}";
		var queryParams = "";
		$('#${config_id}Listtb').find('*').each(function(){
				queryParams+= "&"+$(this).attr('name')+"="+$(this).val();
			}
		);
		submitUrl+=queryParams;
		submitUrl = encodeURI(submitUrl);
		 window.location.href=submitUrl;
	}
</script>
<table width="100%"   id="${config_id}List" toolbar="#${config_id}Listtb"></table>
<div id="${config_id}Listtb" style="padding:3px; height: auto">
<div name="searchColums">
	<#list config_queryList  as x>
		<span style="display:-moz-inline-box;display:inline-block;">
		<span style="display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;">${x['field_txt']}：</span>
		<#if x['search_mode']=="group">
			<input type="text" name="${x['field_name']}_begin"  style="width: 94px"  <#if x['field_type']=="Date">class="easyui-datebox"</#if> />
			<span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
			<input type="text" name="${x['field_name']}_end"  style="width: 94px" <#if x['field_type']=="Date">class="easyui-datebox"</#if> />
		</#if>
		<#if x['search_mode']=="single">
				<#if  (x['field_dictlist']?size >0)>
				<select name = "${x['field_name']}" WIDTH="100" style="width: 104px">
				<option value = "">---请选择---</option>
				<#list x['field_dictlist']  as xd>
					<option value = "${xd['typecode']}">${xd['typename']}</option>
				</#list>
				</select>
			</#if>
			<#if  (x['field_dictlist']?size <= 0)>
				<input type="text" name="${x['field_name']}"  style="width: 100px" <#if x['field_type']=="Date">class="easyui-datebox"</#if>  />
			</#if>
		</#if>
		</span>	
	</#list>
	</div>
	<div style="height:30px;" class="datagrid-toolbar">
	<span style="float:left;" >
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="exportXls();">导出excel</a>
	</span>
	
<#if  (config_queryList?size >0)>
		<span style="float:right">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="${config_id}Listsearch()">查询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset_${config_id}('${config_id}List')">重置</a>
		</span>
</#if>
	</div>
</div>
