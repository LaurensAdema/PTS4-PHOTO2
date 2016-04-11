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
import org.joda.time.DateTime;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("tbusername");
        String password = request.getParameter("tbpassword");
        Account account = new Account();
        account.setNaam(name);
        account.setPassword(password);
        request.setAttribute("account", account);
        List<Project> projecten = new ArrayList<>();
        projecten.add(new Project("Een", DateTime.now()));
        projecten.add(new Project("Twee", DateTime.now()));
        projecten.add(new Project("Drie", DateTime.now()));
        projecten.add(new Project("Vier", DateTime.now()));
        request.setAttribute("projectlist", projecten);
        boolean status = account.validate();
        request.setAttribute("logged", status);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

}
