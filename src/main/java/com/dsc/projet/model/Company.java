/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 *
 * @author snianfo
 */
@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)        
    long id;
    public String username;
    public String email;
    public String pswd;
    public String description;
    
    public String name;
    public String city;
    
    
    
    @OneToOne(cascade = CascadeType.ALL)
    public Image img;
    
    @ManyToMany()
    List<Domain> domList = new ArrayList<>();
    
    @OneToMany(mappedBy = "creator")
    List<CompanyProject> projects = new ArrayList<>();
    
    @OneToMany(mappedBy = "creator",cascade = CascadeType.ALL)
    List<Competition> competitions = new ArrayList<>();
    
    @ManyToMany()
    List<Student> followers = new ArrayList<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    public Set<UserRole> roles = new HashSet<>();
    
    public Company() {
         this.roles.add(UserRole.COMPANY); 
    }

    public Company(String username, String name, String email,String pswd, String description) {
        this.name = name;    
        this.username = username;
        this.email = email;
        this.description = description;
        this.pswd = pswd;
        this.roles.add(UserRole.COMPANY); 
    }
    
    
    public Company(String nom, String v, String description, List<Domain> dom) {
        this.name = nom;
        this.city = v;
        this.description = description;
        this.domList = dom;
        this.roles.add(UserRole.COMPANY); 
    }
    
       void setDerivedPassword(String codedPassword) {
        this.pswd = codedPassword;
    }
    
    
    String getDerivedPassword() {
       return  this.pswd;
    }
    
    public void setRoles(Set<UserRole> roles){
        this.roles = roles;
    }
    public Set<UserRole> getRoles(Set<UserRole> roles){
        return roles;
    }
    public String getUsername(){
        return username;
    }
}