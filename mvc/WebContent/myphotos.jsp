<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="MyProjectsServlet" />

<t:Masterpage>   
    <jsp:body>

        <div class="container">
            <div class="panel-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            My projects and login codes
                        </h3>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Group name</th>
                                    <th>Login code</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${projects}" var="item">
                                    <tr>
                                        <td><b><c:out value="${item.name}"/></b></td><td></td>
                                    </tr>
                                    <c:forEach items="${item.groups}" var="group">
                                        <tr>
                                            <td><i><c:out value="${group.groupName}"/></i></td>
                                            <td><i><c:out value="${group.loginCode}"/></i></td>
                                   </tr>
                                </c:forEach>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>

                </div>

            </div>
        </div>
    </jsp:body>
</t:Masterpage>