<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Управление пользователями. $BalanceSystem</title>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

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
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script> -->

</head>
<body>
<%@include file="header.jsp" %>

<c:if test="${!empty contragentsList}">
	<div class="container">
		<h3>Список Контрагентов:</h3>

		<table class="table table-view">
			<tr>
				<th>#</th>
				<th>Найменование</th>
				<th>Адрес</th>
				<th>&nbsp;</th>

			</tr>
			<c:forEach items="${contragentsList}" var="contragent" varStatus="myIndex">
				<tr>
					<td>${myIndex.index + 1}</td>
					<td>${contragent.name}</td>
					<td>${contragent.address}</td>
					<td><a href="./delete/contragent/${contragent.id}" class="btn btn-danger"> Удалить</a></td>

				</tr>
			</c:forEach>
		</table>

	</div>
</c:if>

<div class="container">


	<h2>Добавить Контрагента</h2>

	<form:form method="post" action="addContagent" commandName="contragent" class="form-horizontal">
		<div class="form-group">
			<form:label path="name" class="col-sm-2 control-label">
				Название:
			</form:label>
			<div class="col-sm-10">
				<form:input path="name" class="form-control"/>
			</div>

		</div>
		<div class="form-group">

			<form:label path="address" class="col-sm-2 control-label">
				Описание:
			</form:label>
			<div class="col-sm-10">
				<form:input path="address" class="form-control"/>
			</div>


		</div>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>


	</form:form>
</div>
</body>
</html>