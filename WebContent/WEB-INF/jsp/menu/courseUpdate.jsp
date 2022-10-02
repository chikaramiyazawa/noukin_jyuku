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
<%@ page import="Bean.ContextBean"%>

<%@ taglib prefix = "fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授業更新画面</title>

<link rel="stylesheet"  href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel="stylesheet">
  <link rel="stylesheet"  href="<c:url value='/css/style.css' />">
	<link rel="stylesheet"  href="<c:url value='/css/calendar.css' />">
</head>
<body>

<c:import url="/WEB-INF/jsp/menu/header.jsp"></c:import>


		<SCRIPT language="Java Script">

function numOnly(){

    m = String.formCharCode(event, keyCode);

    if("0123456789\r",indexOf(m,0) < 0) return false;

    return true;

}


function checkValue(){

    var excute = doucment.getElementById("Insert").value;

    if(window.confirm("登録を行います。よろしいですか？") == true){

    return true;

    }else{

    reture false;

    }

}
</SCRIPT>

</div>





 <% List<SearchBeans> searchBean = new ArrayList<SearchBeans>();

   searchBean = (List<SearchBeans>)request.getAttribute("list");

   List<UserBean> user = new ArrayList<UserBean>();

   user = (List<UserBean>)session.getAttribute("user");

   List<SubjectBean> subject = new ArrayList<>();

   subject = (List<SubjectBean>)session.getAttribute("subject");

	List<ContextBean> context = new ArrayList<ContextBean>();

	context = (List<ContextBean>)session.getAttribute("context");

%>

		<% if(session.getAttribute("alert") != null) {

    String alert = (String)session.getAttribute("alert"); %>

		<script type="text/javascript">

    var msg = "<%=alert%>"
    alert(msg);

    </script>
		<%} %>


		<%if(session.getAttribute("message") != null) {


    List<ArrayList> message = new ArrayList<>();

    message = (List<ArrayList>) session.getAttribute("message"); %>

		<script type="text/javascript">

    var msg = null;

    <%for(int i = 0; i < message.size(); i++) {%>

    msg = "<%=message.get(i)%>"

    alert(msg);

    <%}%>

    </script>

		<%} %>


		<form action="<%=request.getContextPath()%>/CourseUpdateServlet" name="UISerchform" method="post"
			onSubmit="setwait();" />

		<style>
label {
	display: inline-block;
	width: 150px;
}

label2 {
	display: inline-block;
	width: 145px
}
</style>


<div class="app">

<c:import url="/WEB-INF/jsp/menu/menu.jsp"></c:import>

<div class="content">

  <h1>授業更新画面</h1>


こちらで授業情報を入力してください。<font color="red">※は必須入力項目です</font>

<br><input type="hidden" name="COURSE_ID" value=<%=searchBean.get(0).getCourseBean().getCourse_ID() %> size=50   >

<label for="cna">コース名<font color="red">※</font></label>  <input type="text" name="NAME_COURSE" size=20 value=<%=searchBean.get(0).getCourseBean().getCourse_Name()%> >

<br>  <label for="tu">授業料<font color="red">※</font></label> <input type="number" pattern="^[0-9]+$" name="TUITION" value=<%=searchBean.get(0).getCourseMeisai().getTuition()%> size=20 maxlength=10 onkeyDown="return numonly()">

<br> <label for="cod">コース内容<font color="red">※</font></label> <input type="text" name="DETAILS_COURSE" value=<%=searchBean.get(0).getCourseBean().getDetail() %>  size=50>

<br> <label for="sc">学校

	  <select name="TARGET">

			<option value=<%=searchBean.get(0).getContextBean().getGrade() %> >
			<%=searchBean.get(0).getContextBean().getTarget() %>
			<%for(int i = 0; i < context.size(); i++){%>
			<option value=<%=context.get(i).getGrade() %>>
				<%if(context.get(i).getTarget() != null) {%>

				<%=context.get(i).getTarget()%>
				<%} %>


				<%} %>

		</select>


		  <br> <label for="st">講義開始期間<font color="red">※</font></label>

		   <select name="Year_Start">

		   		<option value=<%=new SimpleDateFormat("yyyy").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>>
		   		<%=new SimpleDateFormat("yyyy").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>
		   		</option>
		   		<%for (int i = 2022; i < 2051; i++){ %>
		   		<option value= <%=i%> > <%=i%></option>
		   		<%} %>

		   </select>年

		   <select name="Month_Start">
		   		<option value=<%=new SimpleDateFormat("MM").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>>
		   		<%=new SimpleDateFormat("MM").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>
		   		</option>
		   		<%for (int i = 1; i < 13; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>月

		   <select name="Day_Start">
		   		<option value=<%=new SimpleDateFormat("dd").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>>
		   		<%=new SimpleDateFormat("dd").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>
		   		</option>
		   		<%for(int i = 1; i < 32; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>日

		   <select name="Hour_Start">
		   		<option value=<%=new SimpleDateFormat("HH").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>>
		   		<%=new SimpleDateFormat("HH").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>
		   		</option>
		   		<%for(int i = 0; i < 24; i++) {%>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>時

		   <select name="Minute_Start">
		   		<option value=<%=new SimpleDateFormat("mm").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>>
		   		<%=new SimpleDateFormat("mm").format(searchBean.get(0).getCourseMeisai().getStart_Lecture()) %>
		   		</option>
		   		<%for(int i = 0; i < 60; i++) { %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>分

		 <br> <label for="et">講義終了期間<font color="red">※</font></label>

		   <select name="Year_End">
		   		<option value=<%=new SimpleDateFormat("yyyy").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>>
		   		<%=new SimpleDateFormat("yyyy").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>
		   		</option>
		   		<%for (int i = 2022; i < 2051; i++){ %>
		   		<option value= <%=i%> > <%=i%></option>
		   		<%} %>

		   </select>年

		   <select name="Month_End">
		   		<option value=<%=new SimpleDateFormat("MM").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>>
		   		<%=new SimpleDateFormat("MM").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>
		   		</option>
		   		<%for (int i = 1; i < 13; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>月

		   <select name="Day_End">
		   		<option value=<%=new SimpleDateFormat("dd").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>>
		   		<%=new SimpleDateFormat("dd").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>
		   		</option>
		   		<%for(int i = 1; i < 32; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>日

		   <select name="Hour_End">
		   		<option value=<%=new SimpleDateFormat("HH").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>>
		   		<%=new SimpleDateFormat("HH").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>
		   		</option>
		   		<%for(int i = 0; i < 24; i++) {%>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>時

		   <select name="Minute_End">
		   		<option value=<%=new SimpleDateFormat("mm").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>>
		   		<%=new SimpleDateFormat("mm").format(searchBean.get(0).getCourseMeisai().getEnd_Lecture()) %>
		   		</option>
		   		<%for(int i = 0; i < 60; i++) { %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>分


          <br>	<label for="su">科目名<font color="red">※</font></label>

          <select name="SUBJECT">

			<option value=<%=searchBean.get(0).getCourseMeisai().getSubject()%>>
			<%if(searchBean.get(0).getCourseMeisai().getSubject() == 1) {%>
			国語
			<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getSubject() == 2) {%>
			数学
			<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getSubject() == 3) {%>
			英語
			<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getSubject() == 4) {%>
			理科
			<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getSubject() == 5) {%>
			社会
			<%} %>

			</option>

			<%for(int i = 0; i < subject.size(); i++){%>
			<option value=<%=subject.get(i).getID()%>>
				<%if(subject.get(i).getName() != null) {%>

				<%=subject.get(i).getName()%>
				<%} %>
			</option>
				<%} %>

		</select>

	<br> <label2 for="le">開催曜日<font color="red">※</font></label2>
		<select name="DAY_LECTURE">
			<option value=<%=searchBean.get(0).getCourseMeisai().getDay_Lecture()%>>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 1){ %>月<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 2){ %>火<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 3){ %>水<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 4){ %>木<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 5){ %>金<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 6){ %>土<%} %>
			<%if(searchBean.get(0).getCourseMeisai().getDay_Lecture() == 7){ %>日<%} %>
			</option>
			<option value=1>月</option>
			<option value=2>火</option>
			<option value=3>水</option>
			<option value=4>木</option>
			<option value=5>金</option>
			<option value=6>土</option>
			<option value=7>日</option>
		</select>

	<br> <label2 for="te">講師名<font color="red">※</font></label2>
		 <select name="TEACHER">
			<option value=<%=searchBean.get(0).getCourseMeisai().getTEACHER() %>><%=searchBean.get(0).getCourseMeisai().getTEACHER() %></option>
			<%for(int i= 0; i < user.size(); i++) {%>
			<option value=<%=user.get(i).getUser_NAME() %>>
				<%if(user.get(i).getUser_NAME() != null) {%>
				<%=user.get(i).getUser_NAME() %>
				<%} %>
				<%} %>
			</option>
		</select>

	<br>
		 <input type="submit" name="update" value="更新" id="insert" onClick="return checkvalue()" />
		 <input type="submit" name="search" value="検索画面へ" id="back" />

		</form>



  </div>


  </div>


</body>
</html>