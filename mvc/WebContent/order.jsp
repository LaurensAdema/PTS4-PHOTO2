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

                </div>
                <c:forEach items="${cart.photos}" var="item">
                    <div id="products" class="row list-group">

                        <div class="item  col-xs-4 col-lg-4">
                            
                                <div class="caption">
                                    
                                    <h4 class="group inner list-group-item-heading">
                                        <c:out value="${item.key.name}"/></h4>
                                    <br/>
                                    <div class="col-xs-6 col-md-6">

                                        <c:out value="Price: $${item.key.price}"/></div>

                                    <br>
                                    <div class="col-xs-6 col-md-6">

                                        <c:out value="Quantity: ${item.value}"/></div>

                                    <br>
                                    <div class="col-xs-6 col-md-6">

                                        <c:out value="Total: $${item.key.price * item.value }"/></div>

                                    <br>
                                    </br>

                            </div>
                                        
                        </div>
                    </div>
                </c:forEach>
                <br>
                <div id="total" class="row list-group">

                    <div class="item  col-xs-4 col-lg-4">
                        <div class="thumbnail">

                            <div class="caption">
                                <h4 class="group inner list-group-item">
                                    <c:out value="Total: $${cart.price}"/></h4>
                            </div>
                        </div>
                    </div>
                </div>
            <a class="btn btn-danger" href="/WEB-INF/shoppingcart.jsp">Back to shopping cart</a> <a class="btn btn-success" href="/WEB-INF/order.jsp">Finalize order</a>
        </div>
        <script>
            $(document).ready(function () {
                    event.preventDefault();
                    $('#products .item').addClass('list-group-item');            });</script>
    </div>
</jsp:body>

</t:Masterpage>

