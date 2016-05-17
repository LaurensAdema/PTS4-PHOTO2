

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.foto.Project"%>
<t:Masterpage>

    
<jsp:body>
       <body>
 <form role="form" class="form-group" ID="formbefore" action="AddGroupServlet" method="post">
     

Groepnaam:<input type="text" class="form-control" name="tbgroupname" />
<br>
Inlogcode: <input type="text" class="form-control" name="tbinlogcode" />
<br>
<button type="submit" class="btn btn-default" ID="btnsubmit" >
                            Maak nieuwe group aan
                        </button>
</form>

</body>
            
            
      
    </jsp:body>
</t:Masterpage>