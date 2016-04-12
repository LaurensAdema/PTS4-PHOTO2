/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logincode;

import com.database.Database;
import com.domain.site.LanguageServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author soufyan
 */
public class LogincodeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Test</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Test at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accountnaam = request.getParameter("tbaccountname");
        String adres = request.getParameter("tbadres");
      insertValues(accountnaam,adres);
        
           }

      private void insertValues(String accountnaam, String adres) {
        ResultSet rs = Database.getDatabase().query("INSERT INTO ACCOUNT (accounttype,username,password,last_name,adres) VALUES ( K" + " , " +  accountnaam +" , " + "password" + " , " + "laurens" + " , " + adres + ")", Database.QUERY.UPDATE);
        int key = -1;

        try {
            if (rs != null) {
                while (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            if (key != -1) {
              Database.getDatabase().query("INSERT INTO account_group (accountID,groupID) VALUES (" + key + " , " + "1 )", Database.QUERY.UPDATE);
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }
    }

    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
