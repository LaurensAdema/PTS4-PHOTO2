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
                <form role="form" class="form form-group" action="UploadDownloadFileServlet" method="post" enctype="multipart/form-data">
                    <label for="selectgroup">Select a Group</label>
                    <select name="files" id="selectgroup" class="form-control required">
                        <c:forEach items="${projects}" var="project">
                            <optgroup label="<c:out value="${project.name}"/>">
                                <c:forEach items="${project.groups}" var="group">
                                    <option value="<c:out value="${group.id}"/>"><c:out value="${group.groupName}"/></option>
                                </c:forEach>
                            </optgroup>
                        </c:forEach>
                    </select>
                    </br>
                    <label for="tbfilename">Select File to Upload:</label>
                    <input type="file" name="tbfilename" multiple="" class="btn btn-primary" required>
                    </br>
                    <label for="tbprijs">Add price for all photo's in this group: €  <label>
                            <input type="number" name="tbprijs" step="0.01" min="0.01" max="99" required />
                            <input type="submit" value="Upload" class="btn btn-primary">
                            </form>
                            </div>

                            </div>
                        </jsp:body>
                    </t:Masterpage>
