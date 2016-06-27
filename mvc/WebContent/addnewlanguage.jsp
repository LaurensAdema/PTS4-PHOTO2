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
                     <form role="form" class="form-horizontal" ID="formselectlanguage" action="AddLanguageServlet" method="post"> 
                         <div><p>
                        Here you can add your new language. Simply choose your country, and optionally give the Language a Display Name for the language selector in the navigation
                        bar.Note that this name isn't changeable afterwards. Without a display name the country itself will be displayed as the name.
                        Click on "add language to website" and poof! Your Language is now added.
                        <strong>Important: </strong>The newly added language will have standard english translations.
                        To edit translations of already added languages,<a href="/WEB-INF/languagemanager.jsp">Click here</a>.
                        </p></div>
                        <label for="Countryselect">Select your country</label>
                        <select id="Countryselect" name="Countryselect" class="selectpicker">
                            
                          <c:forEach items="${newlanguages}" var="taal">  
                            <option value="<c:out value="${taal.id}"/>"><c:out value="${taal.country}"/></option>  
                          </c:forEach>
                        </select>   
                        <label for="editLanguageName">(optional)Give a Display name</label>
                        
                        <td><input type="text" name="editLanguageName" class="form-horizontal" value=""/>
                        <button type="submit" class="btn btn-default" ID="btnNewLang" onclick="window.location.href = 'AddLanguageServlet'" >
                        Add this language to the website!
                        </button>
                    </form><p>
            </div>  
        </div>
        </div>
    </jsp:body>
</t:Masterpage>