package com.domain.bestelling;

import com.database.Database;
import com.domain.account.Account;
import com.domain.account.Photographer;
import com.domain.account.Customer;
import com.domain.account.Admin;
import com.domain.bestelling.Product;
import com.domain.photo.Photo;
import com.domain.photo.Project;
import com.domain.bestelling.ShoppingCart;
import com.domain.photo.Group;
import com.domain.site.LanguageServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String color = request.getParameter("selectcolor");
        String material = request.getParameter("material");

        Photo photoEdit = (Photo) request.getSession().getAttribute("editphoto");

        try {
            ResultSet rs = Database.getDatabase().query("INSERT INTO product (photoID,name,price,material,color) VALUES (" + photoEdit.getId() + " , " + photoEdit.getName() + " , " + photoEdit.getPrice() + " , " + material + " , " + color + ")", Database.QUERY.UPDATE);
            int key = -1;

            if (rs != null) {
                while (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            if (key != -1) {
                response.sendRedirect(request.getContextPath() + "/shoppingcart.jsp?add=" + key);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Photo photo = null;
        if (request.getParameter("id") != null) {
            try {
                ResultSet results;
                results = Database.getDatabase().query("SELECT * FROM photo WHERE id = " + request.getParameter("id"), Database.QUERY.QUERY);

                if (results != null) {
                    while (results.next()) {
                        photo = new Photo(results.getString("name"), results.getInt("price"), results.getInt("id"), results.getString("pathlowres"), results.getString("pathhighres"));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
            request.getSession().setAttribute("editphoto", photo);

        }
        
    }
}
