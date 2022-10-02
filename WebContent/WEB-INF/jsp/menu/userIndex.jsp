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
<%@ page import="Bean.UserBean"%>
<%@ page import="Bean.SubjectBean"%>

<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ユーザー一覧</title>
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


	<h1>ユーザー一覧</h1>
	<div id="header">
		<br>
		<form action="<%=request.getContextPath()%>/UserIndexServlet" method="post" onSubmit="setwait();">
			<%
  List<UserBean> user = new ArrayList<UserBean>();

  user = (List<UserBean>)session.getAttribute("user");

%>

			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<th>ユーザー名</th>
					<th>権限</th>
					<th>パスワード</th>
					<th>操作</th>
				</tr>
				<%if(user  != null) {%>
				<%for(int count = 0; count < user.size(); count++) {%>
				<%if(user.get(count).getDelete_flg() == 0){ %>
				<tr>
					<td><%=user.get(count).getUser_ID()%></td>
					<td><%=user.get(count).getUser_NAME()%></td>
					<td>
						<%
        String Admin_Name;

        switch(user.get(count).getAdmin() ){

        case 1: out.print("学生"); break;

        case 2: out.print("講師"); break;

        }


        %>

					</td>

				  <td><%=user.get(count).getUser_Pass() %></td>

				  <td>
				  	<button type="submit" name="update" value="<%=user.get(count).getUser_ID()%>">更新</button>
					<button type="submit" name="delete" value="<%=user.get(count).getUser_ID()%>">削除</button>
				  </td>

				</tr>
				<%} %>

				<%} %>

				<%} %>

			</table>

			<br> <input type="submit" name="insert" value="登録画面へ"
				id="insert" onClick="return checkvalue()" />

		</form>
	</div>
	</div>
	</div>

</body>
</html>
