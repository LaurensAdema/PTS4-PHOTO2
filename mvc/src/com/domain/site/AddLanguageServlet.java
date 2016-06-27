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
import javax.servlet.http.HttpSession;

public class AddLanguageServlet extends HttpServlet {

    public Language newlanguage = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        newlanguage = null;
        if (request.getParameter("Countryselect") != null) {
            String selectlang = request.getParameter("Countryselect");
            int selectid = Integer.valueOf(selectlang);
            int langid = -1;
            HttpSession s = request.getSession();

            List<Language> languages = new ArrayList<>();
            ResultSet langResults = Database.getDatabase().query("SELECT * FROM language WHERE id in (select distinct languageID from element_language)", Database.QUERY.QUERY);
            try {
                if (langResults != null) {
                    while (langResults.next()) {
                        languages.add(new Language(
                                langResults.getInt("id"),
                                langResults.getString("name"),
                                langResults.getString("iso"),
                                langResults.getString("Country")
                        ));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
            for (Language L : languages) {
                if (L.getId() == selectid) {
                    langid = selectid;

                }
            }

            if (langid == -1) {
                ResultSet rs = Database.getDatabase().query("SELECT * FROM language WHERE id = " + selectid, Database.QUERY.QUERY);
                try {
                    if (rs != null)
                    while (rs.next()) {{
                        newlanguage = new Language(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("iso"),
                                rs.getString("Country"));
                    }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddLanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Database.getDatabase().closeConnection();

                }
                List<Element> elements = new ArrayList<>();
                ResultSet Wholelanguage = Database.getDatabase().query("SELECT element.id,element_language.text,element.name,element.description FROM element_language, element "
                        + "WHERE element_language.LanguageID = " + 83 + " AND element.id=element_language.ElementID", Database.QUERY.QUERY);
                try {
                    if (Wholelanguage != null) {
                        while (Wholelanguage.next()) {
                            elements.add(new Element(
                                    String.valueOf(Wholelanguage.getInt("element.id")),
                                    Wholelanguage.getString("element.name"),
                                    Wholelanguage.getString("element.description"),
                                    Wholelanguage.getString("element_language.text")
                            ));
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Database.getDatabase().closeConnection();
                }
                for (Element E : elements) {

                    Insertelement(E);

                }
                //Update language name
                if(request.getParameter("editLanguageName")!=null)
                {
                ResultSet rs4 = Database.getDatabase().RawQuery("UPDATE language SET name = '" + request.getParameter("editLanguageName") + "' WHERE id = " + selectid);
                
                try {
                    if (rs4 != null) {
                        while (rs4.next()) {

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                            {
                                Database.getDatabase().closeConnection();
                            } 
                }
                //Update wanneer geen optionele Naam
                if(request.getParameter("editLanguageName")==null)
                {
                ResultSet rs5 = Database.getDatabase().RawQuery("UPDATE language SET name = '" + newlanguage.getCountry() + "' WHERE id = " + selectid);
                
                try {
                    if (rs5 != null) {
                        while (rs5.next()) {

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally
                            {
                                Database.getDatabase().closeConnection();
                            } 
                }
                
        }

        if (newlanguage == null) {
            String langerror = "Country is already added";
            request.setAttribute("langerror", langerror);
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Language> AllLanguages = new ArrayList<>();
        try {
            ResultSet rs = Database.getDatabase().query("SELECT * FROM language WHERE id not in (select distinct languageid from element_language)", Database.QUERY.QUERY);
            //ResultSet rs = Database.getDatabase().RawQuery("SELECT * FROM language WHERE id not in (select distinct languageid from element_language)");
            if (rs != null) {
                while (rs.next()) {
                    AllLanguages.add(new Language(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("iso"),
                            rs.getString("Country")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }
        request.setAttribute("newlanguages", AllLanguages);

    }

    public void Insertelement(Element element) {
        ResultSet rs = Database.getDatabase().query("INSERT INTO element_language (LanguageID,ElementID,text) VALUES (" + newlanguage.getId() + " , " + element.getId() + " , " + element.getTranslation() + ")", Database.QUERY.UPDATE);
        int key = -1;

        try {
            if (rs != null) {
                while (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            if (key != -1) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }

    }
}
