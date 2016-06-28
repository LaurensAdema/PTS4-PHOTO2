<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="LangManagementServlet" />
<t:Masterpage>
    <jsp:body>
        <div class="container">
        <div class="panel panel-primary">
                <div class="panel-heading">Edit Language</div>
                <div class="panel-body">
                    <form role="form" class="form-group" ID="formselectlanguage" action="LangManagementServlet" method="post">   
                        <select id="langselect" name="langselect" class="selectpicker">
                            <c:forEach items="${languages}" var="taal">
                                <c:choose>
                            <c:when test="${sessionScope.langselect==taal.id}">
                                <option selected value="<c:out value="${taal.id}"/>"><c:out value="${taal.name}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="<c:out value="${taal.id}"/>"><c:out value="${taal.name}"/></option>   
                                </c:otherwise>
                                </c:choose>
                            </c:forEach>   
                        </select>
                        <div class="btn-group" role="group">
                            <button type="submit" class="btn btn-sm btn-warning" ID="btnselectlang" onclick="form.action = 'LangManagementServlet?select'">
                                Select this Language
                            </button>
<!--                            <button type="submit" class="btn btn-sm btn-danger" ID="btldeletelang" onclick="form.action = 'LangManagementServlet?delete'">
                                Delete this Language
                            </button>-->

                        </div>
                 </div>
            </div>
            <div class ="panel panel-primary">
                <div class="panel-heading">Element overview</div>
                <table class="table table-striped table-condensed">
                    <thead>
                        <tr>
                            <th>Element Name</th>
                            <th>Description</th>
                            <th>Value</th>
                            <th>Controls</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${elements}" var="item">
                            <tr>
                                <td><c:out value="${item.name}"/></td>
                                <td><c:out value="${item.description}"/></td>
                                <td><input type="text" name="edit22_<c:out value="${item.id}"/>"class="form-control" value="<c:out value="${item.translation}"/>"></td>
                            <td><button type="submit" class="btn btn-xs btn-primary" ID="btnSaveElement" onclick="form.action = 'LangManagementServlet?edit&id=<c:out value="${item.id}"/>'">
                                    Save Changes
                                </button></td>
                            </tr>
                                </c:forEach>
                    </tbody>
                </table>
            </form>
            </div>  
        </div>
        
    </jsp:body>
</t:Masterpage>