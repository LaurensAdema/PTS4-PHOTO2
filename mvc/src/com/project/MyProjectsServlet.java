package com.project;

import com.database.Database;
import com.domain.account.Account;
import com.domain.photo.Group;
import com.domain.photo.Project;
import com.domain.site.LanguageServlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyProjectsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        List<Project> projects = new ArrayList<>();

        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null)
        {
            ResultSet results = Database.getDatabase().query("SELECT * FROM project_account WHERE accountID = " + account.getID() , Database.QUERY.QUERY);
            try
            {
                if (results != null)
                {
                    while (results.next())
                    {
                        ResultSet results2 = Database.getDatabase().query("SELECT * FROM project WHERE id = " + results.getInt("projectID"), Database.QUERY.QUERY);
                        if (results2 != null)
                        {
                            while (results2.next())
                            {
                                Project p = new Project(results2.getString("name"));
                                ResultSet results3 = Database.getDatabase().query("SELECT * FROM pgroup WHERE id IN ( SELECT pgroupID FROM project_pgroup WHERE projectID = " + results.getInt("projectID") + " )", Database.QUERY.QUERY);

                                if (results3 != null)
                                {
                                    while (results3.next())
                                    {
                                        p.getGroups().add(new Group(0, results3.getString("logincode"), results3.getString("groupname")));
                                    }
                                    projects.add(p);
                                }

                            }
                        }
                    }
                }
            } catch (SQLException ex)
            {
                Logger.getLogger(MyProjectsServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                Database.getDatabase().closeConnection();
            }
            request.getSession().setAttribute("projects", projects);
        }
    }
}
}
