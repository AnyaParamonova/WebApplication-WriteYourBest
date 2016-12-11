<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session != null && session.getAttribute("nickname") != null){
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WriteYourBest.do?action=CREATEWALL");
        dispatcher.forward(request, response);
    }
%>
<html>
<head>
    <title>Log In | Write your best</title>
    <meta charset="utf-8">
    <meta lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/log.in.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="/image/logo.ico">

    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/logInRequest.js"></script>
</head>
<body style='background: url("../image/background.jpg") no-repeat; background-size: cover;'>

<div id="topBanner">
    <%@include file="/html/navbar.before.authorization.html" %>
</div>
<div id="space"></div>
<div id="form-container">
    <form role="form" id="logInform">
        <div class="form-group">
            <h2>Log in</h2>
            <span class="text-center" style="color:darkred" id="errorMessage"></span>
        </div>
        <div class="form-group">
            <label class="control-label" for="nickname">Your name</label>
            <input id="nickname" type="text" maxlength="30" class="form-control">
        </div>
        <div class="form-group">
            <label class="control-label" for="password">Password</label>
            <input id="password" type="password" maxlength="30" class="form-control">
        </div>
        <br>
        <div class="form-group">
            <button id="logIn-submit" type="button" class="btn btn-info btn-block">Log in</button>
        </div>
        <br>
        <p class="text-center"><a href="#">Forgot your password?</a></p>
        <p class="text-center">Don't have an account? <a href="/index.jsp">Sign up</a></p>
    </form>
</div>

<%@include file="/html/footer.before.authorization.html"%>
</body>
</html>