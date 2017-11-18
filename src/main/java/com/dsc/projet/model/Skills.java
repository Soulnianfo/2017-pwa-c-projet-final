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
@Entity
@Data
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
  
    String label;

    public Skills() {
    }
    
    

    public Skills( String label) {
        this.label = label;
    }
    
    
    
}
