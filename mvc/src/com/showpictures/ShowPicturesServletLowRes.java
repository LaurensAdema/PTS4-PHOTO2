package com.showpictures;

import com.database.Database;
import com.domain.account.Account;
import com.domain.bestelling.Product;
import com.domain.foto.Project;
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

public class ShowPicturesServletLowRes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
       Account account = (Account)request.getSession().getAttribute("account");
        
       
       ResultSet rs0 = Database.getDatabase().query("SELECT * FROM account_group WHERE accountID = " + account.getID(), Database.QUERY.QUERY);
       try {
            while (rs0.next()) {
                ResultSet rs1 = Database.getDatabase().query("SELECT * FROM pgroup WHERE id = " + rs0.getString("groupID"), Database.QUERY.QUERY);
                 try {
            while (rs1.next()) {
                ResultSet rs2 = Database.getDatabase().query("SELECT * FROM photo WHERE logincode = " + rs1.getString("logincode"),Database.QUERY.QUERY);
        try {
            while (rs2.next()) {
                String Pathlowres = rs2.getString("pathlowres");
                out.write("<img src=\"UploadDownloadFileServlet?fileName="+Pathlowres+"\">");
                out.write("<br>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowPicturesServletLowRes.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowPicturesServletLowRes.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowPicturesServletLowRes.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       

     
        
    }

}
