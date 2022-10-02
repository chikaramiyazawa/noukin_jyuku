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
<title>授業登録</title>
 <link rel="stylesheet"  href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
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





		<% SearchBeans searchBean = new SearchBeans();

   List<UserBean> user = new ArrayList<UserBean>();

   user = (List<UserBean>)session.getAttribute("user");

   List<SubjectBean> subject = new ArrayList<>();

   subject = (List<SubjectBean>)session.getAttribute("subject");

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


		<form action="<%=request.getContextPath()%>/CourseInsertServlet" name="UISerchform" method="post"
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

  <h1>授業登録</h1>


こちらで授業情報を入力してください。<font color="red">※は必須入力項目です</font>

<br> <label for="cid">コースID</label> <input type="text" name="COUSRE_ID" size=50 maxlangth=10 disabled />

<br>  <label for="cna">コース名<font color="red">※</font></label>  <input type="text" name="NAME_COURSE" size=20 />

<br>  <label for="tu">授業料<font color="red">※</font></label> <input type="number" pattern="^[0-9]+$" name="TUITION" size=20 maxlength=10 onkeyDown="return numonly()">

<br> <label for="cod">コース内容<font color="red">※</font></label> <input type="text" name="DETAILS_COURSE" size=20>

<br> <label for="sc">学校

     <font color="red">※</font> </label>

        <input type="radio" name="TARGET1" value=10 onchange="setOption(this);">小学校
		<input type="radio" name="TARGET1" value=20 onchange="setOption(this);">中学校
		<input type="radio" name="TARGET1" value=30 onchange="setOption(this);">高校

<br>

	<label2 for="ta">対象学年<font color="red">※</font></label2>
		<select id="TARGET2" name="TARGET2">
			<option value="">指定なし</option>
		</select>
			<script type="text/javascript">

    var elementy_school = [

         {value: 1, label: "1"} ,

         {value: 2, label: "2"} ,

         {value: 3, label: "3"} ,

         {value: 4, label: "4"} ,

         {value: 5, label: "5"} ,

         {value: 6, label: "6"}
     ];

    var middle_school = [

         {value: 1 ,  label: "1"},

         {value: 2 ,  label: "2"},

         {value: 3 ,  label: "3"}
     ];

    var high_school = [

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

		  <br> <label for="st">講義開始期間<font color="red">※</font></label>

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
		   </select>分

		 <br> <label for="et">講義終了期間<font color="red">※</font></label>

		   <select name="Year_End">
		   		<option value=""></option>
		   		<%for (int i = 2022; i < 2051; i++){ %>
		   		<option value= <%=i%> > <%=i%></option>
		   		<%} %>

		   </select>年

		   <select name="Month_End">
		   		<option value=""></option>
		   		<%for (int i = 1; i < 13; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>月

		   <select name="Day_End">
		   		<option value=""></option>
		   		<%for(int i = 1; i < 32; i++){ %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>日

		   <select name="Hour_End">
		   		<option value=""></option>
		   		<%for(int i = 0; i < 24; i++) {%>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>時

		   <select name="Minute_End">
		   		<option value=""></option>
		   		<%for(int i = 0; i < 60; i++) { %>
		   		<option value=<%=i%>><%=i%></option>
		   		<%} %>
		   </select>分


          <br>	<label for="su">科目名<font color="red">※</font></label>

          <select name="SUBJECT">
			<option value="">指定なし</option>
			<%for(int i = 0; i < subject.size(); i++){%>
			<option value=<%=subject.get(i).getID()%>>
				<%if(subject.get(i).getName() != null) {%>

				<%=subject.get(i).getName()%>
				<%} %>


				<%} %>

		</select>

	<br> <label2 for="le">開催曜日<font color="red">※</font></label2>
		<select name="DAY_LECTURE">
			<option value="">指定なし</option>
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
			<option value="">指定なし</option>
			<%for(int i= 0; i < user.size(); i++) {%>
			<option value=<%=user.get(i).getUser_NAME() %>>
				<%if(user.get(i).getUser_NAME() != null) {%>
				<%=user.get(i).getUser_NAME() %>
				<%} %>
				<%} %>
			</option>
		</select>

	<br>
		 <input type="submit" name="insert" value="登録" id="insert" onClick="return checkvalue()" />
		 <input type="submit" name="search" value="検索画面へ" id="back" />

		</form>



  </div>


  </div>


</body>
</html>