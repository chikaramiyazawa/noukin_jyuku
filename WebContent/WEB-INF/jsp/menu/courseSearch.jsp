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
<title>授業検索</title>
<link rel="stylesheet"  href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel="stylesheet">
  <link rel="stylesheet"  href="<c:url value='/css/style.css' />">
	<link rel="stylesheet"  href="<c:url value='/css/calendar.css' />">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<c:import url="/WEB-INF/jsp/menu/header.jsp"></c:import>

<div class="app">

<c:import url="/WEB-INF/jsp/menu/menu.jsp"></c:import>

<div class="content">



	<h1>授業検索画面</h1>

		<form action="<%=request.getContextPath()%>/CourseSearchServlet"  name="UISearchform"
			onSubmit="setwait();">

			<% List<SearchBeans> searchBean = new ArrayList<SearchBeans>();

   searchBean = (List<SearchBeans>)request.getAttribute("list");

  List<UserBean> user = new ArrayList<UserBean>();

  user = (List<UserBean>)session.getAttribute("user");

  List<SubjectBean> subject = new ArrayList<SubjectBean>();

  subject = (List<SubjectBean>)session.getAttribute("subject");

%>

			<%if(session.getAttribute("alert") != null){

    String  alert = (String)session.getAttribute("alert"); %>
			<%} %>


		<br> コース名:<input type="text" name="NAME_COURSE" id="NAME_COURSE" size=10>

		<br> <label >学校 </label>

        <input type="radio" name="TARGET1" value=10 onchange="setOption(this);">小学校
		<input type="radio" name="TARGET1" value=20 onchange="setOption(this);">中学校
		<input type="radio" name="TARGET1" value=30 onchange="setOption(this);">高校

<br>

	<label>対象学年</label2>
		<select id="TARGET2" name="TARGET2">
			<option value="">指定なし</option>
		</select>
			<script type="text/javascript">

    var elementy_school = [

         {value: "", label :"指定なし"},

         {value: 1, label: "1"} ,

         {value: 2, label: "2"} ,

         {value: 3, label: "3"} ,

         {value: 4, label: "4"} ,

         {value: 5, label: "5"} ,

         {value: 6, label: "6"}
     ];

    var middle_school = [

    	 {value: "", label :"指定なし"},

         {value: 1 ,  label: "1"},

         {value: 2 ,  label: "2"},

         {value: 3 ,  label: "3"}
     ];

    var high_school = [

    	 {value: "", label :"指定なし"},

         {value: 1 , label: "1"},

         {value: 2 , label: "2"},

         {value: 3 , label: "3"}

      ];

    function setOption(radio){
        var target;
        var pullobj;
        var i;

        if((radio.value) == 10){
            target = elementy_school;

        }else if((radio.value) == 20){
            target = middle_school;

        }else{
            target = high_school;
        }

        pullobj = document.getElementById('TARGET2');

        while(pullobj.lastChild){
            pullobj.removeChild(pullobj.lastChild);
        }
        for(i = 0; i < target.length; i++){
            let option = document.createElement('option');

            option.value = target[i].value;

            option.text = target[i].label;

            pullobj.appendChild(option);


        }

    }

		</script>


			 <br> <label>講義開始期間</label>

		   <select name="Year_Start">
		   		<option value=""></option>
		   		<%for (int i = 2022; i < 2051; i++){ %>
		   		<option value= <%=i%> > <%=i%></option>
		   		<%} %>

		   </select>年

		   <select name="Month_Start">
		   		<option value=""></option>
		   		<%for (int i = 1; i < 13; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>月

		   <select name="Day_Start">
		   		<option value=""></option>
		   		<%for(int i = 1; i < 32; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>日

		   <select name="Hour_Start">
		   		<option value=""></option>
		   		<%for(int i = 0; i < 24; i++) {%>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>時

		   <select name="Minute_Start">
		   		<option value=""></option>
		   		<%for(int i = 0; i < 60; i++) { %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>分 ～


			<br>
			科目名:<select name="ID_SUBJECT">
				<option value="">指定なし</option>
				<%for(int i = 0; i < subject.size(); i++){%>
				<option value=<%=subject.get(i).getName()%>>
					<%if(subject.get(i).getName() != null) {%>

					<%=subject.get(i).getName()%>
					<%} %>


					<%} %>

			</select>

			<br>
			 開催曜日:<select name="DAY_LECTURE">
				<option value="">指定なし</option>
				<option value="1">月</option>
				<option value="2">火</option>
				<option value="3">水</option>
				<option value="4">木</option>
				<option value="5">金</option>
				<option value="6">土</option>
				<option value="7">日</option>
			</select>

			<br>
			講師名: <select name="TEACHER">
				<option value="">指定なし</option>
				<%for(int i= 0; i < user.size(); i++) {%>
				<option value=<%=user.get(i).getUser_NAME() %>>
					<%if(user.get(i).getUser_NAME() != null) {%>
					<%=user.get(i).getUser_NAME() %>
					<%} %>
					<%} %>
				</option>
			</select>

			<br> <br> <input type="submit" name="create" value="新規作成">
			<input type="submit" name="search" value="検索">
		</form>


	<div>
		<div id="main">

		<br>
			<caption>検索結果</caption>
			<table border="1">
				<tr>
					<th>講師名</th>
					<th>コース名</th>
					<th>科目名</th>
					<th>開催曜日</th>
					<th>講義開始時間</th>
					<th>対象学年</th>
					<th>操作</th>
				</tr>
					<%if(searchBean != null) {%>
					<%for(int count = 0; count < searchBean.size(); count++) {%>
					<%if(searchBean.get(count).getCourseBean().getDelete_Flg() == 0) {%>
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

					<td>
					<button type="submit" name="update" value="<%=searchBean.get(count).getCourseBean().getCourse_ID()%>">更新</button>
					<button type="submit" name="delete" value="<%=searchBean.get(count).getCourseBean().getCourse_ID()%>">削除</button>
					</td>

				</tr>
				<%} %>
				<%} %>
				<%} %>
			</table>


		</div>
		</div>
	</div>
	</div>
</body>
</html>