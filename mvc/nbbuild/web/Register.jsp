<%-- 
    Document   : Register
    Created on : 11-apr-2016, 14:24:51
    Author     : soufyan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <h1>Aanmeldpagina
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <form method="post" action="registration.jsp">
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Voeg Leerling toe</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Naam</td>
                        <td><input type="text" name="accountname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Klas</td>
                        <td><input type="text" name="klasname" value="" /></td>
                    </tr>
                    
                    <tr>
                        <td><input onclick="document.forms[0].action = 'logincode.jsp'; return true;" type="submit" value="Submit" /></td>
                   
                    </tr>
                 
                </tbody>
            </table>
            </center>
        </form>
        <form>
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Voeg Klas toe</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>School</td>
                        <td><input type="text" name="groupname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Plaats</td>
                        <td><input type="text" name="groupplace" value="" /></td>
                    </tr>
                    
                    <tr>
                        <td><input onclick="document.forms[1].action = 'logincode.jsp'; return true;" type="submit" value="Submit" /></td>
                   
                    </tr>
                 
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>
