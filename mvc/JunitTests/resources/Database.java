package resources;



import com.database.*;
import com.domain.account.Account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author NesciO
 */
public class Database {

    public void query(String select__from_account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static enum QUERYTYPES {
        SELECT, FROM, WHERE, IS, INSERT, INTO, AND, FIELD, TABLE, VALUES, DELETE, IN, HAAKJE, OR
    }

    public static enum QUERY {
        QUERY, UPDATE
    }

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://84.24.156.65:3306/photoshop";
    //  static final String DB_URL = "jdbc:mysql://localhost:3333";

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

    public void openConnection() {
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

    private ResultSet queryDatabase(Map<Integer, LinkedHashMap<QUERYTYPES, String>> query, QUERY type) {
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
            List<String> toPrepare = new ArrayList<>();
            for (Entry<Integer, LinkedHashMap<QUERYTYPES, String>> entry : query.entrySet()) {
                for (Entry<QUERYTYPES, String> entryinner : entry.getValue().entrySet()) {
                    if (entryinner.getKey() == QUERYTYPES.IS) {
                        if (type == QUERY.QUERY) {
                            queryTemp += " = ? ";
                        } else {
                            queryTemp += " ? ";
                        }
                        toPrepare.add(entryinner.getValue());
                    } else {
                        queryTemp += " " + entryinner.getValue() + " ";
                    }
                }
            }
            queryTemp += ";";

            if (type == QUERY.QUERY) {
                PreparedStatement SQLquery = conn.prepareStatement(queryTemp);
                int i = 1;
                for (String entry : toPrepare) {
                    SQLquery.setString(i, entry);
                    i++;
                }
                return SQLquery.executeQuery();
            } else {
                PreparedStatement SQLquery = conn.prepareStatement(queryTemp, Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                for (String entry : toPrepare) {
                    SQLquery.setString(i, entry);
                    i++;
                }

                SQLquery.executeUpdate();

                return SQLquery.getGeneratedKeys();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public ResultSet query(String query, QUERY type) {
        return queryDatabase(queryBuilder(query), type);
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

        querytoBuild = querytoBuild.replace("(", " ( ");
        querytoBuild = querytoBuild.replace(")", " ) ");

        for (String word : querytoBuild.split(" ")) {
            words.add(word);
        }

        words.removeAll(Arrays.asList("", null));

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
                case "IN":
                    tempQuery(QUERYTYPES.IN, word, query);
                    break;
                case ")":
                    tempQuery(QUERYTYPES.HAAKJE, word, query);
                    break;
                case "(":
                    tempQuery(QUERYTYPES.HAAKJE, word, query);
                    break;
                case "IS":
                    tempQuery(QUERYTYPES.IS, word, query);
                    break;
                case "OR":
                    tempQuery(QUERYTYPES.OR, word, query);
                    break;
                case ",":
                    tempQuery(QUERYTYPES.HAAKJE, word, query);
                    break;
                default:
                    String previousword = words.get(i - 1);
                    for (int j = i + 1; j < words.size(); j++) {
                        String word2 = words.get(j);
                        if (!word2.contains("SELECT")
                                && !word2.contains("FROM")
                                && !word2.contains("WHERE")
                                && !word2.contains("AND")
                                && !word2.contains("DELETE")
                                && !word2.contains("INSERT")
                                && !word2.contains("INTO")
                                && !word2.contains("VALUES")
                                && !word2.contains("(")
                                && !word2.contains(")")
                                && !word2.contains("=")
                                && !word2.contains("IN")
                                && !word2.contains("AS")
                                && !word2.contains(",")
                                && !word2.contains("OR")
                                && !word.contains("=")) {
                            word += " " + word2;
                            words.remove(j);
                            j--;
                        } else {
                            break;
                        }
                    }
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
                            tempQuery(QUERYTYPES.IS, word, query);
                            break;
                        case "OR":
                            tempQuery(QUERYTYPES.FIELD, word, query);
                            break;
                        case "(":
                            if (words.get(i - 2).equals("VALUES")) {
                                tempQuery(QUERYTYPES.IS, word, query);
                            } else {
                                tempQuery(QUERYTYPES.FIELD, word, query);
                            }
                            break;
                        case ",":
                            tempQuery(QUERYTYPES.IS, word, query);
                            break;
                        default:
                            break;
                    }
            }
        }
        return query;

    }
public ResultSet RawQuery(String query)
{
    try {
            if (conn == null || conn.isClosed()) {

                openConnection();
                if (conn == null) {
                    System.out.println("Geen conn");
                    return null;
                }
            if (query.toLowerCase().startsWith("select"))
                    {
                PreparedStatement SQLquery = conn.prepareStatement(query);
                SQLquery.executeQuery();
                return SQLquery.getGeneratedKeys();
                    }
                    
            else    {
			PreparedStatement SQLquery = conn.prepareStatement(query);
                        SQLquery.executeUpdate();
                        return SQLquery.getGeneratedKeys();
                    }
        }}
			catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
}
}
