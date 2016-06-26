<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="LangManagementServlet" />
<t:Masterpage>
    <jsp:body>
        <div class="container">
         <div class = "panel panel-primary">
            <div class="panel-heading">Edit Language</div>
            <div class="panel-body">
                <form role="form" class="form-group" ID="formselectlanguage" action="LangManagementServlet" method="post">        
                <select id="langselect" name="langselect">
                    <c:forEach items="${languages}" var="taal">
                        <option value="<c:out value="${taal.id}"/>"><c:out value="${taal.name}"/></option>   
                    </c:forEach>
                </select>
                    <div class="btn-group" role="group">
                        
                        <button type="submit" class="btn btn-sm btn-warning" ID="btnselectlang" onclick="form.action='LangManagementServlet?select'">
                        Select this Language
                        </button>
                        <button type="submit" class="btn btn-sm btn-danger" ID="btldeletelang" action="deletelang=true">
                            Delete this Language
                        </button>
                        
                        </div>
<!--                    <div>
                        <a class="btn btn-warning" href="/WEB-INF/languagemanager.jsp?selectlang=1" action="post">Select this language</a>
                    </div>           -->
                    </form>
            </div>
        </div>    
        </div>
        <div class="jumbotron">
            <c:out value="${test2}"/>
        </div>
       
    </jsp:body>
</t:Masterpage>