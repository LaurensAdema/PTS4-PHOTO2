<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<jsp:include page="AllOrdersServlet" />

<t:Masterpage>
    <jsp:body>
        <div class="container">
            <div class="row">
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
                                        <th>Order number</th>
                                        <th>Paid</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${orders}" var="item">
                                        <tr style="cursor: pointer;" onclick="window.document.location = '/WEB-INF/vieworder.jsp?all=1&id=' + <c:out value="${item.id}"/>">
                                            <td><c:out value="$${item.price}"/></td>
                                            <td><c:out value="${item.date}"/></td>
                                            <td><c:out value="${item.id}"/></td>
                                            <td><i>Yes</i></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </jsp:body>
</t:Masterpage>
