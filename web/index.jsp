<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if(session != null && session.getAttribute("nickname") != null){
      RequestDispatcher dispatcher = request.getRequestDispatcher("/WriteYourBest.do?action=DETERMINE");
      dispatcher.forward(request, response);
    }
%>
<html>
<head>
  <title>Main Page | Write your best</title>
  <meta charset="utf-8">
  <meta lang="en">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <link href="/css/common.css" rel="stylesheet">
  <link href="/css/index.css" rel="stylesheet">
  <link rel="icon" type="img/ico" href="/image/logo.ico">

  <script src="/js/jquery-3.1.1.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <script src="/js/signUpRequest.js"></script>

</head>
<body style='background: url("/image/background.jpg") no-repeat; background-size: cover;'>
<div id="topBanner">
  <%@include file="html/navbar.before.authorization.html" %>
  <div id="getStartedForm">
    <h1 class="text-center">BECOME THE BEST WRITER</h1>
    <h3 class="text-center"><i>Write every day</i></h3>
    <a href="#start" id="getStartedButton" class="btn-lg btn-default text-center center-block">Get started</a>
  </div>

</div>
<a name="start"></a>
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
            <span class="text-center" style="color: darkred" id="errorMessage"></span>
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
        <p class="text-center">Already have an account? <a href="/jsp/LogInPage.jsp">Log in</a></p>
      </form>
    </div>
  </div>
</div>
<br><br><br><br><br>
<%@include file="/html/footer.before.authorization.html"%>
</body>
</html>
