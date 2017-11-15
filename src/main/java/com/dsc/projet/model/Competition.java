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
    
    String titre;
    String description;
    
    @ManyToMany
    List<Skills> reqSkills = new ArrayList<>();
    
    Date begin;
    Date end;

    public Competition() {
        
    }
    
    public Competition(long id, String titre,  Date begin, Date end, String description, List<Skills> l) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.begin = begin;
        this.end = end;
        this.reqSkills = l;
    }           
    
}
