<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<t:Masterpage>

    <jsp:body>
        <h3>Add some inlogcode here</h3>
        <div class="col-md-12">
        <form action="AttachLoginCodeServlet" method="post">
            <div class="form-inline">
                <label  for="tblogincode">Inlogcode here</label>
                <input type="text" class="form-control" name="tblogincode" placeholder="Inlogcode">
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
            </div>
    </jsp:body>

</t:Masterpage>

