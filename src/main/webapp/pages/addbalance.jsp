<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Управление контрактами. $BalanceSystem</title>

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




	<%--DatePicker JA and CSS--%>
	<%--<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.4.5/jquery.datetimepicker.min.css"  rel="stylesheet">--%>
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.4.5/jquery.datetimepicker.min.js"></script>--%>
	<link href="<c:url value="/pages/css/bootstrap-datetimepicker.min.css" />" rel="stylesheet">


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


		function setNdsSum() {



			if(document.getElementById('ndcCheck').checked){
				document.getElementById('ndcForm').disabled = false;
				document.getElementById('ndcForm').value = (document.getElementById('summa').value * 0.2);} else {

				document.getElementById('ndcForm').disabled = true;
				document.getElementById('ndcForm').value = "";
			}
		}

	</script>

</head>
<body>
<%@include file="header.jsp" %>

<c:if test="${error}">
	<div class="alert alert-warning alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<strong>Внимание!</strong> ${error} .
	</div>
</c:if>


<div class="container">
	<div class="page-header">
		<h2>Добавить поступление по Контракту</h2>
	</div>



	<form:form method="post" action="/addbalance" commandName="operContract" class="form-horizontal">


		<form:hidden path="id"/>


		<div class="form-group">

			<form:label path="contractId" class="col-sm-2 control-label">
				Контракт:
			</form:label>
			<div class="col-sm-10">


				<form:select path="contractId.id" class="form-control">
					<form:option value="0" label="Выберите Контракт"/>



					<!-- <form:options items="${contractList}"/> -->

					<c:forEach items="${contractList}" var="contragent" varStatus="myIndex">
						<form:option value="${contragent.id}" label="№${contragent.contractNumber} | ${contragent.startDate} | ${contragent.description}" />
					</c:forEach>


					<!-- <form:options items="${contragentsList}"/> -->
				</form:select>
			</div>


		</div>

		<div class="form-group">
			<form:label path="description" class="col-sm-2 control-label">
				Описание:
			</form:label>
			<div class="col-sm-10">
				<form:textarea path="description" class="form-control"/>
			</div>

		</div>


		<div class="form-group">

			<form:label path="time" class="col-sm-2 control-label">
				Дата:
			</form:label>
			<div class="col-sm-10">
				<form:input path="time" class="form-control" type="datetime" id="datetime"/>
			</div>


		</div>



		<div class="form-group">

			<form:label path="summa" class="col-sm-2 control-label">
				Сумма:
			</form:label>
			<div class="col-sm-10">
				<form:input path="summa"  id="summa" class="form-control"/>
			</div>


		</div>

		<div class="form-group">

			<form:label path="ndc" class="col-sm-2 control-label">
				<div class="checkbox"><label><input type="checkbox" class="checkbox" id="ndcCheck" onclick="setNdsSum()"><strong>НДС:</strong></label></div>
			</form:label>

			<div class="col-sm-10">
				<form:input path="ndc" id = "ndcForm" class="form-control" disabled="true"/>
			</div>


		</div>





		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>


	</form:form>
</div>


<script type="text/javascript" src="<c:url value="/pages/js/bootstrap-datetimepicker.min.js" />" charset="UTF-8"></script>
<script type="text/javascript" src="<c:url value="/pages/js/bootstrap-datetimepicker.ru.js" />" charset="UTF-8"></script>
<script type="text/javascript">


	$("#datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss', autoclose: true,
		todayBtn: true, keyboardNavigation: true, language: 'ru'});
</script>
<script>



	function formatDate(today){
		var year = today.getFullYear();
		var mounth = today.getMonth() +1;
		var day = today.getDate();
		var hours = today.getHours();
		var minutes = today.getMinutes();
		var seconds = today.getSeconds();

		//"yyyy-mm-dd HH:MM:ss"
		var formated = year + "-"
				+ (mounth < 10 ? "0"+mounth : mounth) + "-"
				+ (day < 10 ? "0"+day : day) + " "
				+ (hours < 10 ? "0"+hours : hours) + ":"
				+ (minutes < 10 ? "0"+minutes : minutes) + ":"
				+ (seconds < 10 ? "0"+seconds : seconds);

		return formated;

	}



	var today = new Date();
	var formated_date = formatDate(today);
	document.getElementById('datetime').value = formated_date;


	if (document.getElementById('ndcForm').value){
		document.getElementById('ndcForm').disabled = false;

	}

</script>

</body>
</html>