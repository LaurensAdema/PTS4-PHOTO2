<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<t:Masterpage>

    <jsp:body>
        <div class = "panel panel-primary">
            <div class = "panel-body">
                <div class="well well-sm">
                    <strong>Shopping Cart</strong>
                    <div class="btn-group">
                        <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
                            </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                                class="glyphicon glyphicon-th"></span>Grid</a>
                    </div>
                </div>
                <c:forEach items="${cart}" var="item">
                    <div id="products" class="row list-group">
                        
                        <div class="item  col-xs-4 col-lg-4">
                            <div class="thumbnail">
                                
                                <div class="caption">
                                    <h4 class="group inner list-group-item-heading">
                                        <c:out value="${item.key.name}"/></h4>
                                    <br/>
                                    <div class="col-xs-6 col-md-6">

                                        <c:out value="$${item.key.price}"/></div>
                                        <div class="col-xs-6 col-md-6">

                                        <c:out value="Quantity: ${item.value}"/></div>
                                    <div class="row">

                                        <div class="col-xs-6 col-md-6">
                                            <a class="btn btn-success" href="/WEB-INF/mygroups.jsp?del=<c:out value="${item.key.id}"/>">Remove from cart</a>
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

