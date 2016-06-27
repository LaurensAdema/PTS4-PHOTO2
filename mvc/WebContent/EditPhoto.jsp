
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="EditProductServlet" />

<t:Masterpage>

    <jsp:body>
        <div class="container">
        
        <c:out value="${photo.name}"/>
        <div class="panel panel-primary col-md-4 col-md-offset-4">
            <div class="panel panel-heading text-center"><h4>Edit Photo</h4></div>
            <div class="panel panel-body input-group">
        <form action="EditProductServlet" class="form form-group" method="post">
            <label for="selectcolor">Color</label><br>
            <select name="selectcolor" class="selectpicker form-group">
                <option value="Color">Color</option>
                <option value="Greyscale">Greyscale</option>
                <option value="Sepia">Sepia</option>
            </select><br><br>
             <label for="selectcolor">What should your photo be printed on</label>
             
            <br>
            <select name="material"class="selectpicker form-group" >
                <option value="Mug">Mug</option>
                <option value="Shirt">Shirt</option>
                <option value="Paper">Paper</option>
                <option value="Phonecase">Phonecase</option>
            </select>
            <span class="input-group-btn">
            <input type="submit" class="btn btn-lg btn-primary form-group" value="Save and add to cart">
            </span>
        </form>
                </div>
        <//div>
        </div>
    </jsp:body>

</t:Masterpage>

