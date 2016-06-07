package com.account;

import com.database.Database;
import com.domain.account.Account;
import com.domain.photo.Group;
import com.domain.photo.Photo;
import com.domain.site.LanguageServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyPhotosServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") != null) {
            List<Photo> shoppingcart = (List<Photo>) request.getSession().getAttribute("cart");
            if (request.getParameter("add") != null || request.getParameter("del") != null) {

                Photo item = null;
                ResultSet results;

                try {
                    if (request.getParameter("add") != null) {
                        results = Database.getDatabase().query("SELECT * FROM photo WHERE id = " + request.getParameter("add"), Database.QUERY.QUERY);
                    }
                    results = Database.getDatabase().query("SELECT * FROM photo WHERE id = " + request.getParameter("add"), Database.QUERY.QUERY);
                    if (request.getParameter("del") != null) {
                        results = Database.getDatabase().query("SELECT * FROM photo WHERE id = " + request.getParameter("del"), Database.QUERY.QUERY);
                    }

                    if (results != null) {
                        while (results.next()) {
                            item = new Photo(results.getString("name"), results.getInt("price"), results.getInt("id"), results.getString("pathlowres"), results.getString("pathhighres"));
                        }
                    }
                    if (request.getParameter("add") != null) {

                        shoppingcart.add(item);
                    } else if (request.getParameter("del") != null) {
                        shoppingcart.remove(item);
                    }
                    request.getSession().setAttribute("cart", shoppingcart);
                } catch (SQLException ex) {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Database.getDatabase().closeConnection();
                }

            }
        }

        List<Group> groups = new ArrayList<>();
        if (request.getSession().getAttribute("account") != null) {
            try {
                Account account = (Account) request.getSession().getAttribute("account");
                ResultSet results = Database.getDatabase().query("SELECT * FROM pgroup WHERE id IN ( SELECT groupID FROM account_group WHERE accountID = " + account.getID() + " )", Database.QUERY.QUERY);

                if (results != null) {
                    while (results.next()) {
                        groups.add(new Group(results.getInt("id"), results.getString("logincode"), results.getString("groupname"), results.getDate("date")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
        }
        request.getSession().setAttribute("groups", groups);
    }
}
