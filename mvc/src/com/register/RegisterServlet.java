package com.register;

import com.database.Database;
import com.domain.account.Account;
import com.domain.foto.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("tbUsername");
        String email = request.getParameter("tbEmail");
        String address = request.getParameter("tbAddress");
        String housenr = request.getParameter("tbHousenr");
        String password = request.getParameter("tbPassword");
        String password2 = request.getParameter("tbPassword2");
        String lastname = request.getParameter("tbLastname");
        
        if(password.equals(password2) && !username.equals("") && email.contains("@") &&  !address.equals("") && !housenr.equals("") && !password.equals("") && !lastname.equals("")){
            Database.getDatabase().query("INSERT INTO accounts (username,email,address,nr,password,lastname) VALUES (" + username +" , " + email + " , " + address + " , " + housenr + " , " + password + " , " + lastname + ")", Database.QUERY.UPDATE);
        


        Account account = new Account();
        account.setNaam(username);
        if (account.validate(password)) {
            String lang = null;
            if (request.getSession(true).getAttribute("lang") != null) {
                lang = request.getSession(true).getAttribute("lang").toString();
            }
            request.getSession().invalidate();

            HttpSession newSession = request.getSession(true);
            if (lang != null) {
                newSession.setAttribute("lang", lang);
            }
            newSession.setAttribute("account", account);

        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            //something went wrong
            //TODO: for each field, provide error
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

}
