package com.showpictures;

import com.database.Database;
import com.domain.account.Account;
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

        
        
        ResultSet rs = Database.getDatabase().query("SELECT * FROM photo WHERE groupID = 1", Database.QUERY.QUERY);

        try {
            while (rs.next()) {
                String Pathlowres = rs.getString("pathlowres");
                out.write("<img src=\"UploadDownloadFileServlet?fileName="+Pathlowres+"\">");
                out.write("<br>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowPicturesServletLowRes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
