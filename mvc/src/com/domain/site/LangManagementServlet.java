package com.domain.site;

import com.database.Database;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Mark
 */
public class LangManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String languageID =request.getParameter("langselect");
    if ("select".equals(request.getQueryString()))
    {
             List<Element> elements = new ArrayList<>();
        
     ResultSet Wholelanguage = Database.getDatabase().query("SELECT element.id,element_language.text,element.name,element.description FROM element_language, element "+
             "WHERE element_language.LanguageID = "+languageID+" AND element.id=element_language.ElementID", Database.QUERY.QUERY);   
try{
     if (Wholelanguage != null)
                    {
                        while (Wholelanguage.next())
                        {
                            elements.add(new Element(
                            Wholelanguage.getInt("element.id"),
                            Wholelanguage.getString("element.name"),
                            Wholelanguage.getString("element.description"),
                            Wholelanguage.getString("element_language.text")
                            ));
                            
                        }
                    }
                }
        catch (SQLException ex)
                {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                {
                    Database.getDatabase().closeConnection();
                }
        request.getSession().setAttribute("elements", elements);
    }
    
    response.sendRedirect(request.getContextPath() + "/languagemanager.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
