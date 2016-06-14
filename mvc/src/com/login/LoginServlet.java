package com.login;

import com.database.Database;
import com.domain.account.Account;
import com.domain.account.Photographer;
import com.domain.account.Customer;
import com.domain.account.Admin;
import com.domain.photo.Photo;
import com.domain.photo.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

public class LoginServlet extends HttpServlet {

    //LOGIN
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("tbusername");
        String password = request.getParameter("tbpassword");
        Account account = login(name, password);
        if (account != null)
        {
            String lang = null;
            if (request.getSession(true).getAttribute("lang") != null)
            {
                lang = request.getSession(true).getAttribute("lang").toString();
            }
            request.getSession().invalidate();

            HttpSession newSession = request.getSession(true);
            if (lang != null)
            {
                newSession.setAttribute("lang", lang);
            }
            newSession.setAttribute("cart", new HashMap<Photo, Integer>());
            newSession.setAttribute("account", account);

        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    //LOGOUT
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String lang = null;
        if (request.getSession(true).getAttribute("lang") != null)
        {
            lang = request.getSession(true).getAttribute("lang").toString();
        }
        request.getSession().invalidate();

        HttpSession newSession = request.getSession(true);
        if (lang != null)
        {
            newSession.setAttribute("lang", lang);
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    static public Account login(String username, String password)
    {
        Account account = null;
        try
        {
            ResultSet rs = Database.getDatabase().query("SELECT * FROM account WHERE username = " + username, Database.QUERY.QUERY);

            while (rs.next())
            {
                String foundpassword = rs.getString("password");

                if (password.equals(foundpassword))
                {
                    char type = rs.getString("accountType").charAt(0);
                    int id = rs.getInt("id");
                    String usernameDb = rs.getString("username");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String postal_code = rs.getString("postal_code");
                    String nr = rs.getString("nr");
                    String email = rs.getString("email");
                    switch (type)
                    {
                        case 'p':
                            account = new Photographer(id, usernameDb, first_name, last_name, postal_code, nr, email);
                            break;
                        case 'c':
                            account = new Customer(id, usernameDb, first_name, last_name, postal_code, nr, email);
                            break;
                        case 'a':
                            account = new Admin(id, usernameDb, first_name, last_name, postal_code, nr, email);
                            break;
                    }
                }
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Database.getDatabase().closeConnection();
        }
        return account;
    }
}
