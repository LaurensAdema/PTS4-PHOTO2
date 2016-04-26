package com.login;

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

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("tbusername");
        String password = request.getParameter("tbpassword");
        Account account = new Account();
        account.setNaam(name);
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

        //RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        //rd.forward(request, response);
        response.sendRedirect(request.getContextPath() + "/index.jsp");


        List<Project> projecten = new ArrayList<>();
        projecten.add(new Project("Een", DateTime.now()));
        projecten.add(new Project("Twee", DateTime.now()));
        projecten.add(new Project("Drie", DateTime.now()));
        projecten.add(new Project("Vier", DateTime.now()));
        request.setAttribute("projectlist", projecten);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

}
