<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="OrderServlet" />

<t:Masterpage>

    <jsp:body>
        <div class = "panel panel-primary">
            <div class = "panel-body">
                <h4>Order completed</h4><br><br>
                You will recieve an e-mail shortly containing your order and payment details.<br>
                You can find this order under your recent orders, in your account management.
            </div>
        </div>
    </jsp:body>

</t:Masterpage>

