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
<script>

	function validetePass(){

		var pass1 = document.getElementById("pass1").value;
		var pass2 =document.getElementById("pass2").value;

		if (pass1 !== pass2){
			document.getElementById("wrong").textContent = "Введенные пароли не совпадают."

		} else {
			document.getElementById("wrong").textContent = ""
		}




	}

</script>
</head>
<body>
<%@include file="header.jsp" %>

<c:if test="${!empty userList}">
	<div class="container">
		<h3>List of USERS</h3>

		<table class="table table-view">
			<tr>
				<th>#</th>
				<th>login</th>
				<th>ФИО</th>
				<th>access</th>
				<th>&nbsp;</th>
			</tr>
			<c:forEach items="${userList}" var="user" varStatus="myIndex">
				<tr>
					<td>${myIndex.index + 1}</td>
					<td>${user.login}</td>
					<td>${user.lastName}, ${user.firstName}</td>
					<td>${user.access}</td>
					<td><a href="./delete/user/${user.login}"> delete</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</c:if>

<div class="container">


	<h2>Добавить пользователя</h2>

	<form:form method="post" action="addusers" commandName="user" class="form-horizontal">
		<div class="form-group">
			<form:label path="login" class="col-sm-2 control-label">
				Логин:
			</form:label>
			<div class="col-sm-10">
				<form:input path="login" class="form-control" autocomplete="off"/>
			</div>

		</div>

		<div class="form-group">

			<form:label path="password" type="password" class="col-sm-2 control-label">
				Пароль:
			</form:label>
			<div class="col-sm-10">
				<form:password path="password" id="pass1" class="form-control" autocomplete="off"/>
			</div>


		</div>


		<div class="form-group">
			<label for="pass2" class="col-sm-2 control-label">Повторите Пароль:</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="pass2" autocomplete="off" onblur="validetePass()"/>
			</div>

		</div>

<div>
	<a id="wrong" class="text-danger"></a>
</div>



		<div class="form-group">

			<form:label path="firstName" class="col-sm-2 control-label">
				Имя:
			</form:label>
			<div class="col-sm-10">
				<form:input path="firstName" class="form-control"/>
			</div>


		</div>


		<div class="form-group">

			<form:label path="middleName" class="col-sm-2 control-label">
				Отчество:
			</form:label>
			<div class="col-sm-10">
				<form:input path="middleName" class="form-control"/>
			</div>

		</div>





		<div class="form-group">

			<form:label path="lastName" class="col-sm-2 control-label">
				Фамилия:
			</form:label>
			<div class="col-sm-10">
				<form:input path="lastName" class="form-control"/>
			</div>

		</div>




		<div class="form-group">

			<form:label path="email" class="col-sm-2 control-label">
				email:
			</form:label>
			<div class="col-sm-10">
				<form:input path="email" class="form-control" />
			</div>

		</div>



		<div class="form-group">

			<form:label path="userRoles" class="col-sm-2 control-label">
				Роли:
			</form:label>
			<div class="col-sm-10">
				<form:select path="userRoles" class="form-control">
					<form:option value="ADMIN" label="ADMIN" />
					<form:option value="USER" label="USER" />

				</form:select>
			</div>

		</div>



		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="access" value="true"  class="checkbox"/> Доступ разрешен
					</label>
				</div>
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