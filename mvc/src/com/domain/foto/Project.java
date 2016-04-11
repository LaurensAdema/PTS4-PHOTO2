/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.foto;

import org.joda.time.*;

/**
 *
 * @author markb
 */
public class Project {

    public Project(String Name, DateTime Date) {
        this.Name = Name;
        this.Date = Date;
    }

    private String Name;

    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    private DateTime Date;

    /**
     * Get the value of Date
     *
     * @return the value of Date
     */
    public DateTime getDate() {
        return Date;
    }

    /**
     * Set the value of Date
     *
     * @param Date new value of Date
     */
    public void setDate(DateTime Date) {
        this.Date = Date;
    }

    @Override
    public String toString() {
        return "Project{" + "Name=" + Name + ", Date=" + Date + '}';
    }

}
