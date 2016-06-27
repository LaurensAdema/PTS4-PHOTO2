package com.account;

import com.database.Database;
import com.domain.account.Account;
import com.domain.bestelling.Product;
import com.domain.bestelling.ShoppingCart;
import com.domain.photo.Group;
import com.domain.photo.Photo;
import com.domain.site.LanguageServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NesciO
 */
public class MyPhotosServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") != null) {
            ShoppingCart shoppingcart = (ShoppingCart) request.getSession().getAttribute("cart");
            if (request.getParameter("add") != null || request.getParameter("del") != null || request.getParameter("rem") != null || request.getParameter("plus") != null) {
                String parameter = null;
                if (request.getParameter("add") != null) {
                    parameter = request.getParameter("add");
                }
                if (request.getParameter("del") != null) {
                    parameter = request.getParameter("del");
                }
                if (request.getParameter("rem") != null) {
                    parameter = request.getParameter("rem");
                }
                if (request.getParameter("plus") != null) {
                    parameter = request.getParameter("plus");
                }

                Product item = null;
                ResultSet results = null;

                try {
                    if (parameter != null) {
                        results = Database.getDatabase().query("SELECT * FROM product WHERE id = " + parameter, Database.QUERY.QUERY);

                        if (results != null) {
                            while (results.next()) {
                                item = new Product(results.getInt("id"), results.getString("material"), results.getString("color"), results.getString("size"), null, results.getInt("price"));

                                Photo photo;
                                results = Database.getDatabase().query("SELECT * FROM photo WHERE id = " + results.getInt("photoID"), Database.QUERY.QUERY);
                                if (results != null) {
                                    while (results.next()) {
                                        photo = new Photo(results.getString("name"), results.getInt("price"), results.getInt("id"), results.getString("pathlowres"), results.getString("pathhighres"));
                                        item.setPhoto(photo);
                                        item.setName(photo.getName());
                                    }
                                }
                            }
                        }
                        if (request.getParameter("add") != null) {
                            if (shoppingcart.getProducts().get(item) == null || shoppingcart.getProducts().get(item) == 0) {
                                shoppingcart.getProducts().put(item, 1);
                            } else {
                                shoppingcart.getProducts().put(item, shoppingcart.getProducts().get(item) + 1);

                            }
                        } else if (request.getParameter("del") != null) {
                            shoppingcart.getProducts().remove(item);
                        } else if (request.getParameter("rem") != null) {
                            if (shoppingcart.getProducts().get(item) != 1) {
                                shoppingcart.getProducts().put(item, shoppingcart.getProducts().get(item) - 1);
                            } else {
                                shoppingcart.getProducts().remove(item);
                            }
                        } else if (request.getParameter("plus") != null) {
                            shoppingcart.getProducts().put(item, shoppingcart.getProducts().get(item) + 1);
                            request.getSession().setAttribute("cart", shoppingcart);

                        }
                        request.getSession().setAttribute("cart", shoppingcart);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Database.getDatabase().closeConnection();
                }

            }
        }

        List<Group> groups = new ArrayList<>();
        if (request.getSession().getAttribute("account") != null) {
            try {
                Account account = (Account) request.getSession().getAttribute("account");
                ResultSet results = Database.getDatabase().query("SELECT * FROM pgroup WHERE id IN ( SELECT groupID FROM account_group WHERE accountID = " + account.getID() + " )", Database.QUERY.QUERY);

                if (results != null) {
                    while (results.next()) {
                        groups.add(new Group(results.getInt("id"), results.getString("logincode"), results.getString("groupname")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
        }
        request.getSession().setAttribute("groups", groups);
    }
}
