/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.photo;

import com.database.Database;
import com.domain.site.LanguageServlet;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author markb
 */
public class Project implements Serializable{

    private int id;
    private String name;
    private Timestamp date;
    private List<Group> groups;

    public Project(int id, String name, Timestamp date)
    {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Project(String name, Timestamp date)
    {
        this.name = name;
        this.date = date;
    }
    
    public Project(String name)
    {
        this.name = name;
        groups = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Timestamp getDate()
    {
        return date;
    }

    public void setDate(Timestamp date)
    {
        this.date = date;
    }

    public List<Group> getGroups()
    {
        if (groups == null) {
            groups = new ArrayList<>();
            try {
                ResultSet results = Database.getDatabase().query("SELECT * FROM pgroup WHERE id IN (SELECT pgroupID FROM project_pgroup WHERE projectID = " + id + ")", Database.QUERY.QUERY);

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
        return groups;
    }
    
    @Override
    public String toString()
    {
        return "Project{" + "Name=" + name + ", Date=" + date.toLocalDateTime() + '}';
    }

}
