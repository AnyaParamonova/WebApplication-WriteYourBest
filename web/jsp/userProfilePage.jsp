<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write your best | Main Page</title>
    <meta charset="utf-8">
    <meta lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.common.css" rel="stylesheet">
    <link href="../css/style.userprofile.css" rel="stylesheet">
    <link rel="icon" type="img/ico" href="../images/logo.ico">
</head>
<body id="page">

<div id="topBanner">
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
                    <li><a href="#">Log Out</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-xs-12" >
                <div id="profileInformation">
                    <div>
                        <div class="row">
                            <div class="col-xs-6" align="center">
                                <img alt="User Picture" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive">
                            </div>
                            <div class="col-xs-6">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td>NickName</td>
                                        <td>Anya</td>
                                    </tr>
                                    <tr>
                                    <tr>
                                        <td>Gender</td>
                                        <td>Female</td>
                                    </tr>
                                    <tr>
                                        <td>Date of birth</td>
                                        <td>09.06.1997</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td>mymail@gmail.com</td>
                                    </tr>
                                    <tr>
                                        <td>Raiting</td>
                                        <td>some raiting</td>
                                    </tr>
                                    <tr>
                                        <td>About myself</td>
                                        <td>my description</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <form >
                                <a id="editProfileSubmit" href="#" data-toggle="tooltip" type="submit" class="btn btn-default">Edit profile</a>
                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<div id="space"></div>
<nav class="navbar" id="footer">
    <div class="container-fluid">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="logInPage.html">Logout</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <p class="nav navbar-nav navbar-right">Paramonova Anastasia</p>
            </ul>
        </div>
    </div>
</nav>

<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>