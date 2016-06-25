<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  
   <title>HighChat</title>
  <script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="plug-in/Highcharts-2.2.5/js/highcharts.js"/>
   
 <script type="text/javascript">
 $('#container').highcharts({                   //图表展示容器，与div的id保持一致
     chart: {
         type: 'column'                         //指定图表的类型，默认是折线图（line）
     },
     title: {
         text: 'My first Highcharts chart'      //指定图表标题
     },
     xAxis: {
         categories: ['my', 'first', 'chart']   //指定x轴分组
     },
     yAxis: {
         title: {
             text: 'something'                  //指定y轴的标题
         }
     },
     series: [{                                 //指定数据列
         name: 'Jane',                          //数据列名
         data: [1, 0, 4]                        //数据
     }, {
         name: 'John',
         data: [5, 7, 3]
     }]
 });
 
 </script>
 </head>
 <body>
 fsdfs
 	<div id="container" style="min-width:800px;height:400px"></div>
 </body>

</html>