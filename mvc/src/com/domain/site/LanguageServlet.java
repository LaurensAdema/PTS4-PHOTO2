/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.site;

import com.database.Database;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author NesciO
 */
public class LanguageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String language = "Nederlands";
        if (request.getParameter("lang") != null)
        {
            ResultSet allLanguages = Database.getDatabase().query("SELECT name FROM language WHERE name = " + request.getParameter("lang"), Database.QUERY.QUERY);
            try
            {
                if (allLanguages != null)
                {
                    while (allLanguages.next())
                    {
                        if (allLanguages.getString("name").equals(request.getParameter("lang")))
                        {
                            request.getSession().setAttribute("lang", request.getParameter("lang"));
                            break;
                        }
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

        if(request.getSession().getAttribute("lang") != null)
        {
            language = request.getSession().getAttribute("lang").toString();
        }
        

        ResultSet rs = Database.getDatabase().query("SELECT element_language.text, element.name FROM element_language, element WHERE ElementID IN "
                + "(SELECT ElementID FROM element WHERE PageID IN "
                + "(SELECT id FROM page WHERE uri = " + request.getServletPath() + " OR uri = masterpage)) AND element_language.LanguageID IN "
                + "(SELECT id FROM language WHERE name = " + language + ")"
                + "AND element.id=element_language.ElementID", Database.QUERY.QUERY);

        try
        {
            if (rs != null)
            {
                while (rs.next())
                {
                    request.setAttribute(rs.getString("name"), rs.getString("text"));
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
