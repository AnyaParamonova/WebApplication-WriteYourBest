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
    <link href="/css/add.theme.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="/image/logo.ico">

    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/addThemeRequest.js"></script>
</head>
<body>

<%@include file="/html/navbar.after.authorization.html"%>

<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-3">
            <%@include file="/html/menu.admin.html"%>
        </div>
        <div class="col-xs-6">
            <div class="container-fluid">
                <form role="form">
                    <div class="form-group">
                        <h2>Add new theme</h2>
                        <span class="text-center" style="color:darkred" id="error-message-add"></span>
                        <span class="text-center" style="color:green" id="success-message-add"></span>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="new-theme">Enter theme</label>
                        <input id="new-theme" type="text" class="form-control">
                    </div>
                    <br>
                    <div class="form-group">
                        <button id="add-submit" type="button" class="btn btn-info btn-block">Add</button>
                    </div>
                    <br><br><br>
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

