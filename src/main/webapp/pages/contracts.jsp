<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<c:set var="language" value="ru" scope="session" />
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Управление контрактами. BalanceSystem</title>

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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.4.5/jquery.datetimepicker.min.css"  rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.4.5/jquery.datetimepicker.min.js"></script>
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
<fmt:setLocale value="uk_UA" scope="session"/>
<c:if test="${error}">
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Внимание!</strong> ${error} .
    </div>
</c:if>

<c:if test="${!empty contraсtsList}">
    <div class="container">
        <div class="page-header">
            <h2>Управление контрактами</h2>
        </div>
        <h3>Список Контрактов:</h3>


        <table class="table table-view">
            <tr>
                <th>#</th>
                <th>Номер контракта</th>
                <th>Дата старта</th>
                <th>Сумма</th>
                <th>Контрагент</th>
                <th>Описание</th>
                <th>&nbsp;</th>

            </tr>
            <c:forEach items="${contraсtsList}" var="contract" varStatus="myIndex">
                <tr>
                    <td>${myIndex.index + 1}</td>
                    <td>${contract.contractNumber}</td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy"
                                        value="${contract.startDate}" /></td>

                    <td><fmt:formatNumber value="${contract.summ}"
                                          type="currency" currencyCode="UAH"/></td>
                    <td>${contract.contrAgentId.name}</td>
                    <td>${contract.description}</td>
                    <td><div class="btn-group" role="group btn-group-sm" aria-label="Operations">
                        <a href="/edit/contract/${contract.id}" class="btn btn-warning"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></a>
                        <a href="/delete/contract/${contract.id}" class="btn btn-danger confirm-modal"><span class="glyphicon glyphicon-trash" aria-hidden="true"></a>
                    </div></td>

                </tr>
            </c:forEach>
        </table>

    </div>
</c:if>

<div class="container">


    <h2>Добавить контракт</h2>

    <form:form method="post" action="${save}" commandName="contract" class="form-horizontal">

        <c:if test="${!empty contract}">
            <form:hidden path="id"/>
        </c:if>
        <div class="form-group">
            <form:label path="contractNumber" class="col-sm-2 control-label">
                Номер контракта:
            </form:label>
            <div class="col-sm-10">
                <form:input path="contractNumber" class="form-control"/>
            </div>

        </div>


        <div class="form-group">

            <form:label path="startDate" class="col-sm-2 control-label">
                Дата старта:
            </form:label>
            <div class="col-sm-10">
                <form:input path="startDate" class="form-control"  id="startDate"/>
            </div>


        </div>



        <div class="form-group">

            <form:label path="summ" class="col-sm-2 control-label">
                Сумма:
            </form:label>
            <div class="col-sm-10">
                <form:input path="summ" class="form-control"/>
            </div>


        </div>

        <div class="form-group">

            <form:label path="description" class="col-sm-2 control-label">
                Описание:
            </form:label>
            <div class="col-sm-10">
                <form:input path="description" class="form-control"/>
            </div>


        </div>

        <div class="form-group">

            <form:label path="contrAgentId" class="col-sm-2 control-label">
                Контрагент:
            </form:label>
            <div class="col-sm-10">


                <form:select path="contrAgentId.id" class="form-control">
                    <form:option value="0" label="Выберите Контрагента"/>



                    <!-- <form:options items="${contragentsList}"/> -->

                    <c:forEach items="${contragentsList}" var="contragent" varStatus="myIndex">
                        <form:option value="${contragent.id}" label="${contragent.name}" />
                    </c:forEach>


                    <!-- <form:options items="${contragentsList}"/> -->
                </form:select>
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
<script type="text/javascript" src="<c:url value="/pages/js/bootbox.min.js" />" charset="UTF-8"></script>

<script>



    $("#startDate").datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        todayBtn: true,
        keyboardNavigation: true,
        language: 'ru',
        minView: 2,
        pickerPosition: 'top-right'
    });



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
            bootbox.confirm("При удалении будут так же удалены все записи о поступлениях по данному контракту. Удалить?",
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