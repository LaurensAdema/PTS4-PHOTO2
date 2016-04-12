<jsp:include page="LanguageServlet" />


<%@tag description="Photo 2" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@attribute name="header" fragment="true" %>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="js/bootstrap.js" type="text/javascript"></script>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-9">
            <h1 class="text-center h1">
                PhotoShop 
            </h1>
        </div>
        <div class="col-md-3">
            <c:choose>
                <c:when test = "${ empty account}">
                    <form role="form" class="form-group" ID="formbefore" action="LoginServlet" method="post">
                        <div class="form-group">

                            <label for="tbusername">
                                <c:out value="${lbl_username}"/> 
                            </label>
                            <input type="text" class="form-control" name="tbusername" />
                        </div>
                        <div class="form-group">

                            <label for="tbpassword">
                                <c:out value="${lbl_password}"/> 
                            </label>
                            <input type="password" class="form-control" name="tbpassword" />
                        </div>

                        <div class="checkbox">

                            <label>
                                <input type="checkbox" />  <c:out value="${lbl_rememberusername}"/> 
                            </label>
                        </div> 
                        <button type="submit" class="btn btn-default" ID="btnsubmit" >
                            Log In
                        </button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="form-group profile-usertitle " ID="formafter">
                        <div class="profile-usertitle-name">
                            <c:out value="${account.naam}"/>
                        </div>
                        <div class="profile-usertitle-job">
                            Developer
                        </div>
                        <button type="submit" class="btn btn-default" ID="btnlogout" onclick="window.location.href = '/WEB-INF'" >
                            Log Out
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <nav class="navbar navbar-default ">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse">

                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand active" href="#">Home</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="../../index.jsp">Mijn Producten</a></li>
                    <li><a href="/WEB-INF/login-error.jsp">Admin</a></li>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <div class="navbar-nav btn-group">
                        <a class="navbar-btn btn dropdown-toggle btn-select" data-toggle="dropdown" href="#">Language <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="?lang=english"><img src="img/blank.gif" class="flag flag-gb" alt="English" /> English</a></li>
                            <li><a href="?lang=dutch"><img src="img/blank.gif" class="flag flag-nl" alt="Nederlands" /> Nederlands</a></li>
                        </ul>
                        <script>$(".dropdown-menu li a").click(function () {
                                var selText = $(this).text();
                                $(this).parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
                            });</script>
                    </div>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="glyphicon glyphicon-shopping-cart"></span> 7 - Items<span class="caret"></span></a>
                        <ul class="dropdown-menu dropdown-cart" role="menu">
                            <li>
                                <span class="item">
                                    <span class="item-left">
                                        <img src="http://lorempixel.com/50/50/" alt="" />
                                        <span class="item-info">
                                            <span>Foto 1</span>
                                            <span>23$</span>
                                        </span>
                                    </span>
                                    <span class="item-right">
                                        <button class="btn btn-xs btn-danger pull-right">x</button>
                                    </span>
                                </span>
                            </li>
                            <li>
                                <span class="item">
                                    <span class="item-left">
                                        <img src="http://lorempixel.com/50/50/" alt="" />
                                        <span class="item-info">
                                            <span>Foto 2</span>
                                            <span>23$</span>
                                        </span>
                                    </span>
                                    <span class="item-right">
                                        <button class="btn btn-xs btn-danger pull-right">x</button>
                                    </span>
                                </span>
                            </li>
                            <li>
                                <span class="item">
                                    <span class="item-left">
                                        <img src="http://lorempixel.com/50/50/" alt="" />
                                        <span class="item-info">
                                            <span>Foto 3</span>
                                            <span>23$</span>
                                        </span>
                                    </span>
                                    <span class="item-right">
                                        <button class="btn btn-xs btn-danger pull-right">x</button>
                                    </span>
                                </span>
                            </li>
                            <li>
                                <span class="item">
                                    <span class="item-left">
                                        <img src="http://lorempixel.com/50/50/" alt="" />
                                        <span class="item-info">
                                            <span>Foto 4</span>
                                            <span>23$</span>
                                        </span>
                                    </span>
                                    <span class="item-right">
                                        <button class="btn btn-xs btn-danger pull-right">x</button>
                                    </span>
                                </span>
                            </li>
                            <li class="divider"></li>
                            <li><a class="text-center" href="">View Cart</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>
</head>
<html>
    <jsp:invoke fragment="header"/>
    
    <div id="body" >
        <jsp:doBody/>
    </body>
</html>