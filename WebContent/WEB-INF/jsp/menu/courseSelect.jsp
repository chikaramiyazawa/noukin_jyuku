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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授業履修選択</title>

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

<h1>授業履修選択画面</h1>

<form action="<%=request.getContextPath()%>/CourseSelectServlet" method="post"  name="UISearchform" onSubmit="setwait();">

<%
    UserBean user = new UserBean();

    user = (UserBean)session.getAttribute("user");

   List<SearchBeans> searchBean = new ArrayList<SearchBeans>();

   searchBean = (List<SearchBeans>)session.getAttribute("list"); %>


        <% if(session.getAttribute("alert") != null) {

    String alert = (String)session.getAttribute("alert"); %>

        <script type="text/javascript">

    var msg = "<%=alert%>"
    alert(msg);

    </script>
        <%} %>

	<div>
		<div id="main">

		<br>

			<table border="1">
				<tr>
					<th>講師名</th>
					<th>コース名</th>
					<th>科目名</th>
					<th>開催曜日</th>
					<th>講義開始時間</th>
					<th>対象学年</th>
					<th>受講料</th>
					<th>操作</th>
				</tr>
					<%if(searchBean != null) {%>
					<%for(int count = 0; count < searchBean.size(); count++) {%>
					<%if(searchBean.get(count).getCourseMeisai().getDelete_Flg() == 0){ %>
				<tr>

					<td><%=searchBean.get(count).getCourseMeisai().getTEACHER()%> </td>

					<td><%=searchBean.get(count).getCourseBean().getCourse_Name()%></td>

					<td><%=searchBean.get(count).getSubjectBean().getName() %></td>

					<td>


	<%  switch(searchBean.get(count).getCourseMeisai().getDay_Lecture() ) {

        case 1: out.print("月"); break;

        case 2: out.print("火"); break;

        case 3: out.print("水"); break;

        case 4: out.print("木"); break;

        case 5: out.print("金"); break;

        case 6: out.print("土"); break;

        case 7: out.print("日"); break;

       }

         %>
					</td>

					<td><%=searchBean.get(count).getCourseMeisai().getStart_Lecture() %> </td>

					<td><%=searchBean.get(count).getContextBean().getTarget() %></td>

					<td><%=searchBean.get(count).getCourseMeisai().getTuition() %></td>

					<td>
					<input type ="hidden" name="user_id" value="<%=user.getUser_ID()%>">
					<button type="submit" name="select" value="<%=searchBean.get(count).getCourseBean().getCourse_ID()%>">選択</button>

					</td>

				</tr>
				<%} %>
				<%} %>
				<%} %>
			</table>

			<button type="submit" name="back" value="back">戻る</button>

</form>

</div>

</div>

</body>
</html>