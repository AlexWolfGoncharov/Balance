<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

	<script>


		function setNdsSum() {



			if(document.getElementById('ndcForm').value != 0){
//				document.getElementById('ndcForm').disabled = false;
				document.getElementById('ndcForm').value = Math.round((document.getElementById('summa').value * 0.2)* 100) / 100;
			} else {

//				document.getElementById('ndcForm').disabled = true;
				document.getElementById('ndcForm').value = "";
			}
		}




		function validateForm() {

			var rows = document.getElementById("tableOperDept").rows;

			var sum = document.getElementById("opercontract.summa").textContent;
			sum = parseFloat(sum);
			 max = sum;
			if(rows.length > 2) {
				for (var i = 2; i < rows.length; i++){

					var a = parseFloat(document.getElementById("operDept.summa."+(i-1)).innerHTML);

					if (!isNaN(a))
						max = max - a;

				}
			}

			var x = document.forms["myForm"]["summa"];
			x.max = max;


		}

		function onBlur(){
			var x = document.forms["myForm"]["summa"].value;
			if  (x > max){
				alert("Сумма не должна быть более " + max + "грн.");
				document.forms["myForm"]["summa"].value = max;
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


	<h2>Просмотр записи</h2>





	<form:hidden path="id"/>


	<div class="form-group">

		<div class="col-sm-2 control-label text-right">
			<strong>Контракт:</strong>
		</div>

		<div class="col-sm-10">
			${opercontract.contractId.contractNumber}
		</div>
	</div>



	<div class="form-group">
		<div class="col-sm-2 control-label text-right">
			<strong>Описание:</strong>
		</div>
		<div class="col-sm-10">
			${opercontract.description}
		</div>

	</div>


	<div class="form-group">
		<div class="col-sm-2 control-label text-right">
			<strong>Дата:</strong>
		</div>
		<div class="col-sm-10">
			${opercontract.time}
		</div>


	</div>


	<div class="form-group">


		<div class="col-sm-2 control-label text-right">
			<strong>Сумма:</strong>
		</div>

		<div class="col-sm-10" id="opercontract.summa">
			${opercontract.summa}

		</div>


	</div>

	<div class="form-group">
		<div class="col-sm-2 control-label text-right">
			<strong>НДС:</strong>
		</div>

		<div class="col-sm-10">

			${opercontract.ndc}

		</div>


	</div>



	<div class="form-group">

		<div class="col-sm-12 ">

			<label class="control-label">Разбивка поступления по Департаментам:</label>

		</div>


	</div>
	<div class="form-group">

		<div class="col-sm-12 ">
			<table class="table table-striped" id="tableOperDept">
				<tr>

					<th>#</th>
					<th>Департамент</th>
					<th>Дата время</th>
					<th>Сумма, грн</th>
					<th>НДС, грн</th>
					<th>Описание</th>
					<th>&nbsp;</th>
				</tr>


				<c:forEach var="operDept" items="${opercontract.receiptOperationsDepartmentList}" varStatus="status">

				<tr>
					<td>
							${status.count}

					</td>
						<td>

								${operDept.departmentId.nameOfDepartment}



						</td>

					<td>

							${operDept.time}
					</td>

					<td>
						<div id="operDept.summa.${status.count}">
								${operDept.summa}
						</div>


					</td>

					<td>
							${operDept.ndc}

					</td>
					<td>
							${operDept.description}
					</td>
					<td>
						<a href="/delete/operdep/${operDept.id}" class="btn btn-danger"><span class="glyphicon glyphicon-trash" aria-hidden="true"></a>
					</td>


				</tr>


					<%--<c:set var="max" scope="application" value="${max - operDept.summa}"/>--%>
				</c:forEach>


				<form:form method="post" id="myForm" action="/addoperdep" commandName="operdep" onsubmit="return validateForm()" class="form-horizontal">
				<tr>
					<td>
						<form:hidden path="receptOpContrId" value="${opercontract.id}"/>
					</td>

					<td>
						<form:select path="departmentId.id" class="form-control">

							<c:forEach items="${depList}" var="department" varStatus="myIndex">
								<form:option value="${department.id}" label="${department.nameOfDepartment}" />
							</c:forEach>

						</form:select>


					</td>
					<td>
						<form:input path="time" name="operdep.time" id="time" value="" type="datetime" class="form-control"/>

					</td>

					<td>
						<form:input path="summa" name="summa" id="summa" value="" type="number" step="any" class="form-control"
								onchange="validateForm()" onblur="onBlur()"	 />

					</td>

					<td>
						<%--<input type="checkbox" class="checkbox" id="ndcCheck" onclick="setNdsSum()">--%>
						<form:input path="ndc" name="ndcForm" id="ndcForm" value="" type="number" step="any" class="form-control"  onchange="setNdsSum()"/>

					</td>
					<td>
						<form:input path="description" name="operdep.description" id="description" value="" class="form-control" />

					</td>

					<td>
						<button type="submit" class="btn btn-success">+</button>
					</td>


				</tr>




						</form:form>
		</div>

		</td>

		</tr>







		</table>


	</div>


</div>




<script type="text/javascript" src="<c:url value="/pages/js/bootstrap-datetimepicker.min.js" />" charset="UTF-8"></script>
<script type="text/javascript" src="<c:url value="/pages/js/bootstrap-datetimepicker.ru.js" />" charset="UTF-8"></script>
<script type="text/javascript">


	$("#time").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss', autoclose: true,
		todayBtn: true, keyboardNavigation: true, language: 'ru', minView: 2});
</script>

</body>
</html>