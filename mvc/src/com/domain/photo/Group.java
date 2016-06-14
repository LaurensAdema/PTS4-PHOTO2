/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.photo;

import com.database.Database;
import com.domain.site.LanguageServlet;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NesciO
 */
public class Group {

    private int id;
    private String loginCode;
    private String groupName;
    private List<Photo> photos;
    private Date date;

    public Group(int id, String loginCode, String groupName, Date date) {
        this.id = id;
        this.loginCode = loginCode;
        this.groupName = groupName;
        this.date = date;
    }
    public Group(int id, String loginCode, String groupName) {
        this.id = id;
        this.loginCode = loginCode;
        this.groupName = groupName;
    }
    public Group()
    {
        
    }
    public List<Photo> getPhotos() {
        if (photos == null) {
            loadPhotos();
        }
        return photos;
    }

    private void loadPhotos() {
        if (photos == null) {
            photos = new ArrayList<>();
            try {
                ResultSet results = Database.getDatabase().query("SELECT * FROM photo WHERE id IN ( SELECT photoID FROM photo_group WHERE groupID = " + this.id + " )", Database.QUERY.QUERY);

                if (results != null) {
                    while (results.next()) {
                        photos.add(new Photo(results.getString("name"), results.getInt("price"), results.getInt("id"), results.getString("pathlowres"), results.getString("pathhighres")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LanguageServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Database.getDatabase().closeConnection();
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public Date getDate() {
        return date;
    }

}
