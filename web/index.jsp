<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session != null && session.getAttribute("nickname") != null){
      response.sendRedirect("/jsp/userProfile.jsp");
    }
%>
<html>
<head>
  <title>Main Page | Write your best</title>
  <meta charset="utf-8">
  <meta lang="en">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/style.common.css" rel="stylesheet">
  <link href="css/style.index.css" rel="stylesheet">
  <link rel="icon" type="img/ico" href="images/logo.ico">
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(
                function () {
                    $('#signupSubmit').click(
                           function () {
                               var nickname = $('#nickname').val();
                               var email = $('#email').val();
                               var password = $('#password').val();
                               var passwordAgain = $('#passwordRep').val();
                               $.ajax(
                                       {
                                           type: 'POST',
                                           data: {action:"SIGNUP", nickname: nickname, email: email, password: password, passwordRep: passwordAgain},
                                           url: 'WriteYourBest.do',
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
          <img src="images/icon.png" alt="icon" class="img-responsive" id="logo">
        </a>
        <h4 class="navbar-text">Write your best</h4>
      </div>

      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="jsp/logIn.jsp">Log In</a></li>
          <li><a href="index.jsp">Sign Up</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <div id="getStartedForm">
    <h1 class="text-center">BECOME THE BEST WRITER</h1>
    <h3 class="text-center"><i>Write every day</i></h3>
    <button id="getStartedButton" class="btn-lg btn-default text-center center-block">Get started</button>
  </div>

</div>

<div class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-6">
      <h2><b>Your motivation to write starts today.</b></h2>
      <br>
      <ul id="promptsList">
        <li><span>Every day you receive a new writing prompt.</span></li><br>
        <li><span>Keep your compositions private or make it publicly visible.</span></li><br>
        <li><span>Read compositions of your friends.</span></li><br>
        <li><span>Improve your writing skills.</span></li><br>
        <li><span>Become the best writer.</span></li>
      </ul>
    </div>
    <div class="col-xs-12 col-sm-6">
      <form role="form">
        <div class="form-group">
          <h2>Create account</h2>
            <span class="text-center" style="color:darkred" id="errorMessage"></span>
        </div>
        <div class="form-group">
          <label class="control-label" for="nickname">Your nickname</label>
          <input id="nickname" type="text" maxlength="30" class="form-control" required>
        </div>
        <div class="form-group">
          <label class="control-label" for="email">Email</label>
          <input id="email" type="email" maxlength="30" class="form-control" required>
        </div>
        <div class="form-group">
          <label class="control-label" for="password">Password</label>
          <input id="password" type="password" maxlength="30" class="form-control" placeholder="at least 6 characters"  required>
        </div>
        <div class="form-group">
          <label class="control-label" for="passwordRep">Password again</label>
          <input id="passwordRep" type="password" maxlength="30" class="form-control" required>
        </div>
        <div class="form-group">
          <button id="signupSubmit" type="button" value="sign up submit" class="btn btn-info btn-block">Create your account</button>
        </div>

        <p class="text-center"><a href="#">Forgot your password?</a></p>
        <p class="text-center">Already have an account? <a href="jsp/logIn.jsp">Log in</a></p>
      </form>
    </div>
  </div>
</div>
<br><br><br><br><br>
<nav class="navbar-fixed-bottom" id="footer">
  <div class="container-fluid">
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav navbar-left">
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
        <li><a href="jsp/logIn.jsp">Login</a></li>
        <li><a href="index.jsp">Signup</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li>Paramonova Anastasia</li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>
