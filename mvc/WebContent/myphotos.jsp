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
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Total price</th>
                            <th>Date of purchase</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${projects}" var="project">
                        <optgroup label="<c:out value="${project.name}"/>">
                        <c:forEach items="${project.groups}" var="group">
                            <option value="<c:out value="${group.id}"/>"><c:out value="${group.groupName}"/></option>
                        </c:forEach>
                        </optgroup>
                    </c:forEach>
                    </tbody>
                </table>
                </dl>
            </div>

        </div>
    </jsp:body>
</t:Masterpage>