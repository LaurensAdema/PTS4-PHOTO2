<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="LanguageConfigServlet" />
<t:Masterpage>

    <jsp:body>
        <div class="container">
<div class="input-group">
    <form role="form" class="form-group" ID="language" action="LanguageConfigServlet" method="post">
        <div class="form-group">

            <label for="tbelement">
                Element name
            </label>
            <input type="text" class="form-control" name="tbelname" placeholder="name of element ex. lbl_username"/><br>
            <input type="text" class="form-control" name="tbelvalue" placeholder="value of element so : Username"/>
            <select name="page">
                <c:forEach items="${pages}" var="page">
                    <option name="<c:out value="${page.value}"/>" value="<c:out value="${page.key}"/>"><c:out value="${page.value}"/></option>
                </c:forEach>
            </select>
            <select name="language">
                <c:forEach items="${RequestScope.languages}" var="language">
                    <option name="<c:out value="${language.id}"/>" value="<c:out value="${language.name}"/>"><c:out value="${language.value}"/></option>
                </c:forEach>
            </select>

            <button type="submit" class="btn btn-default" ID="btnlang" >
                add to db
            </button>
    </form>
</div>
</div>
</div>
    </jsp:body></t:Masterpage>