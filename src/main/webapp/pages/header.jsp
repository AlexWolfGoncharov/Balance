<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<sec:authentication var="curentuser" property="principal" />

<nav class ="navbar navbar-inverse navbar-fixed-top navbar-default" >
    <!-- <div class="header" style="heigth:75px"> -->
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"  style="color: #4cae4c">
                <span style= "font-size: 1.7em; vertical-align:middle;"><i class="material-icons" style="vertical-align:middle; font-size:1.2em;">attach_money</i>BalanceSystem </span>
            </a>

        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar">
            <!-- <div id="logo_img"><a style="width:25%" class="logo" ></a></div>
             -->

            <!-- <div class="menu-container" > -->
            <!-- <menu id="menu" class="topmenu" > -->

            <ul class = "nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a  id="add+"  href="<c:url value="/addbalance" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">note_add</i>  Добавить поступление</a></li>






                    </sec:authorize>



                    <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="material-icons" style="vertical-align:middle;font-size:3em;">view_list</i> Просмотр <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li role="separator" class="divider"></li>
                            <li><a  id="users1"  href="<c:url value="/allopercontract" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">work</i>Поступления по Контрактам</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a  id="dept"  href="<c:url value="/alloperdept" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">business</i>Поступления по Департаментаментам</a></li>
                            <li role="separator" class="divider"></li>




                        </ul>
                    </li>





                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right ">
                <sec:authorize access="hasRole('ROLE_ADMIN')">

                    <li> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="material-icons" style="vertical-align:middle;font-size:3em;">supervisor_account</i> Управление <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li role="separator" class="divider"></li>
                            <li><a  id="users1"  href="<c:url value="/userlist" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">people</i>Пользователи</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a  id="dept"  href="<c:url value="/deplist" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">business</i>Департаменты</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a  id="contrAgents"  href="<c:url value="/contragents" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">contacts</i>Котрагенты</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a  id="contracts"  href="<c:url value="/contracts" />"><i style="vertical-align:middle; font-size: 3em;" class="material-icons">work</i>Контракты</a></li>



                        </ul>
                    </li>

                </sec:authorize>

                <li>
                    <sec:authorize access="!isAuthenticated()">
                        <a   href="<c:url value="/login" />" ><i class="material-icons login" style="vertical-align:middle;font-size:3em;">power_settings_new</i></a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a   href="<c:url value="/logout" />" ><i class="material-icons logout" style="vertical-align:middle;font-size:3em;">power_settings_new</i></a>


                    </sec:authorize>
                </li>




            </ul>


        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

