package com.database;

import com.domain.account.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author soufyan
 */
public class Database {

    public static enum QUERYTYPES {
        SELECT, FROM, WHERE, IS, INSERT, INTO, AND, FIELD, TABLE, VALUES, DELETE
    }

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://www.ict4events.nl:3306/photoshop";

    //  Database credentials
    static final String USER = "photoshop";
    static final String PASS = "pts4";

    private static Database database;

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    static Connection conn = null;

    public static void openConnection() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
            }
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection Failed!");
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Connection closed");
                }
            } else {
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ResultSet queryDatabase(Map<Integer, LinkedHashMap<QUERYTYPES, String>> query) {
        //Queried de database voor resultaten met gegeven tabel en field
        //Na gebruik _altijd_ connectie closen (closeConnection())
        try {
            if (conn == null || conn.isClosed()) {

                openConnection();
                if (conn == null) {
                    System.out.println("Geen conn");
                    return null;
                }
            }
            String queryTemp = "";
            Map<QUERYTYPES, String> toPrepare = new LinkedHashMap<QUERYTYPES, String>();
            for (Entry<Integer, LinkedHashMap<QUERYTYPES, String>> entry : query.entrySet()) {
                for (Entry<QUERYTYPES, String> entryinner : entry.getValue().entrySet()) {
                    if (entryinner.getKey() == QUERYTYPES.IS) {
                        queryTemp += " = ? ";
                        toPrepare.put(entryinner.getKey(), entryinner.getValue());
                    } else {
                        queryTemp += " " + entryinner.getValue() + " ";
                    }
                }
            }
            queryTemp += ";";

            PreparedStatement SQLquery = conn.prepareStatement(queryTemp);
            int i = 1;
            for (Entry<QUERYTYPES, String> entry : toPrepare.entrySet()) {
                SQLquery.setString(i, entry.getValue());
                i++;
            }

            return SQLquery.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public static ResultSet query(String query) {
        return queryDatabase(queryBuilder(query));
    }

    public static void tempQuery(Database.QUERYTYPES type, String value, Map<Integer, LinkedHashMap<QUERYTYPES, String>> query) {
        int size = query.size();
        if (query.get(size) == null) {
            query.put(size, new LinkedHashMap<Database.QUERYTYPES, String>());
        }
        query.get(size).put(type, value);
    }

    private static Map queryBuilder(String querytoBuild) {
        Map<Integer, LinkedHashMap<Database.QUERYTYPES, String>> query = new LinkedHashMap<>();

        List<String> words = new ArrayList<>();

        for (String word : querytoBuild.split(" ")) {
            words.add(word);
        }

        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            switch (word) {
                case "SELECT":
                    tempQuery(QUERYTYPES.SELECT, word, query);
                    break;
                case "FROM":
                    tempQuery(QUERYTYPES.FROM, word, query);
                    break;
                case "WHERE":
                    tempQuery(QUERYTYPES.WHERE, word, query);
                    break;
                case "AND":
                    tempQuery(QUERYTYPES.AND, word, query);
                    break;
                case "DELETE":
                    tempQuery(QUERYTYPES.DELETE, word, query);
                    break;
                case "INSERT":
                    tempQuery(QUERYTYPES.INSERT, word, query);
                    break;
                case "INTO":
                    tempQuery(QUERYTYPES.INTO, word, query);
                    break;
                case "VALUES":
                    tempQuery(QUERYTYPES.VALUES, word, query);
                    break;
                default:
                    String previousword = words.get(i - 1);
                    switch (previousword) {
                        case "SELECT":
                            tempQuery(QUERYTYPES.FIELD, word, query);
                            break;
                        case "FROM":
                            tempQuery(QUERYTYPES.TABLE, word, query);
                            break;
                        case "WHERE":
                            tempQuery(QUERYTYPES.FIELD, word, query);
                            break;
                        case "INTO":
                            tempQuery(QUERYTYPES.TABLE, word, query);
                            break;
                        case "AND":
                            tempQuery(QUERYTYPES.FIELD, word, query);
                            break;
                        case "=":
                            for (int j = i + 1; j < words.size(); j++) {
                                String word2 = words.get(j);
                                if (!word2.contains("SELECT")
                                        && !word2.contains("FROM")
                                        && !word2.contains("WHERE")
                                        && !word2.contains("AND")
                                        && !word2.contains("DELETE")
                                        && !word2.contains("INSERT")
                                        && !word2.contains("INTO")
                                        && !word2.contains("VALUES")) {
                                    word += " " + word2;
                                    words.remove(j);
                                    j--;
                                }
                            }
                            tempQuery(QUERYTYPES.IS, word, query);
                            break;
                        default:
                            break;
                    }
            }
        }
        int previousentry = 0;

        /*        for (Entry<Integer, LinkedHashMap<Database.QUERYTYPES, String>> entry : query.entrySet()) {
            if (entry.getValue().containsKey(QUERYTYPES.IS)) {
                if (query.get(previousentry).containsKey(QUERYTYPES.IS)) {
                    String previousword = query.get(previousentry).get(QUERYTYPES.IS);
                    query.put(previousentry, new LinkedHashMap<>());
                    query.get(previousentry).put(QUERYTYPES.IS, previousword + " " + query.get(previousentry + 1).get(QUERYTYPES.IS));
                    query.remove(previousentry + 1);
                }
            }
            previousentry = entry.getKey();

        }*/
        return query;

    }

}
