package com.account;

import com.database.Database;
import com.domain.account.Account;
import com.domain.account.Photographer;
import com.domain.account.Customer;
import com.domain.account.Admin;
import com.domain.bestelling.Bestelling;
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

public class MyOrdersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bestelling order = null;
        Account account = (Account) request.getSession().getAttribute("account");
        int accountID = -1;
        if (request.getParameter("id") != null && account != null) {
            try {

                ResultSet results = Database.getDatabase().query("SELECT * FROM ordera WHERE id = " + request.getParameter("id") + " AND accountID = " + account.getID(), Database.QUERY.QUERY);

                if (results != null) {
                    while (results.next()) {
                        order = new Bestelling(results.getInt("price"), results.getDate("date"), results.getInt("id"));
                        accountID = results.getInt("accountID");
                    }
                    ResultSet results1 = Database.getDatabase().query("SELECT product.*, order_product.quantity FROM product, order_product WHERE id IN ( SELECT productID FROM order_product WHERE orderID = " + order.getId() + " ) AND product.id=order_product.productID", Database.QUERY.QUERY);

                    if (results1 != null) {
                        while (results1.next()) {
                            Product product = new Product(results1.getInt("id"), results1.getString("material"), results1.getString("color"), results1.getString("size"), null, results1.getInt("price"));
                            order.getProducts().put(product, results1.getInt("quantity"));
                            Photo photo;
                            ResultSet results2 = Database.getDatabase().query("SELECT * FROM photo WHERE id = " + results1.getInt("photoID"), Database.QUERY.QUERY);
                            if (results2 != null) {
                                while (results2.next()) {
                                    photo = new Photo(results2.getString("name"), results2.getInt("price"), results2.getInt("id"), results2.getString("pathlowres"), results2.getString("pathhighres"));
                                    product.setPhoto(photo);
                                    product.setName(photo.getName());
                                    order.getProducts().put(product, order.getProducts().get(product));
                                }
                            }
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(MyOrdersServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
            if (accountID == account.getID()) {
                request.getSession().setAttribute("order", order);
            }
        } else {
            
        }

    }
}
