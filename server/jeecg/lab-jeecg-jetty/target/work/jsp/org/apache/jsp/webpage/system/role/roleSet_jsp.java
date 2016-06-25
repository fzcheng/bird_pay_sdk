package org.apache.jsp.webpage.system.role;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class roleSet_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\t$('#functionid').tree({\r\n");
      out.write("\t\t\tcheckbox : true,\r\n");
      out.write("\t\t\turl : 'roleController.do?setAuthority&roleId=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${roleId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("',\r\n");
      out.write("\t\t\tonLoadSuccess : function(node) {\r\n");
      out.write("\t\t\t\texpandAll();\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tonClick: function(node){\r\n");
      out.write("\t\t\t\tvar isRoot =  $('#functionid').tree('getChildren', node.target);\r\n");
      out.write("\t\t\t\tif(isRoot==''){\r\n");
      out.write("\t\t\t\t\tvar roleId = $(\"#rid\").val();\r\n");
      out.write("\t\t\t\t\t$('#operationListpanel').panel(\"refresh\", \"roleController.do?operationListForFunction&functionId=\"+node.id+\"&roleId=\"+roleId);\r\n");
      out.write("\t\t\t\t}else {\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\"#functionListPanel\").panel(\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\ttitle :\"菜单列表\",\r\n");
      out.write("\t\t\t\t\ttools:[{iconCls:'icon-save',handler:function(){mysubmit();}}]\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t);\r\n");
      out.write("\t\t$(\"#operationListpanel\").panel(\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\ttitle :\"操作按钮列表\",\r\n");
      out.write("\t\t\t\t\ttools:[{iconCls:'icon-save',handler:function(){submitOperation();}}]\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t);\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction mysubmit() {\r\n");
      out.write("\t\tvar roleId = $(\"#rid\").val();\r\n");
      out.write("\t\tvar s = GetNode();\r\n");
      out.write("\t\tdoSubmit(\"roleController.do?updateAuthority&rolefunctions=\" + s + \"&roleId=\" + roleId);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction GetNode() {\r\n");
      out.write("\t\tvar node = $('#functionid').tree('getChecked');\r\n");
      out.write("\t\tvar cnodes = '';\r\n");
      out.write("\t\tvar pnodes = '';\r\n");
      out.write("\t\tvar pnode = null; //保存上一步所选父节点\r\n");
      out.write("\t\tfor ( var i = 0; i < node.length; i++) {\r\n");
      out.write("\t\t\tif ($('#functionid').tree('isLeaf', node[i].target)) {\r\n");
      out.write("\t\t\t\tcnodes += node[i].id + ',';\r\n");
      out.write("\t\t\t\tpnode = $('#functionid').tree('getParent', node[i].target); //获取当前节点的父节点\r\n");
      out.write("\t\t\t\twhile (pnode!=null) {//添加全部父节点\r\n");
      out.write("\t\t\t\t\tpnodes += pnode.id + ',';\r\n");
      out.write("\t\t\t\t\tpnode = $('#functionid').tree('getParent', pnode.target); \r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcnodes = cnodes.substring(0, cnodes.length - 1);\r\n");
      out.write("\t\tpnodes = pnodes.substring(0, pnodes.length - 1);\r\n");
      out.write("\t\treturn cnodes + \",\" + pnodes;\r\n");
      out.write("\t};\r\n");
      out.write("\t\r\n");
      out.write("\tfunction expandAll() {\r\n");
      out.write("\t\tvar node = $('#functionid').tree('getSelected');\r\n");
      out.write("\t\tif (node) {\r\n");
      out.write("\t\t\t$('#functionid').tree('expandAll', node.target);\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\t$('#functionid').tree('expandAll');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\tfunction selecrAll() {\r\n");
      out.write("\t\tvar node = $('#functionid').tree('getRoots');\r\n");
      out.write("\t\tfor ( var i = 0; i < node.length; i++) {\r\n");
      out.write("\t\t\tvar childrenNode =  $('#functionid').tree('getChildren',node[i].target);\r\n");
      out.write("\t\t\tfor ( var j = 0; j < childrenNode.length; j++) {\r\n");
      out.write("\t\t\t\t$('#functionid').tree(\"check\",childrenNode[j].target);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t    }\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction reset() {\r\n");
      out.write("\t\t$('#functionid').tree('reload');\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\t$('#selecrAllBtn').linkbutton({   \r\n");
      out.write("\t}); \r\n");
      out.write("\t$('#resetBtn').linkbutton({   \r\n");
      out.write("\t});   \r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("<div class=\"easyui-layout\" fit=\"true\">\r\n");
      out.write("<div region=\"center\" style=\"padding:1px;\">\r\n");
      out.write("<div class=\"easyui-panel\" style=\"padding:1px;\" fit=\"true\" border=\"false\" id=\"functionListPanel\">\r\n");
      out.write(" <input type=\"hidden\" name=\"roleId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${roleId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" id=\"rid\">\r\n");
      out.write(" <!-- update-begin--Author:anchao  Date:20130324 for：角色编辑全选、取消菜单 -->\r\n");
      out.write("  <a id = \"selecrAllBtn\" onclick=\"selecrAll();\">全选</a>\r\n");
      out.write("  <a id = \"resetBtn\"  onclick=\"reset();\">重置</a>\r\n");
      out.write(" <!-- update-end--Author:zhangdaihao  Date:20130205 for：角色编辑全选、取消菜单 -->\r\n");
      out.write(" <ul id=\"functionid\"></ul>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div region=\"east\" style=\"width:150px; overflow: hidden;\" split=\"true\" >\r\n");
      out.write("<div class=\"easyui-panel\" style=\"padding:1px;\" fit=\"true\" border=\"false\" id=\"operationListpanel\">\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
