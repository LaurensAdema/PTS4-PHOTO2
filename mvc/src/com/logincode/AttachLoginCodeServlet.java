/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logincode;

import com.database.Database;
import com.domain.account.Account;
import com.domain.bestelling.Bestelling;
import com.domain.photo.Group;
import com.domain.photo.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String logincode = request.getParameter("tblogincode");
        Account account = (Account) request.getSession(true).getAttribute("account");
        if (account != null && logincode != null) {
            try {
                ResultSet results = Database.getDatabase().query("SELECT * FROM pgroup WHERE logincode = " + logincode, Database.QUERY.QUERY);
                while (results.next()) {
                    ResultSet alreadyAdded = Database.getDatabase().query("SELECT * FROM account_group WHERE accountID = " + account.getID() + " AND groupID = " + results.getInt("id"), Database.QUERY.QUERY);
                    if (!alreadyAdded.isBeforeFirst()) {
                        Database.getDatabase().query("INSERT INTO account_group (accountID,groupID) VALUES (" + account.getID() + " , " + results.getInt("id") + ")", Database.QUERY.UPDATE);
                        response.sendRedirect("accountmanagement.jsp");
                    } else {
                        response.sendRedirect("accountmanagement.jsp?err=" + logincode);
                    }
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Account A = (Account) req.getSession().getAttribute("account");
        List<Group> Mygroups = new ArrayList<>();
        List<Integer> groupids = new ArrayList<Integer>();
        Group AddedGroup = new Group();
        try {
            ResultSet rs = Database.getDatabase().query("SELECT * FROM account_group WHERE accountID = " + A.getID(), Database.QUERY.QUERY);

            while (rs.next()) {
                groupids.add(rs.getInt("groupID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }
        for (int I : groupids) {
            try {
                ResultSet rs = Database.getDatabase().query("SELECT * FROM pgroup WHERE id = " + I, Database.QUERY.QUERY);

                while (rs.next()) {
                    AddedGroup = new Group(rs.getInt("id"), rs.getString("logincode"), rs.getString("groupname"));
                    Mygroups.add(AddedGroup);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
        }

        req.getSession().setAttribute("mygroupies", Mygroups);

        Account account = (Account) req.getSession().getAttribute("account");
        List<Bestelling> orders = new ArrayList<>();
        try {
            ResultSet rs = Database.getDatabase().query("SELECT * FROM ordera WHERE accountID = " + account.getID(), Database.QUERY.QUERY);

            while (rs.next()) {
                orders.add(new Bestelling(rs.getInt("price"), rs.getDate("date"), rs.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }

        req.getSession().setAttribute("orders", orders);

    }

}
