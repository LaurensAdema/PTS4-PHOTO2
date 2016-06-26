<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="OrderServlet" />

<t:Masterpage>

    <jsp:body>
        <div class='container'>
        <table class="table col-lg-offset-1">
                <thead>
                    <tr>
                        <th>Name of project</th>    
                        <th>Date of Project</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${projects}" var="item">
                        <tr>
                        <td><c:out value="${item.name}"/></td>
                        <td><c:out value="${item.date}"/></td>
                        <td><a href="editlink"/><c:out value="${item.id}"/></td>
                        <td><a href="deletelink"/><c:out value="${item.id}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>    
        </div>
           
    </jsp:body>

</t:Masterpage>

