<%-- 
    Document   : logincode
    Created on : 11-apr-2016, 14:46:41
    Author     : soufyan
--%>
<%@page import="java.util.UUID"%>


<form>
     <center>
            <table border="1" width="30%" cellpadding="5">
                 
                 <thead>
                    <tr>
                        <th colspan="2">Hier is uw Logincode
                                                <%
String uuid = UUID.randomUUID().toString();
out.println("= " + uuid);
%>
                        
                        
                        </th>

                    </tr>
                </thead>
            </table>
            </center>
        </form>
</form