<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:Masterpage>

    <jsp:body>
        <div class="container">
            <div class = "panel panel-primary">
                <div class = "panel-body">
                    <div class="well well-sm">
                        <strong>Foto's within this project</strong>
                        <div class="btn-group">
                            <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
                                </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                                    class="glyphicon glyphicon-th"></span>Grid</a>
                        </div>
                    </div>
                    <c:forEach items="${groups}" var="group">
                        <c:choose>
                            <c:when test="${param.id eq group.id}">
                                <c:set var="groupSelected" value="${group}" />
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test = "${ empty groupSelected}">
                            <c:redirect url="mygroups.jsp"/>
                        </c:when>
                    </c:choose>
                    <c:forEach items="${groupSelected.photos}" var="item">
                            <ul id="products" class= "row media-list container">						
                                <div class="col-sm-4 well well-sm item">		
                                    <div class="media">
                                        <div class="media-left">
                                            <img class="media-object list-group-item" src="<c:out value="${item.pathlowres}"/>" alt="<c:out value="${item.name}"/>"/>
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <br>
                                            <label name="lblprice" class="text-center">$<c:out value="${item.price}"/></label>
                                            <a class="btn btn-success" href="/WEB-INF/EditPhoto.jsp?id=<c:out value="${item.id}"/>">Add to cart</a>
                                        </div>
                                    </div>
                                </div></ul>
                    </c:forEach>
                </div>
            </div>
            <script>
                $(document).ready(function () {
                    $('#list').click(function (event) {
                        event.preventDefault();
                        $('#products .item').addClass('list-group-item');
                    });
                    $('#grid').click(function (event) {
                        event.preventDefault();
                        $('#products .item').removeClass('list-group-item');
                        $('#products .item').addClass('grid-group-item');
                    });
                });</script>
        </div>

    </jsp:body>

</t:Masterpage>

