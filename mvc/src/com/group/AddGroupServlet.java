package com.group;

import com.database.Database;
import com.domain.account.Account;
import com.domain.foto.Project;
import com.showpictures.ShowPicturesServletHighRes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

public class AddGroupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String Groupname = request.getParameter("tbgroupname");
        String Inlogcode = request.getParameter("tbinlogcode");
        
 
        Database.getDatabase().query("INSERT INTO pgroup (logincode,groupname) VALUES ("+Inlogcode+" , "+Groupname+")", Database.QUERY.UPDATE);
        
        
        ResultSet rs = Database.getDatabase().query("SELECT * FROM photogroup ORDER BY id DESC LIMIT 1", Database.QUERY.QUERY);
String id = null;
        try {
            while (rs.next()) {
                id = rs.getString("id");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowPicturesServletHighRes.class.getName()).log(Level.SEVERE, null, ex);
        }

        Account test = (Account)request.getSession().getAttribute("account");
        
        
       out.write("groep "+ Groupname + " Succesvol aangemaakt met groepsID: "+ id);
       out.write("<br>");
       out.write("<br>");
        out.write("<a href=\"/WEB-INF/index.jsp\">Terug naar home</a>");
       out.write("</body></html>");
             
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

}
