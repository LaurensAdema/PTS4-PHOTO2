<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<t:Masterpage>

    
<jsp:body>
        <h1>Fotos </h1>
        
        <jsp:include page="ShowPicturesServletLowRes"/>
            
            
      
    </jsp:body>
</t:Masterpage>