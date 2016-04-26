<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.foto.Project"%>
<t:Masterpage>

    <jsp:body>
        <h3>Add some inlogcode here</h3>
        <form>
            <div class="form-inline">
                <label  for="tblogincode">Inlogcode here</label>
                <input type="text" class="form-control" id="tblogincode" placeholder="Inlogcode">
               
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </jsp:body>

</t:Masterpage>

