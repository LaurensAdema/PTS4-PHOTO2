package com.login;

import com.domain.account.Account;
import com.domain.photo.Project;
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

public class LoginServlet extends HttpServlet {

    //LOGIN
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("tbusername");
        String password = request.getParameter("tbpassword");
        Account account = new Account();
        account.setNaam(name);
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

}
