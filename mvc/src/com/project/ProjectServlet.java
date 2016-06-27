/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;

import com.account.AddGroupServlet;
import com.database.Database;
import com.domain.account.Account;
import com.domain.account.Admin;
import com.domain.account.Photographer;
import com.domain.photo.Group;
import com.domain.photo.Project;
import com.domain.site.LanguageServlet;
import com.randomcodegenerator.CodeGenerator;
import java.io.IOException;
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

/**
 *
 * @author Laurens Adema
 */
public class ProjectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");

        String projectname = request.getParameter("projectName");

        if (!projectname.equals(""))
        {
            Database.getDatabase().query("INSERT INTO project (name) VALUES ( " + projectname + " ) ", Database.QUERY.UPDATE);
        }
        response.sendRedirect(request.getContextPath() + "/fotograafpanel.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Project> projects = new ArrayList<>();
        if (request.getSession().getAttribute("account") != null)
        {
            Account account = (Account) request.getSession().getAttribute("account");
            if (account.getClass() == Admin.class || account.getClass() == Photographer.class)
            {
                try
                {
                    ResultSet results = Database.getDatabase().query("SELECT * FROM project", Database.QUERY.QUERY);

                    if (results != null)
                    {
                        while (results.next())
                        {
                            projects.add(new Project(results.getInt("id"), results.getString("name"), results.getTimestamp("date")));
                        }
                    }
                } catch (SQLException ex)
                {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    Database.getDatabase().closeConnection();
                }
            }
        }
        request.getSession().setAttribute("projects", projects);
    }
}
