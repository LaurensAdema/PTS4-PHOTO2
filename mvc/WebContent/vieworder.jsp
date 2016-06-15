<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="MyOrdersServlet" />

<t:Masterpage>

    <jsp:body>
        <div class = "panel panel-primary">
            <div class = "panel-body">
                <div class="well well-sm">
                    <strong>Order #<c:out value="${order.id}"/> on <c:out value="${order.date}"/></strong>

                </div>
                <c:forEach items="${order.products}" var="item">
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

                                    <c:out value="Printed in ${item.key.color}"/></div>
                                <br>
                                <div class="col-xs-6 col-md-6">

                                    <c:out value="Printed on ${item.key.material}"/></div>
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
                                <h4>
                                    <c:out value="Total: $${order.price}"/></h4>
                            </div>
                        </div>
                    </div>


                    <div class="item  col-xs-4 col-lg-4">
                        <div class="thumbnail">

                            <div class="caption">
                                <h4>Delivered to</h4><br>
                                <c:out value="Name: ${account.first_name}"/> <c:out value="${account.last_name}"/><br>
                                <c:out value="E-mail address: ${account.email}"/><br>
                                <c:out value="Postal code: ${account.postal_code}"/><br>
                                <c:out value="Home number: ${account.nr}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script>
                $(document).ready(function () {
                    event.preventDefault();
                    $('#products .item').addClass('list-group-item');
                });</script>
        </div>
    </jsp:body>

</t:Masterpage>

