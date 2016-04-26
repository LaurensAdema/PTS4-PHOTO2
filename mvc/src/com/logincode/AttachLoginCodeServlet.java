/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logincode;

import com.database.Database;
import com.domain.account.Account;
import com.domain.foto.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 *
 * @author Laurens Adema
 */
public class AttachLoginCodeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String logincode = request.getParameter("tblogincode");
        Account account = (Account) request.getSession(true).getAttribute("account");
        if (account != null && logincode != null)
        {
            int foundID = -1;
            try
            {
                ResultSet rs = Database.getDatabase().query("SELECT id FROM pgroup WHERE logincode = " + logincode, Database.QUERY.QUERY);
                
                while (rs.next())
                {
                    foundID = rs.getInt("id");
                    break;
                }
            } catch (SQLException ex)
            {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                Database.getDatabase().closeConnection();
            }
            if(foundID != -1)
            {
                Database.getDatabase().query("INSERT INTO account_group (accountID,groupID) VALUES (" + account.getID() + " , " + foundID + ")", Database.QUERY.UPDATE);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("accountpage2.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    }

}
