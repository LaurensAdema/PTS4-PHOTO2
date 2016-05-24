package com.register;

import com.database.Database;
import com.domain.account.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("tbUserName");
        String firstname = request.getParameter("tbFirstName");
        String lastname = request.getParameter("tbLastName");
        String email = request.getParameter("tbEmail");
        String password = request.getParameter("tbPassword");
        String password2 = request.getParameter("tbPassword2");
        String postal = request.getParameter("tbPostal");
        String housenr = request.getParameter("tbHousenr");

        if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !password2.isEmpty() && !postal.isEmpty() && !housenr.isEmpty())
        {
            if (password.equals(password2))
            {
                //if username not in use
               
                try
            {
                ResultSet rs = Database.getDatabase().query("SELECT * FROM account WHERE username = " + username, Database.QUERY.QUERY);
                
                if (!rs.next())
                {
                  out.println("Succes");
                   Database.getDatabase().query("INSERT INTO account (username,password,first_name,last_name,postal_code,nr,email) VALUES (" + username + " , " + password + " , " + firstname + " , " + lastname + " , " + postal + " , " + housenr + " , " + email + ")", Database.QUERY.UPDATE);
                     Account account = new Account();
            account.setNaam(username);
            if (account.validate(password))
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
                newSession.setAttribute("account", account);

            }

            response.sendRedirect(request.getContextPath() + "/index.jsp");
                }
                else{
                    request.setAttribute("errorMessage", "Username already exists");
                    request.getRequestDispatcher("/Register.jsp").forward(request, response);
                }
                rs.close();
            } 
                catch (SQLException ex)
            {
                System.out.println("Error");
            } 
                finally
            {
                Database.getDatabase().closeConnection();
            }

                
                //if address correct

               
            }

          
        } else
        {
            //something went wrong
            //TODO: for each field, provide error
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
    }

}
