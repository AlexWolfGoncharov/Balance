<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
 
    <title>BalanceSystem. Enegropolis</title>
 
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <%--<link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">--%>
    <link href="<c:url value="/pages/css/main.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<%--<script--%>
            <%--src="<c:url value="/pages/js/jquery.js" />"></script>--%>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://storage.googleapis.com/code.getmdl.io/1.0.4/material.min.js"></script>
    <%--<link href="<c:url value="/pages/js/bootstrap.js" />" rel="text/javascript">--%>

 
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

    <![endif]-->
</head>
 <body>
 <%@include file="./header.jsp" %>
 
<div class="container">
 
    <div class="jumbotron center" style="margin-top: 20px;">
        <h1> <i class="material-icons" style="vertical-align:middle; font-size:1.2em;">attach_money</i>BalanceSystem</h1>
        <p class="lead">
            Система мониторинга баланса  компании Энергополис.
        </p>
        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
          <!--   <h3>Ваш логин: <sec:authentication property="principal.username" /></h3> -->
         <!--    <p><a class="btn btn-lg btn-info" href="<c:url value="/user/" />" role="button">Добавить нового пользователя</a> -->
           <!--  <a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p> -->
 
        </sec:authorize>
    </div>
 
    <div class="footer">
        <p>© AlexWolfGoncharov 2016</p>
    </div>
 
</div>
</body>
</html>