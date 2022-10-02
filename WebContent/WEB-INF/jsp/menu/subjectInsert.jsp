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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科目名登録画面</title>
</head>
<body>
    <h1>科目名登録画面</h1>
    <div id="header">
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

 科目名登録画面です。こちらのフォームに入力してください。<font color="red">※は必須入力項目です</font>

 <form action="<%=request.getContextPath()%>/SubjectInsertServlet"  method="post" onSubmit="setwait();">

 <br><font color="red">※</font>ID<input type="number" pattern="^[0-9]+$" name="ID" size=20 maxlength=10 onkeyDown="return numonly()">

 <br><font color="red">※</font>科目名 <input type="text" name="Name" size=20 />

<br>
 <input type="submit" name="insert" value="登録" id="insert" onClick="return checkvalue()" />

 <input type="submit" name="index" value="科目一覧" id="index" />





</body>
</html>