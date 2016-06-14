
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<t:Masterpage>

    <jsp:body>
          <h1>Edit Your Photo's </h1>
     <form>
         <h2>Change Color </h2>
  <select name="item">
    <option value="Color">Color</option>
    <option value="Greyscale">Greyscale</option>
    <option value="Sepia">Sepia</option>
  </select>
           <form>
         <h2>Print your photo on products </h2>
  <select name="item">
    <option value="Color">Mug</option>
    <option value="Greyscale">Phonecase</option>
    <option value="Sepia">Desk Decore</option>
  </select>
  <input type="submit" value="Submit">
</form>
    </jsp:body>

</t:Masterpage>

