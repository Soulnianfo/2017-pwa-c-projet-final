/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dsc.projet.Controller;

import com.dsc.projet.Model.Project;
import com.dsc.projet.Model.ProjectRepos;
import com.dsc.projet.Model.Student;
import com.dsc.projet.Model.StudentRepos;
import java.util.ArrayList;
import javax.inject.Inject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author snianfo
 */
@org.springframework.stereotype.Controller
public class ControllerStudent {
    
    ArrayList<Project> listProject = new ArrayList<>();
    @Inject
    ProjectRepos repos;
    @Inject StudentRepos studRepos;
    
    @RequestMapping("/home")
    public String home(Model model){
        Student student = new Student("nianfo","souleymane","17/09/1992","1234","snianfo","snianfo@gmail.com");
        Project p1 = new Project("RestaurationPwa", "Creation d'une platfome permettant aux Utilisateurs de reserver en ligne", "student");
        //Project p2 = new Project("PartageDoc", "Creation d'une platfome permettant aux Utilisateurs de partager des docs en ligne", "UJM");
        
       // repos.save(p2);
       // studRepos.save(student);
        repos.save(p1);
        model.addAttribute("projects",repos.findAll());
       // model.addAttribute("student",studRepos.findOne(id));
 
        return "homeStudent";
    }
    @RequestMapping("/projectDetail")
    public String detailProject(Model model, @RequestParam("cid") Long id){
       
        model.addAttribute("detail", repos.findOne(id));
        return "projectDetails";
    }
    @RequestMapping("/monProfil")
    public String profil(Model model, @RequestParam("monId") Long id){
        
     //  model.addAttribute("mesprojet", repos.exists(id));
        return "profil";
    }
    
}
