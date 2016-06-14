<%-- 
    Document   : order
    Created on : 14-jun-2016, 14:45:13
    Author     : Laurens Adema
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="MyPhotosServlet" />

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
                                    <div class="row">

                                        <div class="col-xs-6 col-md-6">
                                            <a class="btn btn-success" href="/WEB-INF/shoppingcart.jsp?del=<c:out value="${item.key.id}"/>">Remove from cart</a>
                                        </div>
                                        <div class="col-xs-6 col-md-6">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <a href="/WEB-INF/shoppingcart.jsp?rem=<c:out value="${item.key.id}"/>"  class="btn btn-danger btn-number"  data-type="minus" data-field="quant[2]">
                                                        <span class="glyphicon glyphicon-minus"></span>
                                                    </a>
                                                </span>
                                                <input type="text" name="quant[2]" class="form-control input-number" value="<c:out value="${item.value}"/>" min="1" max="100">
                                                <span class="input-group-btn">
                                                    <a href="/WEB-INF/shoppingcart.jsp?plus=<c:out value="${item.key.id}"/>"  class="btn btn-success btn-number" data-type="plus" data-field="quant[2]">
                                                        <span class="glyphicon glyphicon-plus"></span>
                                                    </a>
                                                </span>
                                            </div>
                                        </div>
                                        <p></p>
                                    </div>

                                    <br>
                                    </br>

                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="btn btn-success" href="/WEB-INF/order.jsp">Order</a>
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

