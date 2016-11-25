<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession userSession = request.getSession(false);
    if(userSession != null && userSession.getAttribute("user") != null){
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/userProfile.jsp");
        dispatcher.forward(request, response);
    }
%>
<html>
<head>
    <title>Log In | Write your best</title>
    <meta charset="utf-8">
    <meta lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.common.css" rel="stylesheet">
    <link href="../css/style.logInPage.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="../images/logo.ico">

    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(
                function () {
                    $('#logInSubmit').click(
                            function () {
                                var nickname = $('#nickname').val();
                                var password = $('#password').val();
                                $.ajax(
                                        {
                                            type: 'POST',
                                            data: {nickname: nickname, password: password},
                                            url: '../LogIn.do',
                                            success: function (result) {
                                                if(result != ""){
                                                    $('#errorMessage').html(result);
                                                }
                                                else {
                                                    window.location.href = '/jsp/userProfile.jsp';
                                                }
                                            }
                                        }
                                );
                            }
                    );
                }
        );
    </script>

</head>
<body id="page">

<div id="topBanner">
    <nav class="navbar navbar-default" id="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <img src="../images/icon.png" alt="icon" class="img-responsive" id="logo">
                </a>
                <h4 class="navbar-text">Write your best</h4>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logIn.jsp">Log In</a></li>
                    <li><a href="../index.jsp">Sign Up</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div id="space"></div>
<div id="formContainer">
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
            <input id="password" type="password" maxlength="30" class="form-control" placeholder="at least 6 characters">
        </div>
        <br>
        <div class="form-group">
            <button id="logInSubmit" type="button" class="btn btn-info btn-block">Log in</button>
        </div>
        <br>
        <p class="text-center"><a href="#">Forgot your password?</a></p>
        <p class="text-center">Don't have an account? <a href="../index.jsp">Sign up</a></p>
    </form>
</div>


<nav class="navbar-fixed-bottom" id="footer">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="logIn.jsp">Login</a></li>
                <li><a href="../index.jsp">Signup</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>Paramonova Anastasia</li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>