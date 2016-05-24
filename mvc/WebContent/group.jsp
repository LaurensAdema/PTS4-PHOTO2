<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:Masterpage>

    <jsp:body>
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
                    <div id="products" class="row list-group">
                        <div class="item  col-xs-4 col-lg-4">
                            <div class="thumbnail">
                                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
                                <div class="caption">
                                    <h4 class="group inner list-group-item-heading">
                                        <c:out value="${item.name}"/></h4>
                                    <div class="col-xs-6 col-md-6">
                                        <a class="btn btn-sm" href="#">Edit info</a>
                                    </div>
                                    <div class="col-xs-6 col-md-6">
                                        <a class="btn btn-sm" href="#">Delete this picture</a>
                                    </div>
                                    <br/>
                                    <p class="group inner list-group-item-text">
                                        Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                                        sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                                    <div class="row">
                                        <div class="col-xs-12 col-md-6">
                                            <p class="lead">
                                                $21.00</p>
                                        </div>
                                        <div class="col-xs-12 col-md-6">
                                            <a class="btn btn-success" href="#">Add to cart</a>
                                        </div>
                                        <br>
                                        </br>

                                    </div>
                                </div>
                            </div>
                        </div>
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

