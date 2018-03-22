<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="utf-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.qfnu.bean.TQuantity"%>
<%@ page import="com.qfnu.dao.QuantityDAO"%>
<%@ page import="com.qfnu.util.db.JdbcTools"%>
<%@ page import="java.sql.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
	<title>
	曲阜师范大学本科实验教学工作量查询
	</title>
	<script src="./js/jquery-1.6.1.min.js" type="text/javascript"></script>
	<script src="./js/index.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="./css/style.css">
	<link rel="stylesheet" type="text/css" href="./css/index.css">
	<style>
		.print-header{
			font: 34px "Arial","SimSun";
			text-align:center;
			display:block;
			margin:40px 0;
		}
		.print-footer{
			clear: both;
    		overflow: auto;
    		margin: 30px 0;
		}
		.print-footer div{
			font: 18px "微软雅黑", "宋体";
		    width: 250px;
		    text-align: center;
		    float: right;
		    margin: 5px 0;
		}
		.center {
			margin:auto;
			width:70%;
		}
	</style>
</head>
<body>
<div class="sk">
	<!--startprint-->
	<div class="print-header">
		曲阜师范大学本科实验教学工作量
	</div>
	<%
		request.setCharacterEncoding("utf-8");
		List<TQuantity> result = (List<TQuantity>)session.getAttribute("result");
		if (result != null) {
			out.println("<table class='m-tb2'>");
			out.println("<tr>");
				out.println("<th width=120>教师姓名</th>");
				out.println("<th width=170>实验教学工作量</th>");
				out.println("<th width=230>所属学院</th>");
				out.println("<th width=200>授课时间</th>");
			out.println("</tr>");

	        for (TQuantity quantity : result) {
	        	out.println("<tr>");
				out.println("<td>" + quantity.getName() + "</td>");
				out.println("<td>" + quantity.getQuantity() + "</td>");
				out.println("<td>" + quantity.getDept() + "</td>");
				out.println("<td>" + quantity.getTerm() + "</td>");
				out.println("</tr>");
	        }

			out.println("</table>");
			
		}
	%>
	<div class="print-footer">
		<div>
			实验教学与设备管理中心
			<div>
		<%
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			out.println(sdf.format(new Date()));
		%>
			</div>
		</div>
	</div>
	<!--endprint-->
	<div class="center">
		<a href="#" class="button small gray" onclick="preview()">打印</a>
	</div>
	<br><br><br><br>
</div>
</body>