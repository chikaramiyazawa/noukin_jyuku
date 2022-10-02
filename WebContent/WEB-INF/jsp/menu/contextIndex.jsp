<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="Bean.ContextBean"%>

<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>対象学年一覧</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel="stylesheet">
  <link rel="stylesheet"  href="<c:url value='/css/style.css' />">
	<link rel="stylesheet"  href="<c:url value='/css/calendar.css' />">

</head>
<body>

<c:import url="/WEB-INF/jsp/menu/header.jsp"></c:import>

<div class="app">

<c:import url="/WEB-INF/jsp/menu/menu.jsp"></c:import>

<div class="content">

	<h1>対象学年一覧</h1>
	<div id="header">
		<br>
		<form action="<%=request.getContextPath()%>/SubjectIndexServlet"
			method="get" onSubmit="setwait();">
			<%
  List<ContextBean> context = new ArrayList<ContextBean>();

  context = (List<ContextBean>)session.getAttribute("context");

%>

			<table border="1">
				<tr>
					<th>検索キー</th>
					<th>対象学年</th>

					</tr>
				<%if(context != null) {%>
				<%for(int count = 0; count < context.size(); count++) {%>
				<tr>
					<td><%=context.get(count).getGrade()%></td>
					<td><%=context.get(count).getTarget()%></td>

				</tr>
				<%} %>
			<%} %>

				</table>
</form>
</div>
</div>

</body>
</html>