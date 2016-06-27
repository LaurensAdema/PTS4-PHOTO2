package com.domain.site;

import com.database.Database;
import com.domain.account.Account;
import com.domain.account.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class LanguageConfigServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("tbelname");
        String value = request.getParameter("tbelvalue");
        String page = request.getParameter("page");
        String language = request.getParameter("language");

        insertValues(id, value, page, language);
    }

    private void insertValues(String id, String value, String page, String language)
    {
        ResultSet rs = Database.getDatabase().query("INSERT INTO element (name,PageID) VALUES (" + id + " , " + page + ")", Database.QUERY.UPDATE);
        int key = -1;

        try
        {
            if (rs != null)
            {
                while (rs.next())
                {
                    key = rs.getInt(1);
                }
            }
            if (key != -1)
            {
                Database.getDatabase().query("INSERT INTO element_language (LanguageID,ElementID,text) VALUES (" + language + " , " + key + " , " + value + ")", Database.QUERY.UPDATE);

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Database.getDatabase().closeConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HashMap<Integer, String> pages = new HashMap<>();
        HashMap<Integer, String> languages = new HashMap<>();
        if (request.getSession().getAttribute("account") != null)
        {
            Account account = (Account) request.getSession().getAttribute("account");
            if (account.getClass() == Admin.class)
            {
                try
                {
                    ResultSet pageResults = Database.getDatabase().query("SELECT * FROM page", Database.QUERY.QUERY);

                    if (pageResults != null)
                    {
                        while (pageResults.next())
                        {
                            pages.put(pageResults.getInt("id"), pageResults.getString("uri"));
                        }
                    }

                    ResultSet langResults = Database.getDatabase().query("SELECT * FROM `language` WHERE id in (select distinct languageID from element_language)", Database.QUERY.QUERY);

                    if (langResults != null)
                    {
                        while (langResults.next())
                        {
                            languages.put(langResults.getInt("id"), langResults.getString("name"));
                        }
                    }
                } catch (SQLException ex)
                {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    Database.getDatabase().closeConnection();
                }
            } else
            {
                response.sendRedirect(request.getContextPath() + "/index.jsp");

            }
        } else
            {
                response.sendRedirect(request.getContextPath() + "/index.jsp");

            }
        request.getSession().setAttribute("pages", pages);
        request.getSession().setAttribute("languages", languages);
    }

}
