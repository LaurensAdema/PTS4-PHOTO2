<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.foto.Project"%>
<t:Masterpage>

    <jsp:body>
        <h1>Account details </h1>
        <form role="form" class="form-group" ID="formbefore" action="RegisterServlet" method="post">
            <div style="color: #FF0000;">${errorMessage}</div>
            <div class="form-inline">
                <label  for="tbFirstName">First Name</label>
                <input type="text" class="form-control" name="tbFirstName" placeholder="First Name">
                <label  for="tbLastName">Last Name</label>
                <input type="text" class="form-control" name="tbLastName" placeholder="Last Name">
            </div></br>
            <div class="form-group">
                <label  for="tbEmail1">Email address</label>
                <input type="email" class="form-control " name="tbEmail" placeholder="Email">
            </div>
            <div class="form-group">
                <label  for="tbPassword">Username</label>
                <input type="text" class="form-control" name="tbUserName" placeholder="Username">
                <label  for="tbPassword">Password</label>
                <input type="password" class="form-control" name="tbPassword" placeholder="Password">
                <label  for="tbPassword2">Password check</label>
                <input type="password" class="form-control" name="tbPassword2" placeholder=" Repeat Password">
            </div>


            <h1>Address Form </h1>
            <div class="form-group">
                <label for="tbPostal">Postal Code</label>
                <input type="text" class="form-control" name="tbPostal" placeholder="Postal code">
            </div>
            <div class="form-group">
                <label for="tbHousenr">Address number</label>
                <input type="text" class="form-control" name="tbHousenr"  placeholder="Number"> 
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
        
    </jsp:body>
  
</t:Masterpage>

