<jsp:include page="LanguageServlet" />


<%@tag description="Photo 2" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@attribute name="header" fragment="true" %>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="js/bootstrap.js" type="text/javascript"></script> 
    <link href="../WEB-INF/css/dialog.css" rel="stylesheet" type="text/css"/>
    <link href="../WEB-INF/css/grid.css" rel="stylesheet" type="text/css"/>
    <script src="../WEB-INF/js/dialog.js" type="text/javascript"></script>
    <script src="../WEB-INF/js/draggable.js" type="text/javascript"></script>
    <script src="../WEB-INF/js/grid.js" type="text/javascript"></script>
    <script src="../WEB-INF/js/jquery.js" type="text/javascript"></script>
    <script src="../WEB-INF/js/dialog.min.js" type="text/javascript"></script>
    <script src="../WEB-INF/js/draggable.min.js" type="text/javascript"></script>
    <script src="../WEB-INF/js/grid.min.js" type="text/javascript"></script>
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
                            <c:out value="${account.first_name}"/>
                        </div>
                        <div class="profile-usertitle-job">
                            <c:out value="${account.type}"/>
                        </div>
                        <button type='button' class='btn btn-sm' ID='btnshowinfo' onclick="window.location.href = '/WEB-INF/accountpage2.jsp'">Show Account Info</button>
                        <button type="submit" class="btn btn-default" ID="btnlogout" onclick="window.location.href = 'LoginServlet'" >
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
                
            </div>
            <div class="collapse navbar-collapse">
                 <ul class="nav navbar-nav">
                     <c:set var="accounttype" scope="session" value="${'a'}"/>     
                    <c:choose>
                        <c:when test = "${accounttype == 'u'}">
                        <li><a href="/WEB-INF/accountpage2.jsp">Add code to account</a></li>
                        </c:when>    
                        <c:when test = "${accounttype == 'f'}">
                         <li><a href="/WEB-INF/index.jsp">Mijn Producten</a></li>   
                        </c:when>         
                        <c:when test = "${accounttype == 'a'}">                     
                        <li><a href="/WEB-INF/login-error.jsp">Admin</a></li>
                        <li><a href="/WEB-INF/index.jsp">Homepage</a></li> 
                        <li><a href="/WEB-INF/accountpage2.jsp">Add code to account</a></li>
                        </c:when>
                        <c:otherwise>
                        <li><a class="navbar-brand active" <a href="/WEB-INF/Register.jsp">Register</a></li>
                        </c:otherwise>
                    </c:choose>
                    



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