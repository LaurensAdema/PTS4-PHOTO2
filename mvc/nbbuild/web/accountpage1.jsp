<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.foto.Project"%>
<t:Masterpage>

    <jsp:body>
        <h1>Account details </h1>
        <form>
            <div class="form-inline">
                <label  for="tbFirstName">First Name</label>
                <input type="text" class="form-control" name="tbFirstName" placeholder="First Name">
                <label  for="tbLastName">Last Name</label>
                <input type="text" class="form-control" name="tbLastName" placeholder="Last Name">
            </div></br>
            <div class="form-group">
                <label  for="tbEmail1">Email address</label>
                <input type="email" class="form-control " name="tbEmail1" placeholder="Email">
            </div>
            <div class="form-group">
                <label  for="tbEmail2">Email address</label>
                <input type="email" class="form-control" name="tbEmail2" placeholder="Repeat Email">
            </div>
            <div class="form-group">
                <label  for="tbPassword">Password</label>
                <input type="password" class="form-control" name="tbPassword" placeholder="Password">
            </div>
            <div class="form-group">
                <label  for="tbPassword2">Password</label>
                <input type="password" class="form-control" name="tbPassword2" placeholder=" Repeat Password">
            </div>


            <h1>Address Form </h1>
            <div class="form-group">
                <label for="tbPostal">Postal Code</label>
                <input type="text" class="form-control" id="tbPostal" >
            </div>
            <div class="form-group">
                <label for="tbHousenr">Address number</label>
                <input type="text" class="form-control" id="tbHousenr">  </div>
            <div class="form-group">
                <label  for="tbCity">City</label>
                <input type="text" class="form-control" id="tbCity" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </jsp:body>

</t:Masterpage>

