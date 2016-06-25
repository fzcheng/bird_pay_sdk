<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>移动积分支付</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
.code {
	background-image: url(code.jpg);
	font-family: Arial;
	font-style: italic;
	color: Red;
	border: 0;
	padding: 0px 0px;
	letter-spacing: 3px;
	font-weight: bolder;
	font-size: 18px;
}
.unchanged {
border: 0;
}
body,div,p,span,em,a,img,ul,li,tabale,tr,td,h1,h2,h3,ol,br,th{
	margin: 0;
	padding: 0;
}
body{ font-family:"微软雅黑",Verdana, Geneva, sans-serif; font-size:12px; color:#636363;}
img{ border:none;}
a{ text-decoration:none;}
ul,ol{ list-style:none;}
em{ font-style:normal;}
.header{ background:url(http://wabpstatic.magicbirds.cn/pay/header_bg.jpg) repeat-x; height:74px; width:100%; overflow:hidden; }
.main{
	width: 650px;
	margin: 0 auto;
}
.color_red{ color:#d32f2f;}
.color_green{ color:#0086d0;}
.logo{
	margin-top: 18px;
	width: 300px;
	float: left;
}
.logo a{ margin-right:9px;}
.return{ padding-top:5px; padding-bottom:5px; height:20px;}
.return a{ float:right; display:block; background:url(return.png) no-repeat left center; padding-left:20px; font-size:13px; color:#6a6a6a; font-weight:700;}
.box_border{ border:3px solid #e6e6e6; border-radius:5px; -moz-border-radius:5px; -webkit-border-radius:5px; padding-top:20px;}
.box_inner{ padding:0 40px;}
.box_border h2{ font-size:19px; font-weight:normal; border-bottom:2px solid #90c31f; padding-bottom:10px;} 
.box_steps{ height:71px; margin:30px 0;}
.box_steps li{ float:left;}
.box_steps_ct{ float:left; background:url(http://wabpstatic.magicbirds.cn/pay/step.png) no-repeat left top; width:149px; height:71px;}
.box_step_num{ font-family:Arial; font-style:italic; font-size:40px; color:#fff; float:left; line-height:71px;}
.box_step_words{ width:115px; float:right;  padding:22px 0px; font-size:12px; line-height:17px; word-break:break-all;}
.box_steps_next{ width:45px; height:71px; float:left; }
.box_steps_next span{ background:url(http://wabpstatic.magicbirds.cn/pay/next.png) no-repeat; display:block; width:10px; height:18px; margin:30px auto 0;}
.box_table{ border-top:1px solid #a3a7ab; border-left:1px solid #a3a7ab;}
.box_table th,.box_table td{font-size:14px;  border-right:1px solid #a3a7ab; border-bottom:1px solid #a3a7ab;}
.box_table th{ background-color:#f2f2f2;  text-align:center; height:28px; line-height:28px; color:#000;}

.box_table_td{ text-align:center;}
.box_table_lf{ padding:20px 15px; text-indent:2em;}
.box_lg_bt{
	background: url(http://wabpstatic.magicbirds.cn/pay/bottom.png) no-repeat;
	width: 134px;
	height: 38px;
	font-size: 16px;
	line-height: 38px;
	text-align: center;
	color: #fff;
	display: block;
	line-height: none;
}
.box_lg_table{ width:580px; margin:40px auto 0;}
.box_lg_table td{ padding-bottom:8px;}
.box_lg_table label{ color:#000; text-align:right; float:right; font-size:14px;}
.box_lg_code,.box_lg_phone{ width:187px; border:1px solid #a4a5a8; padding:5px;}
.box_lg_send{ border:1px solid #a4a5a8; background-color:#f9f9f9; padding:5px; color:#4d4d4f;}
.box_lg_tip{ font-size:14px; /* visibility:hidden; */ color:#F00; /* height:30px; */ line-height:20px; padding-top:5px;}
.box_lg_load{  margin-top:10px; border:1px solid #b5d177; background-color:#84b21d; color:#fff; width:150px; padding:5px 10px; position:relative;}
.box_lg_load b{ display:block; background:url(http://wabpstatic.magicbirds.cn/pay/loading.png) no-repeat; width:15px; height:15px; float:left; margin-right:5px; }
.box_lg_close{ display:block; background-color:#0086d0; position:absolute; right:5px; top:5px; color:#fff; line-height:8px; padding:1px 2px 3px; font-size:15px; font-family:Arial;}
.box_recomend{ color:#999; font-size:13px; line-height:18px; background-color:#fafdff; border-top:1px solid #e6e6e6; padding:20px 40px; margin-top:20px;}
.box_lg_apply{ margin-top:8px;}
.box_recomend h3{ font-size:15px; margin-bottom:5px;}

.footer{ text-align:center; padding:20px 0; color:#636363; line-height:25px;}
.footer a{ color:#636363; }

.pop{ width:520px; height:235px; display:none; position:absolute; z-index:10;}
.pop_content{ margin-left:157px; margin-top:-128px; padding-right:29px;}
.pop_title{ font-weight:700; font-size:14px; color:#4a4a4a; padding-bottom:10px;}
.pop_dot{ color:#0086d0; font-size:15px;}
.pop_tip{ padding-top:20px;}
.pop_inner{ padding:30px 0; position:relative;  background-color:#fff;}
.pop_close{ background:url(http://wabpstatic.magicbirds.cn/pay/close.png) no-repeat; width:21px; height:21px; display:block; position:absolute; top:10px; right:10px;}
.pop_mask{ display:none; z-index:10; position:fixed; top:0; right:0; left:0; height:100%; background-color:#6e6d6d; filter:alpha(opacity=70); opacity:0.7;}

.pop h1{ background-color:#dbdbdb; font-size:14px; height:27px; line-height:27px; position:absolute; top:10px; left:10px; right:10px; text-indent:1em;}
.pop_applay .pop_close,.pop_recommend .pop_close{ top:3px;}
.pop_applay h3,.pop_recommend h3{ font-weight:normal; margin-bottom:10px;font-size:14px; color:#0086d0; text-align:center; margin-top:20px;}
.pop_applay p{ text-align:center; height:30px; line-height:30px; color:#4a4a4a;}
.pop_applay .pop_label{ vertical-align:text-bottom;}
.pop_applay .box_border,.pop_recommend .box_border{ padding-top:0;}
.pop_applay .pop_sure{margin:10px auto 0;}
.pop_sure,.pop_concel,.pop_ensure{ background-image:url(http://wabpstatic.magicbirds.cn/pay/common_bt.png); color:#fff; width:104px; height:35px; background-repeat:no-repeat; text-align:center; line-height:35px; font-size:15px; display:block;}
.pop_sure,.pop_concel{  background-position:right top; }
.pop_ensure{ background-position:left top; margin-right:20px; margin-left:27%;}
.pop_concel,.pop_ensure{ float:left;}
.pop_recommend h3{ margin:30px 0;}
.pop_opt{ height:30px;}
.pop_tip img{ margin-left:10px;}
.pop_tip .pop_sure{ margin:10px 0 10px 47px;}
.pop .box_lg_tip{ text-align:center;}
.tel {
	float: right;
	color: #727272;
	margin-top: 18px;
	font-size: 12px;
	width: 300px;
	overflow: hidden;
	margin-right: 0px;
	height: 50px;
}
.tel span{height:54px; width:80px; float:right; display:inline-block; background:url(http://wabpstatic.magicbirds.cn/pay/tel.png) no-repeat; background-position:0 0;}
.tel strong {
	color: #238ED2;
	font-size: 16px
}
.tel ul { float:right; width:180px; height:45px; overflow:hidden; padding-left:10px;}
.tel ul li{width:180px; white-space:nowrap;overflow:hidden;}
.red2 {
	color: #F00;
	font-size: 18px;
}
.ui-dialog {
    *zoom:1;
    _float: left;
    position: relative;
    background-color: #FFF;
    border-radius: 6px;
    outline: 0;
    background-clip: padding-box;
    font-family: Helvetica, arial, sans-serif;
    font-size: 14px;
    line-height: 1.428571429;
    color: #333;
    opacity: 0;
    -webkit-transform: scale(0);
    transform: scale(0);
    -webkit-transition: -webkit-transform .15s ease-in-out, opacity .15s ease-in-out;
    transition: transform .15s ease-in-out, opacity .15s ease-in-out;
}
.ui-popup-show .ui-dialog {
    opacity: 1;
    -webkit-transform: scale(1);
    transform: scale(1);
}
.ui-popup-focus .ui-dialog {
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
}
.ui-popup-modal .ui-dialog {
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.1), 0 0 256px rgba(255, 255, 255, .3);
}
.ui-dialog-grid {
    width: auto;
    margin: 0;
    border: 0 none;
    border-collapse:collapse;
    border-spacing: 0;
    background: transparent;
}
.ui-dialog-header,
.ui-dialog-body,
.ui-dialog-footer {
    padding: 0;
    border: 0 none;
    text-align: left;
    background: transparent;
}
.ui-dialog-header {
    white-space: nowrap;
}
.ui-dialog-close {
    position: relative;
    _position: absolute;
    float: right;
    top: 13px;
    right: 13px;
    _height: 26px;
    padding: 0 4px;
    font-size: 21px;
    font-weight: bold;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #FFF;
    opacity: .2;
    filter: alpha(opacity=20);
    cursor: pointer;
    background: transparent;
    _background: #FFF;
    border: 0;
    -webkit-appearance: none;
}
.ui-dialog-close:hover,
.ui-dialog-close:focus {
    color: #000000;
    text-decoration: none;
    cursor: pointer;
    outline: 0;
    opacity: 0.5;
    filter: alpha(opacity=50);
}
.ui-dialog-title {
    margin: 0;
    line-height: 1.428571429;
    min-height: 16.428571429px;
    padding: 15px;
    overflow:hidden; 
    white-space: nowrap;
    text-overflow: ellipsis;
    font-weight: bold;
    cursor: default;
}
.ui-dialog-body {
    padding: 20px;
    text-align: center;
}
.ui-dialog-content {
    display: inline-block;
    position: relative;
    vertical-align: middle;
    *zoom: 1;
    *display: inline;
    text-align: left;
}
.ui-dialog-footer {
    padding: 0 20px 20px 20px;
}
.ui-dialog-statusbar {
    float: left;
    margin-right: 20px;
    padding: 6px 0;
    line-height: 1.428571429;
    font-size: 14px;
    color: #888;
    white-space: nowrap;
}
.ui-dialog-statusbar label:hover {
    color: #333;
}
.ui-dialog-statusbar input,
.ui-dialog-statusbar .label {
    vertical-align: middle;
}
.ui-dialog-button {
    float: right;
    white-space: nowrap;
}
.ui-dialog-footer button+button {
    margin-bottom: 0;
    margin-left: 5px;
}
.ui-dialog-footer button {
    width:auto;
    overflow:visible;
    display: inline-block;
    padding: 6px 12px;
    _margin-left: 5px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: normal;
    line-height: 1.428571429;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border-radius: 4px;
    -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
       -o-user-select: none;
          user-select: none;
}

.ui-dialog-footer button:focus {
  outline: thin dotted #333;
  outline: 5px auto -webkit-focus-ring-color;
  outline-offset: -2px;
}

.ui-dialog-footer button:hover,
.ui-dialog-footer button:focus {
  color: #333333;
  text-decoration: none;
}

.ui-dialog-footer button:active {
  background-image: none;
  outline: 0;
  -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
          box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
}
.ui-dialog-footer button[disabled] {
  pointer-events: none;
  cursor: not-allowed;
  opacity: 0.65;
  filter: alpha(opacity=65);
  -webkit-box-shadow: none;
          box-shadow: none;
}

.ui-dialog-footer button {
  color: #333333;
  background-color: #ffffff;
}

.ui-dialog-footer button:hover,
.ui-dialog-footer button:focus,
.ui-dialog-footer button:active {
  color: #333333;
  background-color: #ebebeb;
}

.ui-dialog-footer button:active{
  background-image: none;
}

.ui-dialog-footer button[disabled],
.ui-dialog-footer button[disabled]:hover,
.ui-dialog-footer button[disabled]:focus,
.ui-dialog-footer button[disabled]:active {
  background-color: #ffffff;
}

.ui-dialog-footer button.ui-dialog-autofocus {
  color: #ffffff;
  background-color: #428bca;
}

.ui-dialog-footer button.ui-dialog-autofocus:hover,
.ui-dialog-footer button.ui-dialog-autofocus:focus,
.ui-dialog-footer button.ui-dialog-autofocus:active {
  color: #ffffff;
  background-color: #3276b1;
}

.ui-dialog-footer button.ui-dialog-autofocus:active {
  background-image: none;
}
.ui-popup-top-left .ui-dialog,
.ui-popup-top .ui-dialog,
.ui-popup-top-right .ui-dialog {
    top: -8px;
}
.ui-popup-bottom-left .ui-dialog,
.ui-popup-bottom .ui-dialog,
.ui-popup-bottom-right .ui-dialog {
    top: 8px;
}
.ui-popup-left-top .ui-dialog,
.ui-popup-left .ui-dialog,
.ui-popup-left-bottom .ui-dialog {
    left: -8px;
}
.ui-popup-right-top .ui-dialog,
.ui-popup-right .ui-dialog,
.ui-popup-right-bottom .ui-dialog {
    left: 8px;
}

.ui-dialog-arrow-a,
.ui-dialog-arrow-b {
    position: absolute;
    display: none;
    width: 0;
    height: 0;
    overflow:hidden;
    _color:#FF3FFF;
    _filter:chroma(color=#FF3FFF);
    border:8px dashed transparent;
}
.ui-popup-follow .ui-dialog-arrow-a,
.ui-popup-follow .ui-dialog-arrow-b{
    display: block;
}
.ui-popup-top-left .ui-dialog-arrow-a,
.ui-popup-top .ui-dialog-arrow-a,
.ui-popup-top-right .ui-dialog-arrow-a {
    bottom: -16px;
}
.ui-popup-top-left .ui-dialog-arrow-b,
.ui-popup-top .ui-dialog-arrow-b,
.ui-popup-top-right .ui-dialog-arrow-b {
    bottom: -15px;
    border-top:8px solid #fff;
}
.ui-popup-top-left .ui-dialog-arrow-a,
.ui-popup-top-left .ui-dialog-arrow-b  {
    left: 15px;
}
.ui-popup-top .ui-dialog-arrow-a,
.ui-popup-top .ui-dialog-arrow-b  {
    left: 50%;
    margin-left: -8px;
}
.ui-popup-top-right .ui-dialog-arrow-a,
.ui-popup-top-right .ui-dialog-arrow-b {
    right: 15px;
}
.ui-popup-bottom-left .ui-dialog-arrow-a,
.ui-popup-bottom .ui-dialog-arrow-a,
.ui-popup-bottom-right .ui-dialog-arrow-a {
    top: -16px;
    border-bottom:8px solid #7C7C7C;
}
.ui-popup-bottom-left .ui-dialog-arrow-b,
.ui-popup-bottom .ui-dialog-arrow-b,
.ui-popup-bottom-right .ui-dialog-arrow-b {
    top: -15px;
    border-bottom:8px solid #fff;
}
.ui-popup-bottom-left .ui-dialog-arrow-a,
.ui-popup-bottom-left .ui-dialog-arrow-b {
    left: 15px;
}
.ui-popup-bottom .ui-dialog-arrow-a,
.ui-popup-bottom .ui-dialog-arrow-b {
    margin-left: -8px;
    left: 50%;
}
.ui-popup-bottom-right .ui-dialog-arrow-a,
.ui-popup-bottom-right .ui-dialog-arrow-b {
    right: 15px;
}
.ui-popup-left-top .ui-dialog-arrow-a,
.ui-popup-left .ui-dialog-arrow-a,
.ui-popup-left-bottom .ui-dialog-arrow-a {
    right: -16px;
    border-left:8px solid #7C7C7C;
}
.ui-popup-left-top .ui-dialog-arrow-b,
.ui-popup-left .ui-dialog-arrow-b,
.ui-popup-left-bottom .ui-dialog-arrow-b {
    right: -15px;
    border-left:8px solid #fff;
}
.ui-popup-left-top .ui-dialog-arrow-a,
.ui-popup-left-top .ui-dialog-arrow-b {
    top: 15px;
}
.ui-popup-left .ui-dialog-arrow-a,
.ui-popup-left .ui-dialog-arrow-b {
    margin-top: -8px;
    top: 50%;
}
.ui-popup-left-bottom .ui-dialog-arrow-a,
.ui-popup-left-bottom .ui-dialog-arrow-b {
    bottom: 15px;
}
.ui-popup-right-top .ui-dialog-arrow-a,
.ui-popup-right .ui-dialog-arrow-a,
.ui-popup-right-bottom .ui-dialog-arrow-a {
    left: -16px;
    border-right:8px solid #7C7C7C;
}
.ui-popup-right-top .ui-dialog-arrow-b,
.ui-popup-right .ui-dialog-arrow-b,
.ui-popup-right-bottom .ui-dialog-arrow-b {
    left: -15px;
    border-right:8px solid #fff;
}
.ui-popup-right-top .ui-dialog-arrow-a,
.ui-popup-right-top .ui-dialog-arrow-b {
    top: 15px;
}
.ui-popup-right .ui-dialog-arrow-a,
.ui-popup-right .ui-dialog-arrow-b {
    margin-top: -8px;
    top: 50%;
}
.ui-popup-right-bottom .ui-dialog-arrow-a,
.ui-popup-right-bottom .ui-dialog-arrow-b {
    bottom: 15px;
}


@-webkit-keyframes ui-dialog-loading {
    0% {
        -webkit-transform: rotate(0deg);
    }
    100% {
        -webkit-transform: rotate(360deg);
    }
}
@keyframes ui-dialog-loading {
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
}

.ui-dialog-loading {
    vertical-align: middle;
    position: relative;
    display: block;
    *zoom: 1;
    *display: inline;
    overflow: hidden;
    width: 32px;
    height: 32px;
    top: 50%;
    margin: -16px auto 0 auto;
    font-size: 0;
    text-indent: -999em;
    color: #666;
}
.ui-dialog-loading {
    width: 100%\9;
    text-indent: 0\9;
    line-height: 32px\9;
    text-align: center\9;
    font-size: 12px\9;
}

.ui-dialog-loading::after {
    position: absolute;
    content: '';
    width: 3px;
    height: 3px;
    margin: 14.5px 0 0 14.5px;
    border-radius: 100%;
    box-shadow: 0 -10px 0 1px #ccc, 10px 0px #ccc, 0 10px #ccc, -10px 0 #ccc, -7px -7px 0 0.5px #ccc, 7px -7px 0 1.5px #ccc, 7px 7px #ccc, -7px 7px #ccc;
    -webkit-transform: rotate(360deg);
    -webkit-animation: ui-dialog-loading 1.5s infinite linear;
    transform: rotate(360deg);
    animation: ui-dialog-loading 1.5s infinite linear;
    display: none\9;
}
</style>
<script language="javascript" type="text/javascript"> 
function validate2() 
{
  return false;
}  
function validate() {
    return true;
}
</script>


<script>
    function next() {
        document.getElementById('form1').submit();
        document.getElementById("goumai").style.display="none";
        document.getElementById("goumai_ing").style.display="block";
    }
</script>

  </head>
  
  <body>

	 <div class="header">
  <div class="main">
    	<div class="logo"><a href="#" title="积分支付"><img src="http://leyo.magicbirds.cn/images/Jincaijifen/jifenpay_logo.png"></a></div>
        <div class="tel">
				<ul>
					<li>短信支付及投诉相关问题请电询</li>
					<li><strong>400-096-8662</strong></li>
				</ul>

			
        </div>
    </div>
</div>

<div class="main">
	<div class="return">
    </div>
    <div class="box_border">
        <div class="box_inner">
            
         <div class="box_lg">
         	<div align="center">
         	<img src="http://leyo.magicbirds.cn/images/Jincaijifen/pay_fail.png">
         	</div>
   			<br />
		</div>
        
            
            
      	</div>
       
    </div>
    
</div>

    
<div class="footer">
	<p>京ICP备14010549号 魔力小鸟（北京）信息技术有限公司 </p>
	<p><strong>客服电话</strong>：<strong>4000968662</strong><strong>|客服QQ：2810476497</strong></p>
    
</div>    
    
  </body>
</html>
