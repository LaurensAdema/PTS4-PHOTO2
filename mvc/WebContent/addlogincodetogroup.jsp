

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.foto.Project"%>
<t:Masterpage>

    
<jsp:body>
       <body>
 <form role="form" class="form-group" ID="formbefore" action="AddLoginCodeToGroupServlet" method="post">
     
GroupID:<input type="text" class="form-control" name="tbgroupid" />
<br>
Logincode:<input type="text" class="form-control" name="tblogincode" />
<br>
<button type="submit" class="btn btn-default" ID="btnsubmit" >
                            Voeg logincode toe aan groep
                        </button>
</form>

</body>
            
            
      
    </jsp:body>
</t:Masterpage>