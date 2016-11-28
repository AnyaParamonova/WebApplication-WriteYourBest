<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session == null || session.getAttribute("nickname") == null){
        response.sendRedirect("/index.jsp");
    }
%>
<html>
<head>
    <title>Profile | Write your best</title>
    <meta charset="utf-8">
    <meta lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.common.css" rel="stylesheet">
    <link href="../css/style.userProfile.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="../images/logo.ico">
</head>
<body id="page">
<div>
    <nav class="navbar navbar-default" id="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="../images/icon.png" alt="icon" class="img-responsive" id="logo">
                </a>
                <h4 class="navbar-text">Write your best</h4>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="nickname">${sessionScope.nickname}<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Edit profile</a></li>
                            <li class="divider"></li>
                            <li><a href="/WriteYourBest.do?action=LOGOUT">Log out</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-3">
            <div class="sidebar-nav">
                <div class="well">
                    <ul class="nav nav-list">
                        <li class="nav-header text-center">Menu</li>
                        <li><a href="#" style="color: #2b542c;">My compositions</a></li>
                        <li><a href="#" style="color: #2b542c;">Create composition</a></li>
                        <li><a href="#" style="color: #2b542c;">My friends</a></li>
                        <li><a href="#" style="color: #2b542c;">Gallery</a></li>
                        <li><a href="#" style="color: #2b542c;">Edit profile</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-xs-9">
            <div class="well">
                <h1>
                    Accordion Menu With Icon</h1>
                Admin Dashboard Accordion Menu
            </div>
        </div>
    </div>
</div>

<nav class="navbar-fixed-bottom" id="footer">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="../index.jsp">Logout</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>Paramonova Anastasia</li>
            </ul>
        </div>
    </div>
</nav>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>