<%-- 
    Document   : projectinterface
    Created on : 5-apr-2016, 15:27:48
    Author     : markb
--%>

<%@tag description="The project interface" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="project" fragment="true" %>
<div class="container-fluid">
    <div class="row "height="100px">
        <div class="col-md-6 panel panel-default">
            <div class="panel-heading">
                <div class="btn-group pull-right">
                    <a href="#" class="btn btn-primary btn-sm">New</a>
                    <a href="#" class="btn btn-primary btn-sm">Edit</a>
                    <a href="#" class="btn btn-primary btn-sm">Delete</a>
                </div>
                <h2 class="panel-title">select a current project</h2>
            </div>
            <div class="panel-body">
                <form role="form" class="form form-group" action="LoginServlet" method="post">
                    <table>
                        <tr><th>Name</th><th>Date</th></tr>
                                <c:forEach items="${projectlist}" var="project">
                            <tr>
                                <td>
                                    <c:out value="${project}"/>
                                </td>
                                <td>
                                    <c:out value="${project}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
        </div>
        <div class="col-md-6 panel panel-default">
            <div class="panel-heading">
                <h2 class="panel-title">or create a new one</h2>
            </div>
            <div class="panel-body">
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
                        </br>
                        <button type="submit" class="btn btn-default">
                            Create project
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 panel panel-default" >
            <h1> Imagine A fileupload here </h1>
        </div>
    </div>
</div>