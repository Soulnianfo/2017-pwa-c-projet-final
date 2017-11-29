/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author Smaïl
 */
@Data
@Entity
public class Login {
    @Id
    int id;        
   public String username;
   public String password;
    public Login(){
        
    }

    public Login(String username, String password) {
        this.username= username;
        this.password = password;
    }
    
    



}
