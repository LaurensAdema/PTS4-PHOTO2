/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.photo;

import java.io.Serializable;
import java.util.ArrayList;
import org.joda.time.*;

/**
 *
 * @author markb
 */
public class Project implements Serializable {

    public Project(String Name, DateTime Date, String code)
    {
        this.Name = Name;
        this.Date = Date;
        this.code = code;
        this.fotos = new ArrayList<>();
    }
    
    public Project(String Name, DateTime Date, String code, ArrayList<Photo> fotos)
    {
        this.Name = Name;
        this.Date = Date;
        this.code = code;
        this.fotos = fotos;
    }

    private String Name;

    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName()
    {
        return Name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name)
    {
        this.Name = Name;
    }

    private DateTime Date;

    /**
     * Get the value of Date
     *
     * @return the value of Date
     */
    public DateTime getDate()
    {
        return Date;
    }

    /**
     * Set the value of Date
     *
     * @param Date new value of Date
     */
    public void setDate(DateTime Date)
    {
        this.Date = Date;
    }

    private String code;

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public String getCode()
    {
        return code;
    }

    private ArrayList<Photo> fotos;

    /**
     * Get the value of fotos
     *
     * @return the value of fotos
     */
    public ArrayList<Photo> getFotos()
    {
        return fotos;
    }

    /**
     * Add foto
     *
     * @param foto new foto
     */
    public void addFoto(Photo foto)
    {
        this.fotos.add(foto);
    }
    
    /**
     * Set the value of fotos
     *
     * @param fotos new value of fotos
     */
    public void setFoto(ArrayList<Photo> fotos)
    {
        this.fotos = fotos;
    }

    @Override
    public String toString()
    {
        return "Project{" + "Name=" + Name + ", Date=" + Date + '}';
    }

}
