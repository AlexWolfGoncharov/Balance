<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8">
	<title>Управление контрактами. $BalanceSystem</title>


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






	<div>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">

			<li role="presentation"  class="active">
				<a href="#date" aria-controls="date" role="tab" data-toggle="tab" id="date-tab">Выбор за дату</a></li>
			<li role="presentation">
				<a href="#contract" aria-controls="contract" role="tab" data-toggle="tab" id="contract-tab">Выбор по контракту</a></li>
			<li role="presentation">
				<a href="#all" aria-controls="all" role="tab" data-toggle="tab" id="all-tab">Отображение всех</a></li>

		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="date" aria-labelledby="date-tab">

				<h2>Выбор записей за период </h2>


				<form:form method="get" action="allopercontract"  class="form-inline">


					<div class="form-group">
						<label for="datestart">с </label>
						<input name="datestart" class="form-control" type="datetime" id="datestart" placeholder="дд.мм.гггг"/>
					</div>
					<div class="form-group">
						<label for="dateend">по </label>
						<input name="dateend" class="form-control" type="datetime" id="dateend" placeholder="дд.мм.гггг"/>
					</div>
					<button type="submit" class="btn btn-default">Поиск</button>


				</form:form>



			</div>
			<div role="tabpanel" class="tab-pane" id="contract" aria-labelledby="contract-tab">


				<h2>Выбор записей по контракту </h2>

				<form:form method="get" action="allopercontract"  class="form-inline">

					<select name="contractId" class="form-control">

						<c:forEach items="${contraсtsList}" var="contragent" varStatus="myIndex">
							<option value="${contragent.id}" label="№${contragent.contractNumber} | ${contragent.startDate} | ${contragent.description}" />
						</c:forEach>


					</select>
					<button type="submit" class="btn btn-default">Поиск</button>
				</form:form>


			</div>
			<div role="tabpanel" class="tab-pane" id="all" aria-labelledby="all-tab">


				<h2>Отображение всех записей</h2>

				<form:form method="get" action="allopercontract"  class="form-inline">

					<input hidden="true" name="all" value="1"/>

					<button type="submit" class="btn btn-default">Поиск</button>
				</form:form>




			</div>

		</div>

	</div>






	<c:if test="${!empty operContractList}">
		<div class="container">
			<h2>Список операций</h2>
			<c:if test="${!empty type}">
				<h3>${type}</h3>
			</c:if>


			<table class="table table-view">
				<tr>
					<th>#</th>
					<th>Дата время</th>
					<th>Контракт</th>
					<th>Сумма, грн</th>
					<th>НДС, грн</th>
					<th>Описание</th>
					<th>&nbsp;</th>

				</tr>
				<c:forEach items="${operContractList}" var="operContract" varStatus="myIndex">
					<tr>
						<td>${myIndex.index + 1}</td>
						<td>${operContract.time}</td>
						<td>${operContract.contractId.contractNumber}</td>
						<td>${operContract.summa}</td>
						<td>${operContract.ndc}</td>
						<td>${operContract.description}</td>
						<td>
							<div class="btn-group" role="group btn-group-sm" aria-label="Operations">
								<a href="./view/opercontract/${operContract.id}" class="btn btn-primary"><span class="glyphicon glyphicon-th-list" aria-hidden="true"></span></a>
								<a href="./edit/opercontract/${operContract.id}" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></a>
								<a href="./delete/opercontract/${operContract.id}" class="btn btn-danger"><span class="glyphicon glyphicon-trash" aria-hidden="true"></a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>


	</c:if>

		</div>

</div>


	<script type="text/javascript" src="<c:url value="/pages/js/bootstrap-datetimepicker.min.js" />" charset="UTF-8"></script>
	<script type="text/javascript" src="<c:url value="/pages/js/bootstrap-datetimepicker.ru.js" />" charset="UTF-8"></script>
	<script type="text/javascript">
		$('#${tab}-tab').tab('show')


		$("#datestart").datetimepicker({format: 'dd.mm.yyyy', autoclose: true,
			todayBtn: true, keyboardNavigation: true, language: 'ru', minView: 2});


		$("#dateend").datetimepicker({format: 'dd.mm.yyyy', autoclose: true,
			todayBtn: true, keyboardNavigation: true, language: 'ru', minView: 2});




	</script>




</body>
</html>