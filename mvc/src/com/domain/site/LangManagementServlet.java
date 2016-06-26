package com.domain.site;

import com.database.Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LangManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        if (request.getQueryString().equals("select"))
        {
             this.GetAllElements(request, response);
            response.sendRedirect(request.getContextPath() + "/languagemanager.jsp");
        }
        else if (request.getParameter("edit")!=null||request.getParameter("id")!=null)
        {
            String tochange = request.getParameter("edit22_" + request.getParameter("id"));
            if (!"".equals(tochange))
                {    
                this.Updateelement(request.getParameter("id"),request.getParameter("edit22_" + request.getParameter("id")),request.getParameter("langselect"));
                request.setAttribute("edited", 1);
                response.sendRedirect(request.getContextPath() + "/languagemanager.jsp");
                List<Element> elements2 = (List<Element>)request.getSession().getAttribute("elements");
                for (Element e : elements2)
                    if (e.getId().equals(request.getParameter("id")))
                    {
                    e.setTranslation(tochange);
                    }
                }
            }
        }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    public void GetAllElements(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    List<Element> elements = new ArrayList<>();   
        ResultSet Wholelanguage = Database.getDatabase().query("SELECT element.id,element_language.text,element.name,element.description FROM element_language, element "+
                 "WHERE element_language.LanguageID = "+request.getParameter("langselect")+" AND element.id=element_language.ElementID", Database.QUERY.QUERY);   
            try{
                if (Wholelanguage != null)
                        {
                            while (Wholelanguage.next())
                            { 
                                elements.add(new Element(
                                String.valueOf(Wholelanguage.getInt("element.id")),
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
                    }
            finally
                    {
                    Database.getDatabase().closeConnection();
                    }
            request.getSession().setAttribute("elements", elements);
            request.getSession().setAttribute("langselect",request.getParameter("langselect"));
          
    }
    
    public void Updateelement(String ID, String changedtxt, String languageID)
    {

        ResultSet One = Database.getDatabase().RawQuery("UPDATE element_language SET text ='"+changedtxt+"' WHERE element_language.LanguageID ="+languageID+" AND element_language.ElementID ="+ ID);
    try{
        
        if(One!=null)
        {
            while (One.next())
            {
            One.getString("element.name");
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
    
    }
    
}
