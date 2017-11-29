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
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author snianfo
 */
@Data
@Entity
public class CompanyProject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String title;
    String descrip;
    
    @ManyToOne
    Company creator;
    
    String beginMonth;
    
    String beginYear;
    
    String endMonth;
    
    String endYear;
    
    public CompanyProject(){
        
    }

    public CompanyProject(String title, String descrip, Company creator, String beginMonth, String beginYear, String endMonth, String endYear) {
        
        this.title = title;
        this.descrip = descrip;
        this.creator = creator;
        this.beginMonth = beginMonth;
        this.beginYear = beginYear;
        this.endMonth = endMonth;
        this.endYear = endYear;
    }

   
}
