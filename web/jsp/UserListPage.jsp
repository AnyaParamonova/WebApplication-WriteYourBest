<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session == null || session.getAttribute("nickname") == null || !session.getAttribute("type").equals("admin")){
        response.sendRedirect("/index.jsp");
    }
%>
<html>
<head>
    <title>Wall | Write your best</title>
    <meta charset="utf-8">
    <meta lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/user.profile.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="/image/logo.ico">

    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script  src="/js/loadCompositionsRequest.js"></script>
</head>
<body>

<%@include file="/html/navbar.after.authorization.html"%>
<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-3">
            <%@include file="/html/menu.admin.html"%>
        </div>
        <div class="col-xs-9">
            <div id="compositions-box">
                <div class="container-fluid" id="compositions-wrapper">
                    ${requestScope.users}
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="/html/footer.after.authorization.html"%>
<br><br><br><br><br>
</body>
</html>