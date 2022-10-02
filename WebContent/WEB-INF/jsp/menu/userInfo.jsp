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
<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="Bean.UserBean"%>
<%@ page import="Bean.StudyBean" %>
<%@ page import="Bean.SearchBeans"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報</title>
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

<h1>ユーザー情報</h1>
<form action="<%=request.getContextPath()%>/UserSearchServlet"  name="UISearchform" onSubmit="setwait();">

<%
  UserBean user = new UserBean();

  user = (UserBean)session.getAttribute("user");



  List<SearchBeans> searchBean = new ArrayList<SearchBeans>();

  searchBean = (List<SearchBeans>)request.getAttribute("list");


  %>

<table border="1">
				<tr>
					<th>ユーザーID</th>
					<th>ユーザー名</th>
					<th>権限</th>
					<th>受講料</th>

				</tr>
				<%if(user.getDelete_flg() == 0){ %>
				<tr>

					<td><%=user.getUser_ID()%></td>
					<td><%=user.getUser_NAME()%></td>
					<td>
						<%
        String Admin_Name;

        switch(user.getAdmin() ){

        case 1: out.print("学生"); break;

        case 2: out.print("講師"); break;

        }


        %>

					</td>
	           <%if(user.getAdmin() == 1 ){ %>
				  <td><%=user.getTuition() %></td>
				<%}else{ %>
				  <td> - </td>
				<%} %>
				</tr>



</table>

              <%if(user.getAdmin() == 1 ){ %>
				<button type="submit" name="register" value="<%=user.getUser_ID()%>">履修登録へ</button>
              <%}else if(user.getAdmin() == 2){ %>
				<p>講師のため履修登録出来ません。</p>
			   <%}else{ %>
				<p>ユーザー情報を取得できませんでした。</p>
				<%} %>
			<%}else {%>
			<p>すでに削除されています。</p>
			<%} %>
			    <button type="submit" name="back" value="back">戻る</button>
</form>

<br>

<form action="<%=request.getContextPath()%>/StudyInsertServlet" method="post" name="UISearchform" onSubmit="setwait();">

        <% if(session.getAttribute("alert") != null) {

    String alert = (String)session.getAttribute("alert"); %>

        <script type="text/javascript">

    var msg = "<%=alert%>"
    alert(msg);

    </script>
        <%} %>


	<table border="1">
				<tr>
					<th>コースID</th>
					<th>コース名</th>
					<th>操作</th>

				</tr>
		<%if (searchBean != null) {%>
		<%for(int count = 0; count < searchBean.size(); count++) {%>
		<input type="hidden" name="user_id" value= "<%=user.getUser_ID() %>" />
				<tr>
					<td><%=searchBean.get(count).getCourseBean().getCourse_ID()%></td>
					<td><%=searchBean.get(count).getCourseBean().getCourse_Name()%></td>
					<td>
					<%if(searchBean.get(count).getStudyBean().getStudyIng() == 0 ){ %>
					<button type="submit" name="studying" value="<%=searchBean.get(count).getCourseBean().getCourse_ID()%>">履修</button>
					<%}else {%>
					<button type="submit" name="reset" value="<%=searchBean.get(count).getCourseBean().getCourse_ID()%>">取り消し</button>
					<%} %>
					</td>
		<%} %>

        <%} %>



</form>
</div>

</div>

</body>
</html>