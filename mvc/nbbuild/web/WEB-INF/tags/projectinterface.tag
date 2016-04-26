<%-- 
    Document   : projectinterface
    Created on : 5-apr-2016, 15:27:48
    Author     : markb
--%>

<%@tag description="The project interface" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="project" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<div class="container-fluid">
    <div class="row" height="100px">
        <div class="panel panel-default col-md-6">
           <form role="form" class="form form-group" action="LoginServlet" method="post">
<table>
<tr><th>Name</th> <th>Date</th></tr>
<c:forEach var="Project" items="${projectlist}">
       <tr>
        <td>
            <c:out value="${Project.Name}" />
        </td>
        <td>
            <c:out value="${Project.Date}" />
        </td>
    </tr>
</c:forEach>
</table>
<form>
        </div>
        <div class="panel panel-default col-md-6 panelcollapseheight">
            <form role="form" class="form form-group" action="LoginServlet" method="post">
                <div class="form-group ">
                    <label for="tbProjectname" text="Project Name">
                        Project Name
                    </label>
                    <input type="text" class="form-control" id="tbProjectname" placeholder="Enter name here" />
                </div>
                <div class="form-group">
                    <label for="dtpProjectDate">
                        Date: 
                    </label>
                    <div class='input-group ' id='dtpProjectDate'>
                        <input type='date' class="form-control" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="form-group">

                    <label for="tbProjectPhotoNumber">
                        Number of Photo's
                    </label>
                    <input type="datetime" class="form-control" id="tbProjectPhotoNumber" />
                </div>
                <button type="submit" class="btn btn-default">
                    Submit
                </button>
            </form>
        </div>
    </div>
</div>