   <%@page contentType="text/html" pageEncoding="UTF-8"%>
   <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
   <t:Masterpage>
        
       <jsp:body>
           <c:out>${database_test}</c:out> 
           <t:projectinterface></t:projectinterface>
       </jsp:body>
       
   </t:Masterpage>