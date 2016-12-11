<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    if(session == null || session.getAttribute("nickname") == null){
        response.sendRedirect("/index.jsp");
    }
%>
<html>
<head>
    <title>Create | Write your best</title>
    <meta charset="utf-8">
    <meta lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <link href="/css/create.composition.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="/image/logo.ico">

    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body>

<%@include file="/html/navbar.after.authorization.html"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-3">
            <%@include file="/html/menu.html"%>
        </div>
        <div class="col-xs-9">
            <div class="container-fluid">
                <form action="/WriteYourBest.do" method="post" accept-charset="UTF-8">
                    <div class="form-group">
                        <label class="h3" style="color: #2b542c;">${requestScope.theme}</label>
                        <textarea name="body" class="form-control" rows="15" placeholder="Write your composition here" style="color: #2b542c;"></textarea>
                    </div>
                    <button name="action" value="SAVECOMPOSITION" id="createButton" type="submit" class="btn btn-default btn-sm pull-right" style="width: 200px">Save</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="/html/footer.after.authorization.html"%>
<br><br><br><br><br>
</body>
</html>