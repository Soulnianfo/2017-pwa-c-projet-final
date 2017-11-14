/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author snianfo
 */
@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String name;
    String descrip;
    String autor;
    
    
    public Project(){
        
    }

    public Project( String name, String descrp, String autor) {
       
        this.name = name;
        this.descrip = descrp;
        this.autor = autor;
    }
    
}
