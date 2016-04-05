package com.domain.site;

import com.database.Database;
import com.domain.account.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageConfigServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("tbusername");
        String value = request.getParameter("tbpassword");
        String page = request.getParameter("tbpassword");
        String language = request.getParameter("tbpassword");
    }

    private void insertValues(String id, String value, String page, String language) {
        ResultSet rs = Database.getDatabase().query("INSERT INTO element (name, PageID) VALUES (" + id + ", " + page + ")", Database.QUERY.UPDATE);
        int key = -1;

        try {
            if (rs != null) {
                while (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            if (key != -1) {
                Database.getDatabase().query("INSERT INTO element_language (LanguageID, ElementID, text) VALUES (" + language + ", " + key + ", " + value + ")", Database.QUERY.UPDATE);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

}
