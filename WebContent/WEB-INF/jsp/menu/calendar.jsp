<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="calendar.MyCalendar" %>
<% MyCalendar mc=(MyCalendar)request.getAttribute("mc"); %>
<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=mc.getGengou() %>年<%=mc.getMonth() %>月カレンダー</title>
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


	<div id="continer">
		<h1><%=mc.getGengou() %>年<%=mc.getMonth()%>月のカレンダー</h1>
		<p>
			<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth()-1 %>">前月</a>
			<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth()+1 %>">翌月</a>
		</p>
		<table>
			<tr>
				<th>日</th>
				<th>月</th>
				<th>火</th>
				<th>水</th>
				<th>木</th>
				<th>金</th>
				<th>土</th>
			</tr>
			<%for(String[] row: mc.getData()){ %>
			<tr>
				<%for(String col:row){ %>
					<%if(col.startsWith("*")){ %>
						<td class="today"><%=col.substring(1)%></td>
					<%}else{%>
						<td><%=col%></td>
					<%}%>
				<%}%>
			</tr>
			<%}%>
		</table>
	 </div>
    </div>
   </div>
</body>
</html>