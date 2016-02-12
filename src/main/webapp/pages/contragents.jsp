<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Управление Контрагентами. BalanceSystem</title>

	<link rel="icon" href="<c:url value="/pages/img/favicon.ico" />" type="image/x-icon">

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
					<td><div class="btn-group" role="group btn-group-sm" aria-label="Operations">
							<a href="/edit/contragent/${contragent.id}" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></a>
							<a href="/delete/contragent/${contragent.id}" class="btn btn-danger confirm-modal"><span class="glyphicon glyphicon-trash" aria-hidden="true"></a>
						</div></td>

				</tr>
			</c:forEach>
		</table>

	</div>
</c:if>

<div class="container">
	<div class="page-header">
		<h2>Управление Контрагентами</h2>
	</div>

	<h2>Добавить Контрагента</h2>

	<form:form method="post" action="/addContagent" commandName="contragent" class="form-horizontal">
		<form:hidden path="id"/>
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
				Адресс:
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
<script type="text/javascript" src="<c:url value="/pages/js/bootbox.min.js" />" charset="UTF-8"></script>

 <script>



	 bootbox.addLocale('my',
			 {
				 OK : 'OK',
				 CANCEL : 'Отменить',
				 CONFIRM : 'Удалить'
			 }
	 );


	 bootbox.setLocale('my');

	 $(document).on("click", ".confirm-modal", function(e) {
		 e.preventDefault();
		 var lHref = $(this).attr('href');
		 if(lHref !== 'undefined') {
			 bootbox.confirm("При удалении будут так же удалены вск контракты по контрагенту и все записи о поступлениях по ним. Удалить?",
					 function (result) {
						 if (result) {
							 window.location.href = lHref;
						 }
					 });
		 }
	 });



 </script>
</body>
</html>