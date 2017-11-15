/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.controller;

import com.dsc.projet.model.Company;
import com.dsc.projet.model.CompanyRepository;
import com.dsc.projet.model.Student;
import com.dsc.projet.model.StudentRepos;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Smaïl
 */
@Controller
public class WelcomeController {
    
    
     @Inject 
    CompanyRepository rep;
    @Inject 
    StudentRepos studRep;
    
    List<Company> listComp = new ArrayList<>();
    List<Student> listStud = new ArrayList<>();
   
    @RequestMapping("/")
   public String Welcome(Model m){
    Company v;
    Student v2;
       m.addAttribute("comp", v =new Company());
       m.addAttribute("stud", v2 =new Student());
       System.out.println("did");
        return "welcome";
   }
    
   @RequestMapping("/addCompany")
   public String addCompany(Company v){
       
      rep.save(v);
      listComp = (List<Company>) rep.findAll();
      System.out.println(listComp);
      System.out.println("**********Data Saved************");
            return "home_company";
   }  
    
    @RequestMapping("/addStudent")
    public String addStudent(Student v){
        studRep.save(v);
        System.out.println("Tout les etudiants"+studRep.findAll());
        return "homeStudent";
        
    }
   
}
