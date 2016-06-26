<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<jsp:include page="AttachLoginCodeServlet" />

<t:Masterpage>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Account Info
                            </h3>
                        </div>
                        <div class="panel-body">
                            <dl>
                                <dt>
                                    First Name:
                                </dt>
                                <dd>
                                    ${account.first_name}
                                </dd>
                                <dt>
                                    Last Name: 
                                </dt>
                                <dd>
                                    ${account.last_name}
                                </dd>
                                <dt>
                                    Email: 
                                </dt>
                                <dd>
                                    ${account.email}
                                </dd>
                                <dt>
                                    Postcode: 
                                </dt>
                                <dd>
                                    ${account.postal_code}
                                </dd>
                            </dl>
                        </div>

                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Recent orders
                            </h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Total price</th>
                                        <th>Date of purchase</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${orders}" var="item">
                                        <tr style="cursor: pointer;" onclick="window.document.location = '/WEB-INF/vieworder.jsp?id=' + <c:out value="${item.id}"/>">
                                            <td><c:out value="$${item.price}"/></td>
                                            <td><c:out value="${item.date}"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <h3>Add some inlogcode here</h3>
            <div class="row">
                <table class="table col-lg-offset-1">
                    <thead>
                        <tr>
                            <th>Name of project</th>    
                            <th>Inlogcodes already activated</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${mygroupies}" var="item">
                            <tr><td><c:out value="${item.groupName}"/></td><td><c:out value="${item.loginCode}"/></td></tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty param.err}">
                <div class="label-danger" >
                    <label for="tblogincode"> Inlogcode already added.                       
                    </label>
                </div>
            </c:if>
            <form action="AttachLoginCodeServlet" method="post">
                <div class="form-inline">
                    <label  for="tblogincode">Inlogcode here</label>
                    <input type="text" class="form-control" name="tblogincode" placeholder="Inlogcode">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </form>
        </div>
    </jsp:body>
</t:Masterpage>
