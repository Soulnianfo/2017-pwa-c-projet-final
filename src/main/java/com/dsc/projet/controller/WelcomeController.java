/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.controller;

import com.dsc.projet.model.Company;
import com.dsc.projet.model.CompanyRepository;
import com.dsc.projet.model.Login;
import com.dsc.projet.model.ProjectRepos;
import com.dsc.projet.model.Student;
import com.dsc.projet.model.StudentRepos;
import com.dsc.projet.model.UserServices;
import groovy.util.logging.Log;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
   
    @Inject
    UserServices userService ;
    @Inject
    ProjectRepos repos;
    
    List<Company> listComp = new ArrayList<>();
    List<Student> listStud = new ArrayList<>();
    
    @RequestMapping("/")
   public String Welcome(Model m){
    Company v;
    Student v2;
    Login v3;
       m.addAttribute("comp", v =new Company());
       m.addAttribute("stud", v2 =new Student());
       m.addAttribute("log", v3 =new Login());
     
        return "welcome";
   }
   
     @RequestMapping("/profile")
   public String direct(Authentication auth, Model m, HttpServletRequest request) {
            HttpSession session = request.getSession();
            session.setAttribute("username", auth.getName());
//         System.out.println("Role User :"+auth.getPrincipal());
//         System.out.println("Role User :"+auth.getName());
           System.out.println("bool :"+auth.getAuthorities().toString());
        if(auth.getAuthorities().toString().equals("[STUDENT]")){
            
            m.addAttribute("student", studRep.findByUsername(auth.getName()));
            m.addAttribute("projects", repos.findAll());
                  return "homeStudent";
        } 
        
       m.addAttribute("company", rep.findByUsername(auth.getName()));

                  return "home_company";
   } 

     

   @RequestMapping("/addCompany")
   public String addCompany(Company v, HttpServletRequest request){
       HttpSession session = request.getSession();
       session.setAttribute("username", v.username);
     // listComp = (List<Company>) rep.findAll();
      System.out.println("enregistrement company :"+v.username);
      //System.out.println("**********Data Saved************");
      userService.saveCompanyComputingDerivedPassword(v, v.pswd);
      System.out.println( "affichage du resultat inserer de company "+userService.loadUserByUsername(v.username));

            return "redirect:/profil_company";
   }  
    
    @RequestMapping("/addStudent")
    public String addStudent(Student v, HttpServletRequest request){
       // studRep.save(v);
       HttpSession session = request.getSession();
       session.setAttribute("username", v.username);
       
        userService.saveStudentComputingDerivedPassword(v, v.passWord);
 
       // m.addAttribute("student", studRep.findByUsername(v.username));
        System.out.println( "affichage du resultat inserer "+userService.loadUserByUsername(v.username));
        
        return "redirect:/monProfil";
        
    }
    
    /*
    @RequestMapping("/checkLogin")
    public String checkLogin(Login v){
        System.out.println("Entré");
        System.out.println(v.getUsername());
        System.out.println(v.password);
       System.out.println( "affichage du resultat"+userService.loadUserByUsername(v.getUsername()));
      // if(userService.equals(null))
          //  System.out.println("rien trover");
        return "homeStudent";
        
    }
*/
    }
    
   