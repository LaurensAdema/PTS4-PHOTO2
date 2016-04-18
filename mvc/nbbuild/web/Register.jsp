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
          <form action="LogincodeServlet" method="post"  >
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
                        <td><input type="text" name="tbaccountname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Adres</td>
                        <td><input type="text" name="tbadres" value="" /></td>
                    </tr>
                    
                    <tr>
                        <td><input  type="submit" value="register" /></td>
                   
                    </tr>
                     <tr>
                        <td><input  type="submit" value="logincode" onclick="document.forms[0].action = 'logincode.jsp'; return true;" /></td>
                   
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
                        <td><input  type="submit" value="Submit" /></td>
                   
                    </tr>
                 
                </tbody>
            </table>
            </center>
        </form>
             <form action="PhotoGroup" method="post"  >
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="2">Voeg foto toe aan een group</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>foto</td>
                        <td><input type="text" name="tbaccountname" value="" /></td>
                    </tr>
                    <tr>
                      
                    <tr>
                        <td><input  type="submit" value="register" /></td>
                   
                    </tr>
                 
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>
