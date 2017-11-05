/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.Model;

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
public class Student {
    @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    String name;
    String firstName;
    String date;
    String passWord;
    String pseudo;
    String email;

    public Student(){   
    }
    public Student(String name, String firstName,String date, String passWord,String speudo,String email){
        this.name = name;
        this.firstName = firstName;
        this.date = date;
        this.passWord = passWord;
        this.pseudo = speudo;
        this.email = email;
    }
    
}
