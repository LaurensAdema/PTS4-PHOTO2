


   <%@page contentType="text/html" pageEncoding="UTF-8"%>
   <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
   <t:Masterpage>
        
       <jsp:body>
          <form action="ControllerServlet" method="post">
Name:<input type="text" name="name"><br>
Password:<input type="password" name="password"><br>
<input type="submit" value="login">
</form>
       </jsp:body>
   </t:Masterpage>