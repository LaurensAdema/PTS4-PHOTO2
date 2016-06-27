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

public class AddLanguageServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Language newlanguage=null;
    if (request.getParameter("Countryselect")!=null)
    {
    String selectlang = request.getParameter("Countryselect");
    int selectid = Integer.valueOf(selectlang);
     int langid = -1;
    List<Language> exist = (List<Language>) request.getSession().getAttribute("languages");
    for (Language L : exist)
    {
        if (L.getId()==selectid)
        {
        langid=selectid;
        }
    }
    }
    
    if (newlanguage==null)
    {
        String langerror = "Country is already added";
        request.setAttribute("langerror", langerror);
    }
    else
    {
    
    ResultSet rs = Database.getDatabase().RawQuery("INSERT INTO element_language (LanguageID,ElementID,text) VALUES (" + newlanguage.getId() + " , " + 1 + " , " + "test" + ")");
    try{
        if (rs==null)
    {
        String langerror = "Database test file failed to insert";
        request.setAttribute("langerror", langerror);
    }}
    finally
    {
    Database.getDatabase().closeConnection();
    
    }
    }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    List<Language> AllLanguages = new ArrayList<>();
        try
        {
            ResultSet rs = Database.getDatabase().query("SELECT * FROM language WHERE id not in (select distinct languageid from element_language)",Database.QUERY.QUERY);
            //ResultSet rs = Database.getDatabase().RawQuery("SELECT * FROM language WHERE id not in (select distinct languageid from element_language)");
            if (rs != null)
            {
                while (rs.next())
                {
                    AllLanguages.add(new Language(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("iso"),
                           rs.getString("Country")));
                }
            }
        }
        catch (SQLException ex)
        {
        Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
        Database.getDatabase().closeConnection();
        }
        request.setAttribute("newlanguages",AllLanguages);
        
    }
       
    }

