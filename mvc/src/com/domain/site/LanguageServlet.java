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
import java.util.Map;
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
            throws ServletException, IOException {
        //request.setAttribute("database_test", "temp");

        String page = request.getServletPath();

        //temp
        String language = "english";

        ResultSet rs = Database.getDatabase().query("SELECT element_language.text, element.name FROM element_language, element WHERE ElementID IN "
                + "(SELECT ElementID FROM element WHERE PageID IN "
                + "(SELECT id FROM page WHERE uri = " + page + " OR uri = masterpage)) AND element_language.LanguageID IN "
                + "(SELECT id FROM language WHERE name = " + language + ")"
                + "AND element.id=element_language.ElementID", Database.QUERY.QUERY);

        try {
            if (rs != null) {
                while (rs.next()) {
                    request.setAttribute(rs.getString("name"), rs.getString("text"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }
    }

}
