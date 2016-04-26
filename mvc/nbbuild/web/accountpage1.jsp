<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.domain.foto.Project"%>
<t:Masterpage>

    <jsp:body>
        <h1>Account details </h1>
        <form>
            <div class="form-inline">
                <label  for="exampleInputEmail3">First Name</label>
                <input type="text" class="form-control" id="tbFirstname" placeholder="First Name">
                <label  for="exampleInputEmail3">Last Name</label>
                <input type="text" class="form-control" id="tbLastName" placeholder="Last Name">
            </div></br>
            <div class="form-group">
                <label  for="exampleInputEmail3">Email address</label>
                <input type="email" class="form-control " id="exampleInputEmail3" placeholder="Email">
            </div>
            <div class="form-group">
                <label  for="exampleInputEmail3">Email address</label>
                <input type="email" class="form-control" id="exampleInputEmail3" placeholder="Email">
            </div>
            <div class="form-group">
                <label  for="exampleInputPassword3">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
            </div>
            <div class="form-group">
                <label  for="exampleInputPassword3">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
            </div>


            <h1>Address Form </h1>
            <div class="form-group">
                <label  for="exampleInputEmail3">Address</label>
                <input type="text" class="form-control" id="tbFirstname">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">Postal Code</label>
                <input type="text" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">Address number</label>
                <input type="email" class="form-control" id="exampleInputEmail2">  </div>
            <div class="form-group">
                <label  for="exampleInputPassword3">City</label>
                <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </jsp:body>

</t:Masterpage>

