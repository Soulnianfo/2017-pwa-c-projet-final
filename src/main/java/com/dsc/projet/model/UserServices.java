/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.text.normalizer.ICUBinary;

/**
 *
 * @author Smaïl
 */
@Component
public class UserServices implements UserDetailsService {
   @Inject
   StudentRepos repoStud;
   
   @Inject
   CompanyRepository repoComp;



    public final PasswordEncoder encoder = new BCryptPasswordEncoder();
   
  
   
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Student u = repoStud.findByUsername(username);
          System.err.println("USER: "+u);
         if (u != null) {
                 
                 return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getDerivedPassword(), u.getRoles());

        }

         Company u1 = repoComp.findByUsername(username); 
        if (u1 != null) {
            
                 return new org.springframework.security.core.userdetails.User(u1.getUsername(), u1.getDerivedPassword(), u1.getRoles());

        }
        
         throw new UsernameNotFoundException(username);
         
       
    }

   
     public UserDetails  loadCompanyByUsername(String username) throws UsernameNotFoundException {
          System.err.println("*****entre****comapny : ");
       Company u1 = repoComp.findByUsername(username);
       System.err.println("comapny : "+u1);
           if (u1 == null) {
            throw new UsernameNotFoundException(username);
        }
     return new org.springframework.security.core.userdetails.User(u1.getUsername(), u1.getDerivedPassword(), u1.getRoles());
       
         
     }

    public void saveStudentComputingDerivedPassword(Student u, String rawPassword) {
        System.err.println("PASS: "+rawPassword);
        setCompanytingDerivedPassword(u, rawPassword);
        System.err.println("PASS: "+u.passWord);
        repoStud.save(u);
        System.out.println("NOUVEAU ETUDIANT :"+repoStud.findByUsername(u.username).username +" "+
                repoStud.findByUsername(u.username).id);
    }
    
    public void setCompanytingDerivedPassword(Student u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
       // System.out.println("mot de passe encoder"+codedPassword);
        u.setDerivedPassword(codedPassword);
    }
    
     public void saveCompanyComputingDerivedPassword(Company u, String rawPassword) {
        setComputingDerivedPassword(u, rawPassword);
        repoComp.save(u);
    }
    
    
    public void setComputingDerivedPassword(Company u, String rawPassword) {
        String codedPassword = encoder.encode(rawPassword);
       // System.out.println("mot de passe encoder"+codedPassword);
        u.setDerivedPassword(codedPassword);
    }

    }
