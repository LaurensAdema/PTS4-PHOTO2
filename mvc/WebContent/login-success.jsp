<%@page import="com.domain.account.Account"%>

<p>You are successfully logged in!</p>
<%
Account account=(Account)request.getAttribute("account");
out.print("Welcome, "+account.getNaam());
%>
