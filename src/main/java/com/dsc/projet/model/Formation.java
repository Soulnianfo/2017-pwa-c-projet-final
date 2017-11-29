/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

/**
 *
 * @author snianfo
 */
@Data
@Entity
public class Formation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String Domain;
    String name;
    String dateBegin;
    String dateEnd;
    @ManyToMany(fetch = FetchType.EAGER)
    List <Student> students = new ArrayList<>();
    
    public Formation(){
        
    }

    public Formation(Long id, String Domain, String name, String dateBegin, String dateEnd, List<Student> student) {
        this.id = id;
        this.Domain = Domain;
        this.name = name;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.students = student;
    }

    

    
}
