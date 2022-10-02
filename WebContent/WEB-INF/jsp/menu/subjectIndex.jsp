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
<%@ page import="Bean.SearchBeans"%>

<%@ page import="Bean.SubjectBean"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目一覧</title>

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


	<h1>科目一覧</h1>

		<br>
		<form action="<%=request.getContextPath()%>/SubjectIndexServlet"
			method="get" onSubmit="setwait();">
			<%
  List<SubjectBean> subject= new ArrayList<SubjectBean>();

  subject = (List<SubjectBean>)session.getAttribute("subject");

%>

			<table border="1">
				<tr>
					<th>ID</th>
					<th>科目名</th>

					</tr>
				<%if(subject != null) {%>
				<%for(int count = 0; count < subject.size(); count++) {%>
				<tr>
					<td><%=subject.get(count).getID()%></td>
					<td><%=subject.get(count).getName()%></td>

				</tr>
				<%} %>
			<%} %>

				</table>

			<br>
			</form>
	</div>
  </div>
</body>
</html>