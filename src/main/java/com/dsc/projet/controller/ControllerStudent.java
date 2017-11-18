
package com.dsc.projet.controller;

import com.dsc.projet.model.Project;
import com.dsc.projet.model.ProjectRepos;
import com.dsc.projet.model.SkillsRepository;
import com.dsc.projet.model.Skills;
import com.dsc.projet.model.Student;
import com.dsc.projet.model.StudentRepos;
import java.util.ArrayList;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author n
 */
@Controller
public class ControllerStudent {
    ArrayList<Project> listProject = new ArrayList<>();
    @Inject
    ProjectRepos repos;
    @Inject StudentRepos studRepos;
    @Inject SkillsRepository skillRepo;
   
    Student student1;
    @RequestMapping("/home")
    public String home(Model model){
        student1 = new Student("nianfo","souleymane","17/09/1992","1234","snianfo","snianfo@gmail.com");
       
        //Project p1 = new Project("RestaurationPwa", "Creation d'une platfome permettant aux Utilisateurs de reserver en ligne", "Nianfo");
        //Project p2 = new Project("PartageDoc", "Creation d'une platfome permettant aux Utilisateurs de partager des docs en ligne", "UJM");
        
       //repos.save(p2);
        studRepos.save(student1);
        //repos.save(p1);
        model.addAttribute("projects",repos.findAll());
       model.addAttribute("student",studRepos.findOne(student1.getId()));
        
        return "homeStudent";
    }
    @RequestMapping("/projectDetail")
    public String detailProject(Model model, @RequestParam("cid") Long id){
       
        model.addAttribute("detail", repos.findOne(id));
        return "projectDetails";
    }
    @RequestMapping("/monProfil")
    public String profil(Model model, @RequestParam("monId") Long id){
        
        model.addAttribute("myprojects", repos.findAll());
        model.addAttribute("student", studRepos.findOne(id));
        model.addAttribute("skills", skillRepo.findAll());
        model.addAttribute("skill",new Skills());
        return "profil";
    }
    @RequestMapping("/addskill")
    public String addSkill(Skills skill){
        skillRepo.save(skill);
       return "redirect:/monProfil?monId=14";
    }
    
}
