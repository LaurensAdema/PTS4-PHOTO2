<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="AddLanguageServlet" />
<t:Masterpage>
    <jsp:body>
        <div class="container">
            <div class ="panel panel-primary">
                <div class="panel-heading">Add a new Language</div>
                <div class="panel-body">
                    <div class="col-md-12"><p>
                        Here you can add your new language. Simply choose your country, and optionally give the Language a Display Name for the language selector in the navigation
                        bar.(otherwise the Country itself will be used as name)Click on "add language to website" and poof! Your Language is now added.
                        <strong>Important: </strong>The newly added language will have standard english translations.
                        To edit translations of already added languages,<a href="/WEB-INF/languagemanager.jsp">Click here</a>.
      
                        
                        </p></div>
                    <form role="form" class="form-group" ID="formselectlanguage" action="AddLanguageServlet" method="post"> 
                        <select id="Countryselect" name="Countryselect" class="selectpicker">
                          <c:forEach items="${newlanguages}" var="taal">  
                            <option value="<c:out value="${taal.id}"/>"><c:out value="${taal.country}"/></option>  
                          </c:forEach>
                        </select>   
                        
                     </form>
            </div>  
        </div>
        </div>
    </jsp:body>
</t:Masterpage>