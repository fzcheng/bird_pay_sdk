<script type="text/javascript">
$(function(){$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>(
	{
	<#if config_istree=="Y">treeField:'text',</#if>
	idField: '${id_field}',
	title: '${config_name}',
	url:'exCgAutoListController.do?datagrid&configId=${config_id}&field=${fields}${default_query_param}',
	updateUrl: 'exCgAutoListController.do?rowEdit',
	fit:true,
	fitColumns:true,
	pageSize: ${page_size},
	<#if config_ispagination =="Y">pagination:true,</#if>
	<#if config_ischeckbox=="Y">singleSelect:false,<#else>singleSelect:true,</#if>
	sortOrder:'asc',
	rownumbers:true,
	showFooter:true,
	frozenColumns:[[]],
	columns:[
		[	
			<#if config_istree=="Y">
				<#list config_fieldList  as x>  
					<#if x_index==0>{field:"${id_field}", title:"${x['field_title']}", hidden:true}, </#if>
					<#if x_index!=0>{field:"${x['field_id']}", title:"${x['field_title']}",<#if x['field_isShow'] == "N" || x['field_showType']=="file">hidden:true,</#if>
					<#if x['field_href'] != "">
					formatter:function(value,rec,index){var href='';href+="<a href='#' onclick=\"addOneTab('"+value+"','${x['field_href']}')\" ><u>"+value+"</u></a>";return href;},
					 <#elseif x['field_formatter'] != "" >		
						formatter: ${x['field_formatter']},
					<#else>
							
						    formatter: function(value, row, index){
						    	 var str =value; 
						    	 
						    	 return '<span title=\"' + value + '\" class=\"easyui-tooltip\">' + str + '</span>';
						    },
					</#if> width:100}, 
					</#if>
				</#list>
			<#else>
					<#if config_ischeckbox=="Y">{field:'ck',checkbox:true},</#if>
					<#list config_fieldList  as x>  
						 {
						 field:'${x['field_id']}',
						 title:'${x['field_title']}',
						 width:50,
						 editor: {
						 	type:'text'
						 },
						 <#if x['field_isShow'] == "N" || x['field_showType']=="file" || x['field_showType']=="hidden">hidden:true,</#if>
						 <#if x['field_href'] != "">
						 	formatter:function(value,rec,index){
						 		var href='';
						 		href+=applyHref(value,'${x['field_href']}',value,rec,index);
						 		return href;
						 	},
						 <#elseif x['field_formatter'] != "" >		
							formatter: ${x['field_formatter']},
						<#else>
							
						    formatter: function(value, row, index){
						    	 var str =value; 
						    	 
						    	 return '<span title=\"' + value + '\" class=\"easyui-tooltip\">' + str + '</span>';
						    },
						 </#if>
						 sortable:true
						 },
					</#list>
			</#if>
			{field:'opt',title:'操作',width:30,formatter:function(value,rec,index){
						if(!rec.${id_field}){return '';}
						var href='';
						href+="[<a href='#' onclick=delObj('exCgAutoListController.do?del&configId=${config_id}&id="+rec.${id_field}+"','${config_id}List')>";
						href+="删除</a>]";
						return href;
						}
			}
		]
	],
	 loadFilter: function (data) {
        for (var i = 0; i < data.rows.length; i++) {
               for (var att in data.rows[i]) {
                   if (typeof (data.rows[i][att]) == "string") {
                       data.rows[i][att] = data.rows[i][att].replace(/</g, "&lt;").replace(/>/g, "&gt;");
                   }
               }
        }
        return data;
    },
	onLoadSuccess:function(data){$("#${config_id}List").<#if config_istree=="Y">treegrid<#else>datagrid</#if>("clearSelections");},
	onClickRow:function(rowIndex,rowData) 
		{rowid=rowData.${id_field};gridname='${config_id}List';}
	});
	$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('getPager').pagination({beforePageText:'',afterPageText:'/{pages}',displayMsg:'{from}-{to}共{total}条',showPageList:true,showRefresh:true});
	$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('getPager').pagination({onBeforeRefresh:function(pageNumber, pageSize){ $(this).pagination('loading');$(this).pagination('loaded'); }});});
	function reloadTable(){	
		try{
		$('#'+gridname).datagrid('reload');
		$('#'+gridname).treegrid('reload');
		}catch(ex){
			//donothing
		}
	}
	function reload${config_id}List(){$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('reload');}
	function get${config_id}ListSelected(field){return getSelected(field);}
	function getSelected(field){var row = $('#'+gridname).<#if config_istree=="Y">treegrid<#else>datagrid</#if>('getSelected');if(row!=null){value= row[field];}else{value='';}return value;}
	function get${config_id}ListSelections(field){var ids = [];var rows = $('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('getSelections');for(var i=0;i<rows.length;i++){ids.push(rows[i][field]);}ids.join(',');return ids};
	function ${config_id}Listsearch(){var queryParams=$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('options').queryParams;$('#${config_id}Listtb').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>({url:'exCgAutoListController.do?datagrid&configId=${config_id}&field=${fields}'});}
	function dosearch(params){var jsonparams=$.parseJSON(params);$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>({url:'exCgAutoListController.do?datagrid&configId=${config_id}&field=${fields},',queryParams:jsonparams});}
	function ${config_id}Listsearchbox(value,name){var queryParams=$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('options').queryParams;queryParams[name]=value;queryParams.searchfield=name;$('#${config_id}List').<#if config_istree=="Y">treegrid<#else>datagrid</#if>('reload');}$('#${config_id}Listsearchbox').searchbox({searcher:function(value,name){${config_id}Listsearchbox(value,name);},menu:'#${config_id}Listmm',prompt:'请输入查询关键字'});
	function searchReset_${config_id}(name){ $("#"+name+"tb").find(":input").val("");${config_id}Listsearch();}
	
	function templateXls() {
		 window.location.href="exCgAutoListController.do?templateXls&configId=${config_id}";
	}
	function importXls() {
		add('Excel导入', 'exCgAutoListController.do?goImplXls&configId=${config_id}', "${config_id}Listt");
	}
	//将字段href中的变量替换掉
	function applyHref(tabname,href,value,rec,index){
		//addOneTab(tabname,href);
		var hrefnew = href;
		var re = "";
		var p1 = /\#\{(\w+)\}/g;
		try{
			var vars =hrefnew.match(p1); 
			for(var i=0;i<vars.length;i++){
				var keyt = vars[i];
				var p2 = /\#\{(\w+)\}/g;
				var key = p2.exec(keyt);
				hrefnew =  hrefnew.replace(keyt,rec[key[1]]);
			}
		}catch(ex){
		}
		
		if(hrefnew.indexOf("exCgAutoListController.do")>=0 || hrefnew.indexOf("addOneTab")>0){
			re += "<a href = '#' onclick=\"addOneTab('"+tabname+"','"+ hrefnew+"')\" ><u>"+value+"</u></a>";
		}else if(hrefnew.indexOf("exCgFormBuildController.do")>=0){
			re += "<a href = '#' onclick=\"createdetailwindow('"+tabname+"','"+ hrefnew+"')\" ><u>"+value+"</u></a>";
		}else{
			re += "<a href = '#' onclick=\"addOneTab('"+value+"','"+ hrefnew+"')\" ><u>"+value+"</u></a>";
		}
		return re;
	}
	
	function addTable(title,addurl,gname,width,height) {
		gridname=gname;
		createwindow(title, addurl,width,height);
	}

	/**
	 * 更新事件打开窗口
	 * @param title 编辑框标题
	 * @param addurl//目标页面地址
	 * @param id//主键字段
	 */
	function updateTable(title,url, id,id_field,width,height) {
			gridname=id;
			var rowData = $('#'+id).datagrid('getSelected');
			if (!rowData) {
				tip('请选择编辑项目');
				return;
			}
			url += '&'+id_field+'='+rowData[id_field];
			createwindow(title,url,width,height);
	}
	
	/**
	 * 查看详细事件打开窗口
	 * @param title 查看框标题
	 * @param addurl//目标页面地址
	 * @param id//主键字段
	 */
	function detailTable(title,url, id,id_field,width,height) {
		var rowData = $('#'+id).datagrid('getSelected');
	  	
		if (!rowData) {
			tip('请选择编辑项目');
			return;
		}
	    url += '&load=detail&'+id_field+'='+rowData[id_field]; 
		createdetailwindow(title,url,width,height);
	}

		
</script>
<table width="100%"   id="${config_id}List" toolbar="#${config_id}Listtb"></table>
<div id="${config_id}Listtb" style="padding:3px; height: auto">
<div name="searchColums">
<#if config_querymode == "group">
	<#list config_queryList  as x>
		<span style="display:-moz-inline-box;display:inline-block;">
		<span style="display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;">${x['field_title']}：</span>
		<#if x['field_queryMode']=="group">
			<input type="text" name="${x['field_id']}_begin"  style="width: 94px" value="${q["${x['field_id']}_begin"]?if_exists?html}" <#if x['field_type']=="Date">class="easyui-datebox"</#if> />
			<span style="display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;">~</span>
			<input type="text" name="${x['field_id']}_end"  style="width: 94px"   value="${q["${x['field_id']}_end"]?if_exists?html}"   <#if x['field_type']=="Date">class="easyui-datebox"</#if> />
		</#if>
		<#if x['field_queryMode']=="single">
			<#assign var_field_id=x['field_id'] />
			<#if  (x['field_dictlist']?size >0)>
				<select name = "${x['field_id']}" WIDTH="100" style="width: 104px">
				<option value = "">---请选择---</option>
				<#list x['field_dictlist']  as xd>
					<option value = "${xd['typecode']}" <#if xd['typecode']=="${q['${var_field_id}']?if_exists?html}">selected</#if> >${xd['typename']}</option>
				</#list>
				</select>
			</#if>
			<#if  (x['field_dictlist']?size <= 0)>
				<input type="text" name="${x['field_id']}"  style="width: 100px" value="${q["${x['field_id']}"]?if_exists?html}" <#if x['field_type']=="Date">class="easyui-datebox"</#if>  />
			</#if>
		</#if>
		</span>	
	</#list>
</#if>
	</div>
	<div style="height:30px;" class="datagrid-toolbar">
	<span style="float:left;" >
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addTable('${config_name}录入','exCgFormBuildController.do?ftlForm&tableName=${config_id}&${default_query_param}','${config_id}List',${style_width},${style_height})">${config_name}录入</a>
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="updateTable('${config_name}编辑','exCgFormBuildController.do?ftlForm&tableName=${config_id}','${config_id}List','${id_field}',${style_width},${style_height})">${config_name}编辑</a>
	<!--
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-put" onclick="add('${config_name}excel数据导入','excelTempletController.do?goImplXls&tableName=${config_id}','${config_id}List')">excel数据导入</a>
	<a href="excelTempletController.do?exportXls&tableName=${config_id}" class="easyui-linkbutton" plain="true" icon="icon-putout">excel模板</a>
	-->
	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detailTable('查看','exCgFormBuildController.do?ftlForm&tableName=${config_id}','${config_id}List','${id_field}',${style_width},${style_height})">查看</a>
	</span>
	
<#if  (config_queryList?size >0)>
	<#if config_querymode == "group" >
		<span style="float:right">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="${config_id}Listsearch()">查询</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="searchReset_${config_id}('${config_id}List')">重置</a>
		</span>
	</#if>
	<#if config_querymode == "single">
		<span style="float:right">
		<input id="${config_id}Listsearchbox" class="easyui-searchbox"  data-options="searcher:${config_id}Listsearchbox,prompt:'请输入关键字',menu:'#${config_id}Listmm'"></input>
		<div id="${config_id}Listmm" style="width:120px">
		<#list config_queryList  as x>
			<div data-options="name:'${x['field_id']}',iconCls:'icon-ok'  ">${x['field_title']}</div>
		</#list>
		</div>
		</span>
	</#if>
</#if>
	</div>
</div>
