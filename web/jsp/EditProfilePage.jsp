<%--
  Created by IntelliJ IDEA.
  User: Anastasia_Paramonova
  Date: 11.12.2016
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session == null || session.getAttribute("nickname") == null){
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
    <link href="/css/edit.profile.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="/image/logo.ico">

    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/changeDataRequests.js"></script>
</head>
<body>

<%@include file="/html/navbar.after.authorization.html"%>

<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-3">
            <%@include file="/html/menu.user.html"%>
        </div>
        <div class="col-xs-6">
            <div class="container-fluid">
                <form role="form">
                    <div class="form-group">
                        <h2>Edit profile</h2>
                        <span class="text-center" style="color:darkred" id="error-message-edit"></span>
                        <span class="text-center" style="color:green" id="success-message-edit"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="new-nickname">Change your nickname</label>
                        <input id="new-nickname" type="text" maxlength="30" class="form-control" value="${sessionScope.nickname}">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="new-email">Change your email</label>
                        <input id="new-email" maxlength="30" class="form-control" value="${sessionScope.email}">
                    </div>
                    <br>
                    <div class="form-group">
                        <button id="save-edit-submit" type="button" class="btn btn-info btn-block">Save</button>
                    </div>
                </form>
                <br>
                <form role="form">
                    <div class="form-group">
                        <h2>Change password</h2>
                        <span class="text-center" style="color:darkred" id="error-message-change"></span>
                        <span class="text-center" style="color:green" id="success-message-change"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="old-password">Enter your old password</label>
                        <input id="old-password" type="password" maxlength="30" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="new-password">Enter your new password</label>
                        <input id="new-password" type="password" maxlength="30" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="new-password-rep">Repeate your new password</label>
                        <input id="new-password-rep" type="password" maxlength="30" class="form-control">
                    </div>
                    <br>
                    <div class="form-group">
                        <button id="save-change-submit" type="button" class="btn btn-info btn-block">Change</button>
                    </div>
                </form>
                <br><br><br>
            </div>

        </div>
    </div>
</div>

<%@include file="/html/footer.after.authorization.html"%>
<br><br><br><br><br>
</body>
</html>
