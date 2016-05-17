   <%@page contentType="text/html" pageEncoding="UTF-8"%>
   <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
   <%@ page import="java.util.List"%>
      <%@ page import="com.domain.foto.Project"%>
   <t:Masterpage>
        
       <jsp:body>
<!--           <c:out>${database_test}</c:out> -->
           <t:projectinterface></t:projectinterface>
           <t:Upload></t:Upload>
       </jsp:body>
       
   </t:Masterpage>
           