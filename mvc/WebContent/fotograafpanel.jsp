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
                    File upload
                </h3>
            </div>
            <div class="panel-body">
                <form role="form" class="form form-group" action="UploadServlet" method="post" enctype="multipart/form-data">
                    <label for="selectGroupID">Select a Group</label>
                    <select name="selectGroupID" id="selectGroupID" class="form-control required">
                        <option label=" "></option>
                        <c:forEach items="${projects}" var="project">
                            <optgroup label="<c:out value="${project.name}"/>">
                                <c:forEach items="${project.groups}" var="group">
                                    <option value="<c:out value="${group.id}"/>"><c:out value="${group.groupName}"/></option>
                                </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select>
                    </br>
                    <label for="tbFilename">Select File to Upload:</label>
                    <input type="file" name="tbFilename" multiple="" class="btn btn-primary" required>
                    </br>
                    <label for="tbPrice">Add price for all photo's in this group: €  <label>
                            <input type="number" name="tbPrice" step="0.01" min="0.01" max="99" required />
                            <input type="submit" value="Upload" class="btn btn-primary">
                            </form>
                            </div>

                            </div>
                        </jsp:body>
                    </t:Masterpage>
