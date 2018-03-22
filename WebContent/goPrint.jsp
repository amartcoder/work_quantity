<%@ page language="java" pageEncoding="utf-8"%>

<%@page import="java.util.List"%>
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
	<link rel="stylesheet" type="text/css" href="./css/top.css">
	<link rel="stylesheet" type="text/css" href="./css/index.css">
	<style>
.banner,
.sliders{
    width: 1000px;
    height: 230px;
    position: relative;
    overflow: hidden;}
.sliders li{
    position: absolute;}
.slider_text{
    width: 384px;
    height: 84px;
    padding: 10px 15px;
    background: url(images/43560.jpg) no-repeat;
    position: absolute;
    bottom: 15px;
    left: 15px;}
.slider_text a{
    font: normal 26px/30px "Arial","Microsoft YaHei";
    color: #a18a3e;}
.slider_text p{
    font: normal 12px/24px "Arial","Microsoft YaHei";
    color: #333;}
.slider_btns{
    position: absolute;
    right: 15px;
    bottom: 20px;}
.slider_btns li{
    width: 13px;
    height: 13px;
    margin: 0 2px;
    display: inline;
    background: url(images/43558.jpg) no-repeat left;}
.slider_btns li.cur{
    background: url(images/43558.jpg) no-repeat right;}

.floatL{
    float: left;}
</style>
</head>
<body>
<div class="sk">
	<div class="top">
		<div class="top2">
			<!-- 网站logo图片地址请在本组件"内容配置-网站logo"处填写 -->
				<a href="#" title="本科实验教学工作量查询">
			<img src="./images/43596.jpg" width="1000" height="120" border="0" alt="本科实验教学工作量查询" title="本科实验教学工作量查询">
		</a>
		</div>
	</div>
	<div class="banner" style="margin-bottom: 5px">
		<ul id="slider" class="sliders">
			<li style="display: none;"><img src="./images/51404.jpg" width="1000" height="230"> </li>
			<li style="display: list-item;"><img src="./images/51405.jpg" width="1000" height="230"></li>
		</ul>
		<ul id="slider_btns" class="slider_btns">
			<!-- <li class="floatL cur"></li>
			<li class="floatL"></li> -->
		</ul>
	</div>
	<%
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("xm");
		String idNumber = request.getParameter("sfzh");
		List<TQuantity> result = null;
		QuantityDAO dao = new QuantityDAO();
		Connection connection = null;
	    try {
	        connection = JdbcTools.getConnection();
	        String sql = "select name,term,note,idnumber,round(quantity,1) quantity,dept from work_quantity where name = ? and idnumber = ? order by term";
	        result = dao.getForList(connection, sql, name, idNumber);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcTools.releaseDB(null, null, null, connection);
	    }
	%>
	<FORM METHOD=POST ACTION="" id="search_form">
		<div class="register-box">
			<label for="username" class="username_label">姓&nbsp;&nbsp;名
			<input type="text" name="xm" id="xm">
			</label>
			<label for="username" class="username_label">身份证号
				<input type="text" name="sfzh" id="sfzh">
			</label>
			<a href="#" class="button small gray" onclick="query()">查询</a>
			<%if (result != null && result.size() > 0) { %>
			<a href="print.jsp" class="button small gray">打印</a>
			<%} %>
		</div>
	</FORM>
	<%
		if (result != null) {
			out.println("<table class='m-tb2'>");
			out.println("<tr>");
				out.println("<th width=120>姓名</th>");
				out.println("<th width=220>单位</th>");
				out.println("<th width=260>学年学期</th>");
				out.println("<th width=200>实验教学工作量</th>");
			out.println("</tr>");
			
	        for (TQuantity quantity : result) {
	        	out.println("<tr>");
				out.println("<td>" + quantity.getName() + "</td>");
				out.println("<td>" + quantity.getDept() + "</td>");
				out.println("<td>" + quantity.getTerm() + "</td>");
				out.println("<td>" + quantity.getQuantity() + "</td>");
				out.println("</tr>");
	        }
			out.println("</table>");
			
		}

	session.setAttribute("result", result);
	%>
	<br><br><br><br>
	<table>
		<tr>
			<td width=820>
			说明：
			</td>
		</tr>
		<tr>
			<td width=820>
			1.本科实验教学工作量数据库历经多次数据库升级和教师号变更，部分往年教师身份证号等信息是通过姓名关联过去的，所以个别教师工作量可能会出现不能对应等错误，后期会逐步对这些信息进行检查修正，教师本人如发现有错误的请联系QQ（1064488587）。
			</td>
		</tr>
		<tr>
			<td width=820>
			2.职称评定、评奖评优等如果需要填写本科实验教学工作量，老师们可查询填写，同时我们会把相关数据提供给有关部门进行后期审核。
			</td>
		</tr>
		<tr>
			<td width=820>
			3.咨询电话：0537-4456187（实验教学管理科 程超）
			</td>
		</tr>

	</table>
</div>
</body>