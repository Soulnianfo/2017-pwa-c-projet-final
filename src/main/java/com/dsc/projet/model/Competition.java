/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * //@author snianfo
 */
@Entity
@Data
public class Competition {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id;
    
    String title;
    String descrip;
    
    @ManyToOne
    Company creator;
    
    @ManyToMany
    List<Skills> reqSkills = new ArrayList<>();
    
    String beginMonth;
    
    String beginYear;
    
    String endMonth;
    
    String endYear;

    public Competition() {
        
    }
    
    public Competition(String title, String description, Company creator, String beginMonth, String beginYear, String endMonth, String endYear) {
        this.title = title;
        this.descrip = description;
        this.creator = creator;
        this.beginMonth = beginMonth;
        this.beginYear = beginYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
    }    
}