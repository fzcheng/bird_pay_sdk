package org.apache.jsp.webpage.compresapk;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class first_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/context/mytags.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_formvalid_layout_formid_dialog_beforeSubmit;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_t_base_type_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_t_formvalid_layout_formid_dialog_beforeSubmit = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_t_base_type_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_t_formvalid_layout_formid_dialog_beforeSubmit.release();
    _jspx_tagPool_t_base_type_nobody.release();
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("  <title>上传文件</title>\r\n");
      if (_jspx_meth_t_base_0(_jspx_page_context))
        return;
      out.write("  \r\n");
      out.write(" </head>\r\n");
      out.write(" <body style=\"overflow-y: hidden\" scroll=\"no\">\r\n");
      out.write("  ");
      if (_jspx_meth_t_formvalid_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write(" </body>");
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

  private boolean _jspx_meth_t_base_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:base
    jeecg.ext.online.ftl.ExHtmlLinkTag _jspx_th_t_base_0 = (jeecg.ext.online.ftl.ExHtmlLinkTag) _jspx_tagPool_t_base_type_nobody.get(jeecg.ext.online.ftl.ExHtmlLinkTag.class);
    _jspx_th_t_base_0.setPageContext(_jspx_page_context);
    _jspx_th_t_base_0.setParent(null);
    _jspx_th_t_base_0.setType("jquery,easyui,tools");
    int _jspx_eval_t_base_0 = _jspx_th_t_base_0.doStartTag();
    if (_jspx_th_t_base_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_base_type_nobody.reuse(_jspx_th_t_base_0);
      return true;
    }
    _jspx_tagPool_t_base_type_nobody.reuse(_jspx_th_t_base_0);
    return false;
  }

  private boolean _jspx_meth_t_formvalid_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:formvalid
    org.jeecgframework.tag.core.easyui.FormValidationTag _jspx_th_t_formvalid_0 = (org.jeecgframework.tag.core.easyui.FormValidationTag) _jspx_tagPool_t_formvalid_layout_formid_dialog_beforeSubmit.get(org.jeecgframework.tag.core.easyui.FormValidationTag.class);
    _jspx_th_t_formvalid_0.setPageContext(_jspx_page_context);
    _jspx_th_t_formvalid_0.setParent(null);
    _jspx_th_t_formvalid_0.setFormid("formobj");
    _jspx_th_t_formvalid_0.setLayout("div");
    _jspx_th_t_formvalid_0.setDialog(true);
    _jspx_th_t_formvalid_0.setBeforeSubmit("upload");
    int _jspx_eval_t_formvalid_0 = _jspx_th_t_formvalid_0.doStartTag();
    if (_jspx_eval_t_formvalid_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("  <input type=\"hidden\" name=\"fileName\" id=\"fileName\"/>\n");
        out.write("\t\t\t<table style=\"width: 600px;\" cellpadding=\"0\" cellspacing=\"1\" class=\"formtable\">\r\n");
        out.write("\t\t\t\t \r\n");
        out.write("\t\t\t\t<tr>\r\n");
        out.write("\t     <td align=\"right\" height=\"60\">\r\n");
        out.write("\t      <span class=\"filedzt\"> 文件(注意: apk格式)：</span>\r\n");
        out.write("\t     </td>\r\n");
        out.write("\t     <td class=\"value\" style=\"width:400px;\">\r\n");
        out.write("\r\n");
        out.write("    \r\n");
        out.write("\t       <fieldset class=\"step\">\t\t    \r\n");
        out.write("\t\t     <div class=\"form\" style=\"width:400px;\">\r\n");
        out.write("\t\t     <link rel=\"stylesheet\" href=\"plug-in/uploadify/uploadify.css\" type=\"text/css\"></link>\r\n");
        out.write("\t\t     <script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify.js\"></script>\r\n");
        out.write("\t\t     <script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>\r\n");
        out.write("\t\t     <script type=\"text/javascript\">\r\n");
        out.write("\t\t     \t\tvar flag = false;\r\n");
        out.write("\t\t     \t\tvar fileitem=\"\";\r\n");
        out.write("\t\t     \t\tvar fileKey=\"\";\r\n");
        out.write("\t\t     \t\tvar serverMsg=\"\";\r\n");
        out.write("\t\t     \t\tvar m = new Map();\r\n");
        out.write("\t\t     \t\t$(function(){\r\n");
        out.write("\t\t     \t\t\t$('#file_upload').uploadify({\r\n");
        out.write("\t\t     \t\t\t\t\tbuttonText:'上传文件',\r\n");
        out.write("\t\t     \t\t\t\t\tauto:false,\r\n");
        out.write("\t\t     \t\t\t\t\tprogressData:'speed',\r\n");
        out.write("\t\t     \t\t\t\t\tmulti:true,\r\n");
        out.write("\t\t     \t\t\t\t\theight:25,\r\n");
        out.write("\t\t     \t\t\t\t\tqueueSizeLimit:30,\r\n");
        out.write("\t\t     \t\t\t\t\toverrideEvents:['onDialogClose'],\r\n");
        out.write("\t\t     \t\t\t\t\tfileTypeDesc:'文件格式:',\r\n");
        out.write("\t\t     \t\t\t\t\tqueueID:'filediv',\r\n");
        out.write("\t\t     \t\t\t\t\tfileTypeExts:'*.apk',\r\n");
        out.write("\t\t     \t\t\t\t\tfileSizeLimit:'1000MB',\r\n");
        out.write("\t\t     \t\t\t\t\tswf:'plug-in/uploadify/uploadify.swf',\t\r\n");
        out.write("\t\t     \t\t\t\t\tuploader:'makeApk.do?saveFiles',\r\n");
        out.write("\t\t     \t\t\t\t\tonUploadStart : function(file) { \r\n");
        out.write("\t\t     \t\t\t\t\t\t\tvar id=$('#id').val();\r\n");
        out.write("\t\t     \t\t\t\t\t\t\t$('#file_upload').uploadify(\"settings\", \"formData\", {'id':id});\r\n");
        out.write("\t\t     \t\t\t\t\t} ,\r\n");
        out.write("\t\t     \t\t\t\t\tonQueueComplete : function(queueData) { \r\n");
        out.write("\t\t\t\t\t\t\t\t\r\n");
        out.write("\t\t\t\t\t\t\t\t\twindow.document.location.href='makeApk.do?second&fileName='+$(\"#fileName\").val();\r\n");
        out.write("\t\t\t\t\t\t\t\t\t//var dd=window.document.location.href;\r\n");
        out.write("\t\t     \t\t\t\t\t\t//var win = frameElement.api.opener;\r\n");
        out.write("\t\t     \t\t\t\t\t\t//win.reloadTable();\r\n");
        out.write("\t\t     \t\t\t\t\t\t//win.tip(serverMsg);\r\n");
        out.write("\t\t     \t\t\t\t\t\t//frameElement.api.close();\r\n");
        out.write("\t\t     \t\t\t\t\t\t//add('打包','makeApk.do?second&fileName='+$(\"#fileName\").val(),'');\r\n");
        out.write("\t\t     \t\t\t\t\t\t //seft.location.href='makeApk.do?second&fileName='+$(\"#fileName\").val();\r\n");
        out.write("\t\t     \t\t\t\t\t\t /* $.post(\"makeApk.do?second\", $(\"#formobj\").serialize())\r\n");
        out.write("\t\t     \t\t\t\t\t\t    .success(function() { \r\n");
        out.write("\t\t\t     \t\t\t\t\t\t    alert(\"dsd\");\r\n");
        out.write("\t\t\t     \t\t\t\t\t\t    \r\n");
        out.write("\t\t     \t\t\t\t\t\t    })\r\n");
        out.write("\t\t     \t\t\t\t\t\t    .error(function() { \r\n");
        out.write("\t\t     \t\t\t\t\t\t    \talert(\"error\"); \r\n");
        out.write("\t\t     \t\t\t\t\t\t    })\r\n");
        out.write("\t\t     \t\t\t\t\t\t    .complete(function() { \r\n");
        out.write("\t\t     \t\t\t\t\t\t    \talert(\"complete\"); \r\n");
        out.write("\t\t     \t\t\t\t\t\t    });  */\r\n");
        out.write("\t\t     \t\t\t\t\t},\r\n");
        out.write("\t\t     \t\t\t\t\tonUploadSuccess : function(file, data, response) {\r\n");
        out.write("\t\t     \t\t\t\t\t\tvar d=$.parseJSON(data);\r\n");
        out.write("\t\t     \t\t\t\t\t\tif(d.success){\t\r\n");
        out.write("\t\t     \t\t\t\t\t\t\tif(d.attributes.apkfile!=null){\r\n");
        out.write("\t\t     \t\t\t\t\t\t\t\t$(\"#fileName\").val(d.attributes.apkfile);\t\r\n");
        out.write("\t\t     \t\t\t\t\t\t\t}\r\n");
        out.write("\t\t     \t\t\t\t\t\t\t     \t\t\t\t\t\t\t \r\n");
        out.write("\t\t     \t\t\t\t\t\t\tserverMsg = d.msg;\r\n");
        out.write("\t\t     \t\t\t\t\t\t}\r\n");
        out.write("\t\t     \t\t\t\t\t},\r\n");
        out.write("\t\t     \t\t\t\t\tonFallback : function(){\r\n");
        out.write("\t\t     \t\t\t\t\t\ttip(\"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试\")\r\n");
        out.write("\t\t     \t\t\t\t\t},\r\n");
        out.write("\t\t     \t\t\t\t\tonSelectError : function(file, errorCode, errorMsg){\r\n");
        out.write("\t\t     \t\t\t\t\t\tswitch(errorCode) {\r\n");
        out.write("\t\t     \t\t\t\t\t\t\tcase -100:tip(\"上传的文件数量已经超出系统限制的\"+$('#file_upload').uploadify('settings','queueSizeLimit')+\"个文件！\");break;\r\n");
        out.write("\t\t     \t\t\t\t\t\t\tcase -110:tip(\"文件 [\"+file.name+\"] 大小超出系统限制的\"+$('#file_upload').uploadify('settings','fileSizeLimit')+\"大小！\");break;\r\n");
        out.write("\t\t     \t\t\t\t\t\t\tcase -120:tip(\"文件 [\"+file.name+\"] 大小异常！\");break;\r\n");
        out.write("\t\t     \t\t\t\t\t\t\tcase -130:tip(\"文件 [\"+file.name+\"] 类型不正确！\");break;\r\n");
        out.write("\t\t     \t\t\t\t\t\t}\r\n");
        out.write("\t\t     \t\t\t\t\t},\r\n");
        out.write("\t\t     \t\t\t\t\tonUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {\r\n");
        out.write("\t\t     \t\t\t\t\t}\r\n");
        out.write("\t\t     \t\t\t});\r\n");
        out.write("\t\t     \t\t});\r\n");
        out.write("\t\t     \t\t\r\n");
        out.write("\t\t     \t\tfunction upload() {\t\r\n");
        out.write("\t\t     \t\t\t$('#file_upload').uploadify('upload', '*');\t\t\r\n");
        out.write("\t\t     \t\t\treturn flag;\r\n");
        out.write("\t\t     \t\t}\r\n");
        out.write("\t\t     \t\t\r\n");
        out.write("\t\t     \t\tfunction cancel() {\r\n");
        out.write("\t\t     \t\t\t$('#file_upload').uploadify('cancel', '*');\r\n");
        out.write("\t\t     \t\t}\r\n");
        out.write("\t\t     \t\t</script>\r\n");
        out.write("\t\t     \t\t<span id=\"file_uploadspan\"><input type=\"file\" name=\"files\" id=\"file_upload\" /></span>\r\n");
        out.write("\t\t    </div>\r\n");
        out.write("\t\t    <div class=\"form\" id=\"filediv\" style=\"height:100px;width:400px;\">\r\n");
        out.write("\t\t    </div>\r\n");
        out.write("\t\t   </fieldset>    \r\n");
        out.write("    \r\n");
        out.write("\t     </td>\r\n");
        out.write("\t    </tr> \r\n");
        out.write("\t\t\t</table>\r\n");
        out.write("\t\t\t\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_t_formvalid_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_t_formvalid_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_t_formvalid_layout_formid_dialog_beforeSubmit.reuse(_jspx_th_t_formvalid_0);
      return true;
    }
    _jspx_tagPool_t_formvalid_layout_formid_dialog_beforeSubmit.reuse(_jspx_th_t_formvalid_0);
    return false;
  }
}
