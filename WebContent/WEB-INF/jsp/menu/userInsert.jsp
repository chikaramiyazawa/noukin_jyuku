<%@ page language="java" contentType="text/html;  charset=UTF-8"
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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録画面</title>
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


    <h1>ユーザー登録</h1>
    <div id="header">
        <SCRIPT language="Java Script">

function checkValue(){

    var excute = doucment.getElementById("Insert").value;

    if(window.confirm("登録を行います。よろしいですか？") == true){

    return true;

    }else{

    reture false;

    }

}
</SCRIPT>



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

        ユーザー登録画面です。こちらのフォームに入力してください。<font color="red">※は必須入力項目です</font>
        <form action="<%=request.getContextPath()%>/UserInsertServlet"
            method="post" onSubmit="setwait();">
            <br> ユーザーID:<font color="red">※</font>
            <input type="text" name="User_ID" size=20 /> <br> ユーザー名:<font color="red">※</font>
            <input type="text" name="User_NAME" ID="User_NAME" size=20 /> <br>

            パスワード:<font color="red">※</font>
            <input type="text" name="User_Pass" size=20 /> <br>
            権限:<font color="red">※</font>
            <input type="radio" name="Admin" id="Admin" value=1>学生

            <input type="radio" name="Admin" id="Admin" value=2>講師 <br>

            <input type="submit" name="insert" value="登録" id="insert" onClick="return checkvalue()" />

            <input type="submit" name="index" value="ユーザー一覧" id="index" />

        </form>

    </div>
    </div>
    </div>
</body>
</html>