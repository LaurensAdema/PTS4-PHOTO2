<jsp:include page="ProjectServlet" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.photo.Project"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:Masterpage>
    <jsp:body>
        <div class="panel panel-primary col-md-6">
            <div class="panel-heading">
                <h3 class="panel-title">
                  Naar grijs
                </h3>
            </div>
            <div class="panel-body">
                <form role="form" class="form form-group" action="EditPhotoToGrey" method="post" enctype="multipart/form-data">
                 
                    </br>
                   
                    <input type="file" name="tbfilename" multiple="" class="btn btn-primary" required>
                    </br>
               
                            <input type="submit" value="Naar grijs" class="btn btn-primary">
                            </form>
                            </div>

                            </div>
        
        <div class="panel panel-primary col-md-6">
            <div class="panel-heading">
                <h3 class="panel-title">
                  Naar sepia
                </h3>
            </div>
            <div class="panel-body">
                <form role="form" class="form form-group" action="EditPhotoToSepiaServlet" method="post" enctype="multipart/form-data">
                 
                    </br>
                   
                    <input type="file" name="tbfilename" multiple="" class="btn btn-primary" required>
                    </br>
               
                            <input type="submit" value="Naar sepia" class="btn btn-primary">
                            </form>
                            </div>

                            </div>
                        </jsp:body>
                    </t:Masterpage>
