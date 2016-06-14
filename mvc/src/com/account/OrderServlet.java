package com.account;

import com.database.Database;
import com.domain.account.Account;
import com.domain.account.Photographer;
import com.domain.account.Customer;
import com.domain.account.Admin;
import com.domain.bestelling.Product;
import com.domain.photo.Photo;
import com.domain.photo.Project;
import com.domain.bestelling.ShoppingCart;
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

public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");

        ResultSet rs = Database.getDatabase().query("INSERT INTO ordera (accountID,payed,price) VALUES (" + account.getID() + " , true , " + cart.getPrice() + ")", Database.QUERY.UPDATE);

        int key = -1;

        try {
            if (rs != null) {
                while (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            if (key != -1) {

                for (Entry<Product, Integer> e : cart.getProducts().entrySet()) {
                    Database.getDatabase().query("INSERT INTO order_product (orderID,productID,quantity) VALUES (" + key + " , " + e.getKey().getId() + " , " + e.getValue() + ")", Database.QUERY.UPDATE);

                }
                request.getSession().setAttribute("cart", new ShoppingCart());

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Database.getDatabase().closeConnection();
        }

    }
}
