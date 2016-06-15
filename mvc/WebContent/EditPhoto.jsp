
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="EditProductServlet" />

<t:Masterpage>

    <jsp:body>
        <h4>Edit Photo</h4>
        <c:out value="${photo.name}"/>
        <form action="EditProductServlet" method="post">
            Color<br>
            <select name="color">
                <option value="Color">Color</option>
                <option value="Greyscale">Greyscale</option>
                <option value="Sepia">Sepia</option>
            </select><br><br>

            What should your photo be printed on?<br>
            <select name="material">
                <option value="Mug">Mug</option>
                <option value="Shirt">Shirt</option>
                <option value="Paper">Paper</option>
                <option value="Phonecase">Phonecase</option>
            </select>
            <input type="submit" value="Save and add to cart">
        </form>
    </jsp:body>

</t:Masterpage>

